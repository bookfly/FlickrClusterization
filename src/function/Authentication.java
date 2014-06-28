/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jelena
 */
public class Authentication extends Functions {

    public Authentication() {
    }

    public void dajForb() throws IOException, ParserConfigurationException, SAXException {

        //Authentication       
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "method" + getData().getMethodGetFrob());
        //Signature must be MD5 encoded
        setSignature(MD5(getSig()));

        //Flickr moved from http to https
        setRequest(getData().getRequestMethod() + getData().getMethodGetFrob() + "&api_key=" + getData().getKey() + "&api_sig=" + getSignature());
        System.out.println("GET frob request: " + getRequest());

        sendRequest(getClient(), "frob");
    }

    public void logIn() throws IOException, ParserConfigurationException, SAXException {

        //Make Flickr login link
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "frob" + getToken() + "permswrite");
        setSignature(MD5(getSig()));
        setRequest(getData().getRequestAuth() + getData().getKey() + "&perms=write&frob=" + getToken() + "&api_sig=" + getSignature());

        //Certification through web browser is needed
        System.out.println("Browse to the following flickr url to authenticate yourself and then press enter.");
        System.out.println(getRequest());
        BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
        String line = infile.readLine();

        //Authentication is needed
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "frob" + getToken() + "method" + getData().getMethodGetToken());
        setSignature(MD5(getSig()));
        setRequest(getData().getRequestMethod() + getData().getMethodGetToken() + "&api_key=" + getData().getKey() + "&frob=" + getToken() + "&api_sig=" + getSignature());
        System.out.println("Token request: " + getRequest());

        sendRequest(getClient(), "token");
    }

    private void sendRequest(HttpClient client, String tagName) throws IOException, ParserConfigurationException, SAXException {

        setMethod(new GetMethod(getRequest()));

        //Sending GET request
        setStatusCode(client.executeMethod(getMethod()));

        if (getStatusCode() != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod().getStatusLine());
        }
        setRstream(null);

        //Receiving body of response
        setRstream(getMethod().getResponseBodyAsStream());

        //Getting XML and seraching for frob value
        setResponse(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getRstream()));

        //Make sure token is in response
        NodeList tokenResponse = getResponse().getElementsByTagName(tagName);
        Node tokenNode = tokenResponse.item(0);
        if (tokenNode != null) {
            setToken(tokenNode.getTextContent());
            System.out.println("Successfully retrieved " + tagName + ": " + getToken());
        } else {
            printFlickrError(getResponse());
            return;
        }
    }

}
