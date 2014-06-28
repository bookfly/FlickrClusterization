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

        /*    Photo p = new Photo("4087161911");
         GettingLocation gl = new GettingLocation();
         List<Photo> listaSlika = new ArrayList<>();
         listaSlika.add(p);
         gl.setLocations(listaSlika);
         System.out.println("Location: " + p.getLocation() + " Lat: " + p.getLatitude() + " Lon: " + p.getLongitude());
         */
        /*       GettingClusters gc = new GettingClusters();

         List<String> tagovi = new ArrayList<>();
         tagovi = gc.getClusters("shark");
         for (String string : tagovi) {
         System.out.println(string + " ");
         }
         */
        GettingClusters gc = new GettingClusters();
        List<Photo> listOfPhotos = gc.getClusterPhotos("shark", "fish");

        GettingLocation gl = new GettingLocation();
        gl.setLocations(listOfPhotos);

        JsonArray jsonArray = PhotoJsonSerializer.serializePhotos(listOfPhotos);
        FileWriter writer = new FileWriter("slicice.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (JsonElement jsonElement : jsonArray) {
            Photo photo = gson.fromJson(jsonElement, Photo.class);
            writer.write(gson.toJson(photo) + "\n");
            writer.flush();
        }
        writer.close();

    }
}
