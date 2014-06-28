/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import data.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import photo.Photo;

/**
 *
 * @author jelena
 */
public abstract class Functions {

    private Data data;
    private String sig;
    private String signature;
    private String request;
    private InputStream rstream;
    private HttpClient client;
    private GetMethod method;
    private int statusCode;
    private Document response;
    private String token;
    private Photo photo;
    private List<Photo> listPhotos;

    public Functions() {
        data = new Data();
        rstream = null;
        client = new HttpClient();
        method = null;
        token = null;
        photo = new Photo();
        listPhotos = new ArrayList<>();
    }

    //MD5 metoda za kodiranje
    protected static String MD5(String text) {
        String md5Text = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            md5Text = new BigInteger(1, digest.digest((text).getBytes())).toString(16);
        } catch (Exception e) {
            System.out.println("Error in call to MD5");
        }

        if (md5Text.length() == 31) {
            md5Text = "0" + md5Text;
        }
        return md5Text;
    }

    protected void printFlickrError(Document response) {
        NodeList error = response.getElementsByTagName("err");
        String code = error.item(0).getAttributes().item(0).getTextContent();
        String msg = error.item(0).getAttributes().item(1).getTextContent();
        System.out.println("Flickr request failed with error code " + code + ", " + msg);
    }

    protected static String toString(InputStream in) throws IOException {
        StringWriter out = new StringWriter();
        copy(new InputStreamReader(in), out);
        out.close();
        in.close();
        return out.toString();
    }

    private static int copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[1024];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public InputStream getRstream() {
        return rstream;
    }

    public void setRstream(InputStream rstream) {
        this.rstream = rstream;
    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public GetMethod getMethod() {
        return method;
    }

    public void setMethod(GetMethod method) {
        this.method = method;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Document getResponse() {
        return response;
    }

    public void setResponse(Document response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<Photo> getListPhotos() {
        return listPhotos;
    }

    public void setListPhotos(List<Photo> listPhotos) {
        this.listPhotos = listPhotos;
    }
}
