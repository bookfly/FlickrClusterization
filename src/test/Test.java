package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import data.PhotoJsonSerializer;
import function.GettingClusters;
import function.GettingLocation;
import function.GettingPhotos;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import photo.Photo;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, JSONException {

        GettingClusters gc = new GettingClusters();
        List<String> tagovi = new ArrayList<>();
        tagovi = gc.getClusters("shark");
        GettingLocation gl = new GettingLocation();
        JsonArray jsonArray = null;
        FileWriter writer = new FileWriter("photos.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (String string : tagovi) {
            List<Photo> listOfPhotos = gc.getClusterPhotos("shark", string);
            gl.setLocations(listOfPhotos);
            jsonArray = PhotoJsonSerializer.serializePhotos(listOfPhotos);

            for (JsonElement jsonElement : jsonArray) {
                Photo photo = gson.fromJson(jsonElement, Photo.class);
                writer.write(gson.toJson(photo) + "\n");
                writer.flush();
            }

        }
        writer.close();
    }
}
