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
        /*    CreateArff ca = new CreateArff();
         ca.createNominalAtt();
         */
        /*    ReadingJson rjson = new ReadingJson();
         List<Photo> photos = rjson.readJsonStream(new FileInputStream("sharks.json"));

         LocationCounter lc = new LocationCounter();
         List<Photo> withLoc = lc.countWithLoc(photos);
         System.out.println("No with loc: "+ withLoc.size());
         */
        /*    List<Photo> listPhoto = new ArrayList<>();
         ChangingTitle ct = new ChangingTitle();
         listPhoto = ct.changeTitle();

         JsonArray ja = PhotoJsonSerializer.serializePhotos(listPhoto);
         GettingSharkJson gsj = new GettingSharkJson();
         gsj.makeSharkJson(listPhoto);
         */
        //get photos from flickr
        //put photos into json file
        GettingSharkJson gsj = new GettingSharkJson();
  //      gsj.getSharkPhotos("sharks");

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
        

    }

}
