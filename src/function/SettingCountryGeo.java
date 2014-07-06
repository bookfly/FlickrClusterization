/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

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
public class SettingCountryGeo extends Functions {

    public SettingCountryGeo() {
    }

    public List<Photo> setLocation(List<Photo> photos) throws IOException, JSONException {
        for (Photo photo : photos) {
            setCountry(customise(photo.getLocation()), photo);
        }
        return photos;
    }

    private void setCountry(String location, Photo photo) throws IOException, JSONException {

        String url = "http://api.geonames.org/searchJSON?q=" + location + "&maxRows=1&username=jelena_tabas";

        setRequest(url);

        //    System.out.println("GET geo location request: " + getRequest());
        setMethod(new GetMethod(getRequest()));

        setStatusCode(getClient().executeMethod(getMethod()));

        if (getStatusCode() != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod().getStatusLine());
        }
        setRstream(null);
        setRstream(getMethod().getResponseBodyAsStream());

        String jstr = toString(getRstream());
        JSONObject jobj = new JSONObject(jstr);
        //System.out.println(jobj.get("geonames"));
        JSONArray geonames = jobj.getJSONArray("geonames");

        for (int i = 0; i < geonames.length(); i++) {
            JSONObject jphoto = geonames.getJSONObject(i);
            //     System.out.println("First loc: "+photo.getLocation());
            photo.setLocation(jphoto.getString("countryName"));
            //    System.out.println("New loc: "+photo.getLocation());
        }

    }

    private String customise(String location) {
        return location.replace(" ", "+");

    }

}
