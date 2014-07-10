/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.Functions.toString;
import java.io.IOException;
import java.util.List;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class GettingLocation extends Functions {

    public GettingLocation() {
    }

    public void setLocations(List<Photo> photos) throws IOException, JSONException {
        for (Photo photo : photos) {
            try {
                setLanLon(photo);
            } catch (Exception e) {
                System.out.println("Photo doesn't have location");
            }
        }
    }

    private void setLanLon(Photo photo) throws IOException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodGeoLocation() + "&api_key=" + getData().getKey() + "&photo_id=" + photo.getId() + "&format=json");

        System.out.println("GET location request: " + getRequest());
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
        JSONObject location = jobj.getJSONObject("photo").getJSONObject("location");
        JSONObject loc = jobj.getJSONObject("photo").getJSONObject("location").getJSONObject("country");
        photo.setLocation(loc.getString("_content"));

    }

}
