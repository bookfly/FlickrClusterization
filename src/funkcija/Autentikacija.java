/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funkcija;

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
public class Autentikacija extends Funkcije {

    public Autentikacija() {
    }

    public void dajForb() throws IOException, ParserConfigurationException, SAXException {

        //potrebna je autentikacija        
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "method" + getData().getMethodGetFrob());

        //potpis mora biti MD5 enkodiran
        setSignature(MD5(getSig()));

        //flickr je promenio http na https
        setRequest(getData().getRequestMethod() + getData().getMethodGetFrob() + "&api_key=" + getData().getKey() + "&api_sig=" + getSignature());
        System.out.println("GET frob request: " + getRequest());

        zahtevOdgovorGET(getClient(), "frob");
    }

    public void logIn() throws IOException, ParserConfigurationException, SAXException {

        //napravi flickr login link
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "frob" + getToken() + "permswrite");
        setSignature(MD5(getSig()));
        setRequest(getData().getRequestAuth() + getData().getKey() + "&perms=write&frob=" + getToken() + "&api_sig=" + getSignature());

        //potrebno je da se odobri koriscenje api-a kroz browser
        System.out.println("Browse to the following flickr url to authenticate yourself and then press enter.");
        System.out.println(getRequest());
        BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
        String line = infile.readLine();

        //uzmi auth token iz frob-a
        //potrebna je ponovna autentikacija
        setSig(getData().getSecret() + "api_key" + getData().getKey() + "frob" + getToken() + "method" + getData().getMethodGetToken());
        setSignature(MD5(getSig()));
        setRequest(getData().getRequestMethod() + getData().getMethodGetToken() + "&api_key=" + getData().getKey() + "&frob=" + getToken() + "&api_sig=" + getSignature());
        System.out.println("Token request: " + getRequest());

        zahtevOdgovorGET(getClient(), "token");
    }

    private void zahtevOdgovorGET(HttpClient client, String tagName) throws IOException, ParserConfigurationException, SAXException {

        setMethod(new GetMethod(getRequest()));

        //slanje GET zahteva
        setStatusCode(client.executeMethod(getMethod()));

        if (getStatusCode() != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod().getStatusLine());
        }
        setRstream(null);

        //dobijanje tela odgovora
        setRstream(getMethod().getResponseBodyAsStream());

        //dobijanje XML odgovora i trazenje frob vrednosti
        setResponse(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getRstream()));

        //proveri da li je token u odgovoru
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
