package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import data.PhotoJsonSerializer;
import function.GetPhotoInfo;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import function.SearchPhotos;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import photo.Photo;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, JSONException {

        SearchPhotos sp = new SearchPhotos();

        List<Photo> listPhotos = new ArrayList<>();
        listPhotos = sp.searchPhoto("shark", 1);
        listPhotos = sp.searchPhoto("shark", 2);
        listPhotos = sp.searchPhoto("shark", 3);
        listPhotos = sp.searchPhoto("shark", 4);
        listPhotos = sp.searchPhoto("shark", 5);

        GetPhotoInfo gpi = new GetPhotoInfo();
        gpi.getPhotosInfo(listPhotos);

        FileWriter writer = new FileWriter("sharks.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = PhotoJsonSerializer.serializePhotos(listPhotos);

        writer.write(gson.toJson(jsonArray));
        writer.flush();
        writer.close();

    }
}
