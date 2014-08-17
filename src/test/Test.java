package test;

import com.google.gson.JsonArray;
import data.PhotoJsonSerializer;
import function.SettingCountryGeo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import photo.Photo;
import weka.CreateArff;
import weka.ReadArff;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws Exception {
/*
        //get photos from flickr
        //put photos into json file
        GettingSharkJson gsj = new GettingSharkJson();
        gsj.getSharkPhotos("data/sharks");

        //getting only those instances with location
        List<Photo> listPhoto = new ArrayList<>();
        ReadingJson rj = new ReadingJson();
        listPhoto = rj.readJsonStream(new FileInputStream("data/sharks.json"));
        LocationCounter lc = new LocationCounter();
        List<Photo> withLoc = lc.countWithLoc(listPhoto);
        System.out.println("Number of photos with location: " + withLoc.size());
        JsonArray ja = PhotoJsonSerializer.serializePhotos(withLoc);
        gsj.makeSharkJson(withLoc, "data/sharkLoc");
        
 
        //create list of photos
        //change photos title
        List<Photo> listPhoto1 = new ArrayList<>();
        ChangingTitle ct = new ChangingTitle();
        listPhoto1 = ct.changeTitle("data/sharkLoc");
        System.out.println("Size of photo list: " + listPhoto1.size());

        //serialize photos
        JsonArray ja1 = PhotoJsonSerializer.serializePhotos(listPhoto1);
        gsj.makeSharkJson(listPhoto1, "data/sharkTitle");

        //getting photos from sharkLoc.json
        //getting location with geonames
        List<Photo> listPhotos = new ArrayList<>();
        ReadingJson rj1 = new ReadingJson();
        listPhotos = rj1.readJsonStream(new FileInputStream("data/sharkTitle.json"));
        SettingCountryGeo scg = new SettingCountryGeo();
        listPhotos = scg.setLocation(listPhotos);

        gsj.makeSharkJson(listPhotos, "data/changedLocation");
     

        //making arff file
        List<Photo> listPhoto2 = new ArrayList<>();
        listPhoto2 = rj1.readJsonStream(new FileInputStream("data/changedLocation.json"));
        CreateArff ca = new CreateArff();
        ca.general(listPhoto2);
*/
   //     ReadArff ra = new ReadArff();

        //changing number of clusters led to a conclusion the best number is 4
/*        for (int i = 1; i < 30; i++) {
            ra.readArff("proba", i, "rez" + i);
        }
*/
   //     ra.readArff("trial", 4, "result");
        
    //     Desktop.getDesktop().browse(new URI("file:///home/jelena/NetBeansProjects/FlickrClusterization%20/src/visualization/sharks.html")); 
          Desktop.getDesktop().browse(new URI("file:///home/jelena/NetBeansProjects/FlickrClusterization%20/src/visualization/new.html")); 
       
    }

}
