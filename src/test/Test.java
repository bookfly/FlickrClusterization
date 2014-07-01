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

import photo.Photo;
import weka.CreateArff;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws Exception {

/*        List<Photo> listPhoto = new ArrayList<>();

        try {
            
            ChangingTitle ct = new ChangingTitle();
            listPhoto = ct.changeTitle();
            
           JsonArray ja = PhotoJsonSerializer.serializePhotos(listPhoto);
            GettingSharkJson gsj = new GettingSharkJson();
            gsj.makeSharkJson(listPhoto);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        CreateArff ca = new CreateArff();
        ca.createNominalAtt();
    }

}
