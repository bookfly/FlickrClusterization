/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.Functions.toString;
import java.io.IOException;
import java.util.ArrayList;
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
public class GettingClusters extends Functions {

    public GettingClusters() {
    }

    public List<String> getClusters(String stag) throws IOException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodGetClusters() + "&api_key=" + getData().getKey() + "&tag=" + stag + "&format=json");
        System.out.println("GET clusters request: " + getRequest());
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
        JSONArray cluster = jobj.getJSONObject("clusters").getJSONArray("cluster");
        JSONArray jtag = null;
        List<String> listOfTags = new ArrayList<>();
        for (int i = 0; i < cluster.length(); i++) {
            JSONObject jcluster = cluster.getJSONObject(i);
            jtag = jcluster.getJSONArray("tag");
            for (int j = 0; j < jtag.length(); j++) {
                JSONObject tag = jtag.getJSONObject(j);
                listOfTags.add(tag.getString("_content"));
            }
        }
        return listOfTags;
    }

    public List<Photo> getClusterPhotos(String cluster, String stag) throws IOException, JSONException {

        setRequest(getData().getRequestMethod() + getData().getMethodGetClusterPhotos() + "&api_key=" + getData().getKey() + "&tag=" + stag + "&cluster_id=" + cluster + "&format=json");
        System.out.println("GET clusters request: " + getRequest());
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
