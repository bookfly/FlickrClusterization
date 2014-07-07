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
import function.SettingCountryGeo;
import java.util.ArrayList;
import java.util.List;

import photo.Photo;
import weka.CreateArff;
import weka.ReadArff;

public class Test {
    
    public Test() {
        
    }
    
    public static void main(String[] args) throws Exception {

        /*    CreateArff ca = new CreateArff();
         ca.createNominalAtt();
         */
        /*
         //get photos from flickr
         //put photos into json file
         GettingSharkJson gsj = new GettingSharkJson();
         //gsj.getSharkPhotos("sharks");

         //create list of photos
         //change photos title
         List<Photo> listPhoto = new ArrayList<>();
         ChangingTitle ct = new ChangingTitle();
         listPhoto = ct.changeTitle("sharks");
         System.out.println("Size of photo list: " + listPhoto.size());
        
         LocationCounter lc = new LocationCounter();
         List<Photo> withLoc = lc.countWithLoc(listPhoto);
         System.out.println("Number of photos with location: "+withLoc.size());

         //serialize photos
         JsonArray ja = PhotoJsonSerializer.serializePhotos(listPhoto);
         gsj.makeSharkJson(listPhoto, "shark");
        
         ja = PhotoJsonSerializer.serializePhotos(withLoc);
         gsj.makeSharkJson(withLoc, "sharkLoc");
         */
        /*
         //getting photos from sharkLoc.json
         //getting location with geonames
         List<Photo> listPhoto = new ArrayList<>();
         ReadingJson rj = new ReadingJson();
         String file = "sharkLoc.json";
         listPhoto = rj.readJsonStream(new FileInputStream(file));
         SettingCountryGeo scg = new SettingCountryGeo();
         listPhoto = scg.setLocation(listPhoto);

         GettingSharkJson gsj = new GettingSharkJson();
         gsj.makeSharkJson(listPhoto, "changedLocation");
         */
        //making arff file
     /*   List<Photo> listPhoto = new ArrayList<>();
         ReadingJson rj = new ReadingJson();
         String file = "changedLocation.json";
         listPhoto = rj.readJsonStream(new FileInputStream(file));

         CreateArff ca = new CreateArff();
         ca.general(listPhoto);
         */
        ReadArff ra = new ReadArff();
        ra.readArff("proba");
    }
    
}
