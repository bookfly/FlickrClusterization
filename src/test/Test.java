package test;

import com.google.gson.JsonArray;
import data.PhotoJsonSerializer;
import function.SettingCountryGeo;
import java.io.FileInputStream;
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
         //gsj.getSharkPhotos("sharks");

        //create list of photos
        //change photos title
        List<Photo> listPhoto = new ArrayList<>();
        ChangingTitle ct = new ChangingTitle();
        listPhoto = ct.changeTitle("data/sharks");
        System.out.println("Size of photo list: " + listPhoto.size());

        LocationCounter lc = new LocationCounter();
        List<Photo> withLoc = lc.countWithLoc(listPhoto);
        System.out.println("Number of photos with location: " + withLoc.size());

        //serialize photos
        JsonArray ja = PhotoJsonSerializer.serializePhotos(listPhoto);
        gsj.makeSharkJson(listPhoto, "data/shark");

        ja = PhotoJsonSerializer.serializePhotos(withLoc);
        gsj.makeSharkJson(withLoc, "data/sharkLoc");

        //getting photos from sharkLoc.json
        //getting location with geonames
        List<Photo> listPhotos = new ArrayList<>();
        ReadingJson rj = new ReadingJson();
        String file = "data/sharkLoc.json";
        listPhotos = rj.readJsonStream(new FileInputStream(file));
        SettingCountryGeo scg = new SettingCountryGeo();
        listPhoto = scg.setLocation(listPhoto);

        gsj.makeSharkJson(listPhoto, "data/changedLocation");

        //making arff file
        List<Photo> listPhoto2 = new ArrayList<>();
        String file2 = "data/changedLocation.json";
        listPhoto2 = rj.readJsonStream(new FileInputStream(file2));

        CreateArff ca = new CreateArff();
        ca.general(listPhoto);
*/
        ReadArff ra = new ReadArff();
        ra.readArff("data/proba", 4);

    }

}
