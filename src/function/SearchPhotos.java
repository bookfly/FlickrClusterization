/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.Functions.toString;
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
public class SearchPhotos extends Functions {

    public SearchPhotos() {
    }

    public List<Photo> searchPhoto(String text, int page) throws IOException, ParserConfigurationException, SAXException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodSearchPhotos() + "&text=" + text + "&sort=relevance" + "&page=" + page + "&api_key=" + getData().getKey() + "&format=json");
        System.out.println("GET search request: " + getRequest());

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

}
