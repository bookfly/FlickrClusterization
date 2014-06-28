/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class GettingPhotos extends Functions {

    public GettingPhotos() {
    }

    public List<Photo> getPhotos(String userid) throws IOException, ParserConfigurationException, SAXException, JSONException {

        getData().setUserid(userid);
        setRequest(getData().getRequestMethod() + getData().getMethodGetPublicPhotos() + "&user_id=" + getData().getUserid() + "&api_key=" + getData().getKey() + "&format=json");
        System.out.println("GET photos request: " + getRequest());

        setMethod(new GetMethod(getRequest()));
        setStatusCode(getClient().executeMethod(getMethod()));

        if (getStatusCode() != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod().getStatusLine());
        }
        setRstream(null);
        setRstream(getMethod().getResponseBodyAsStream());

        String jstr = toString(getRstream());
        jstr = jstr.substring("jsonFlickrApi(".length(), jstr.length() - 1);

        JSONObject jobj = new JSONObject(jstr);
        JSONArray photos = jobj.getJSONObject("photos").getJSONArray("photo");
        for (int i = 0; i < photos.length(); i++) {
            JSONObject jphoto = photos.getJSONObject(i);
            setPhoto(new Photo());
            getPhoto().setId(jphoto.getString("id"));
            getPhoto().setTitle(jphoto.getString("title"));
            getPhoto().setUserId(jphoto.getString("owner"));
            getPhoto().setSecret(jphoto.getString("secret"));
            getPhoto().setServer(jphoto.getString("server"));
            getListPhotos().add(getPhoto());
        }
        return getListPhotos();
    }

    public void writeIntoDocument(List<Photo> listPhoto) {

        try {
            File myFile = new File("photos.txt");
            for (Photo photo : listPhoto) {
                if (myFile.exists()) {
                    FileWriter fw = new FileWriter(myFile.getAbsoluteFile(), true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(photo.getId() + " ");
                    bw.write(photo.getUserId() + " ");
                    bw.write(photo.getUsername() + " ");
                    bw.write(photo.getSecret() + " ");
                    bw.write(photo.getServer() + " ");
                    bw.write(photo.getTitle() + " ");
                    bw.write(photo.getLocation() + " ");
                    bw.write(photo.getLatitude() + " ");
                    bw.write(photo.getLongitude() + "");
                    bw.write("\n");
                    bw.close();
                    System.out.println("Success!");
                } else {
                    FileWriter fw = new FileWriter(myFile.getAbsoluteFile(), true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(photo.getId() + " ");
                    bw.write(photo.getUserId() + " ");
                    bw.write(photo.getUsername() + " ");
                    bw.write(photo.getSecret() + " ");
                    bw.write(photo.getServer() + " ");
                    bw.write(photo.getTitle() + " ");
                    bw.write(photo.getLocation() + " ");
                    bw.write(photo.getLatitude() + " ");
                    bw.write(photo.getLongitude() + "");
                    bw.write("\n");
                    bw.close();
                    System.out.println("Written for the first time!");
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public String getUserId(String username) throws IOException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodFindByUsername() + "&api_key=" + getData().getKey() + "&username=" + username + "&format=json");
        System.out.println("GET userid request: " + getRequest());

        setMethod(new GetMethod(getRequest()));
        setStatusCode(getClient().executeMethod(getMethod()));

        if (getStatusCode() != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod().getStatusLine());
        }
        setRstream(null);
        setRstream(getMethod().getResponseBodyAsStream());

        String jstr = toString(getRstream());
        jstr = jstr.substring("jsonFlickrApi(".length(), jstr.length() - 1);

        JSONObject jobj = new JSONObject(jstr);
        JSONObject user = jobj.getJSONObject("user");

        return (String) user.get("id");
    }

}
