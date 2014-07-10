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
public class GetPhotoInfo extends Functions {

    public GetPhotoInfo() {
    }

    public void getPhotosInfo(List<Photo> photos) throws IOException, JSONException, ParserConfigurationException, SAXException {
        for (Photo photo : photos) {
            getPhotoInfo(photo);
        }
    }

    private void getPhotoInfo(Photo photo) throws IOException, ParserConfigurationException, SAXException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodGetInfo() + "&photo_id=" + photo.getId() + "&api_key=" + getData().getKey() + "&format=json");
        System.out.println("GET info request: " + getRequest());

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
        JSONObject owner = jobj.getJSONObject("photo").getJSONObject("owner");

        photo.setLocation(owner.getString("location"));

    }

}
