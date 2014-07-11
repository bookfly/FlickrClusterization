/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import data.PhotoJsonSerializer;
import function.GetPhotoInfo;
import function.SearchPhotos;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.xml.sax.SAXException;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class GettingSharkJson {

    List<Photo> listPhotos = new ArrayList<>();

    public GettingSharkJson() {
    }

    public void getSharkPhotos(String fileName) throws IOException, ParserConfigurationException, SAXException, JSONException {

        SearchPhotos sp = new SearchPhotos();

        listPhotos = sp.searchPhoto("shark", 1);
        listPhotos = sp.searchPhoto("shark", 2);
        listPhotos = sp.searchPhoto("shark", 3);
        listPhotos = sp.searchPhoto("shark", 4);
        listPhotos = sp.searchPhoto("shark", 5);
        listPhotos = sp.searchPhoto("shark", 6);
        listPhotos = sp.searchPhoto("shark", 7);
        listPhotos = sp.searchPhoto("shark", 8);

        GetPhotoInfo gpi = new GetPhotoInfo();
        gpi.getPhotosInfo(listPhotos);

        //write data into json file
        makeSharkJson(listPhotos, fileName);

    }

    public void makeSharkJson(List<Photo> photos, String fileName) throws IOException {

        FileWriter writer = new FileWriter(fileName + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = PhotoJsonSerializer.serializePhotos(photos);

        writer.write(gson.toJson(jsonArray));
        writer.flush();
        writer.close();
    }

}
