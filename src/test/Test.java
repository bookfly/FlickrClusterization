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
import photo.Photo;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, JSONException {

        //    Autentikacija a = new Autentikacija();
        //    a.dajForb();
        //     a.logIn();
        //  GettingPhotos ps = new GettingPhotos();
        //    ps.napisiUDokumentSlike(ps.dajSlike());
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("davideluciano")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("benman31")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("Natalie+Bell")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("R.+Francis")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("Steffe")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("Huntcliff")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("buhamdi")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("melfoody")));
        //    ps.napisiUDokumentSlike(ps.dajSlike(ps.dajUserId("alexlegaud")));
        //     PhotoJsonSerializer pjs = new PhotoJsonSerializer();
        //   System.out.println(pjs.serializePhotos(ps.dajSlike(ps.dajUserId("davideluciano"))));
     /*   JsonArray jsonArray = pjs.serializePhotos(ps.dajSlike(ps.dajUserId("davideluciano")));
         FileWriter writer = new FileWriter("slike.json");
         Gson gson = new GsonBuilder().setPrettyPrinting().create();

         for (JsonElement jsonElement : jsonArray) {

         Photo photo = gson.fromJson(jsonElement, Photo.class);
         writer.write(gson.toJson(photo) + "\n");
         }

         writer.close();
         */
        /*     Photo p = new Photo("4087161911");
         GettingLocation gl = new GettingLocation();
         List<Photo> listaSlika = new ArrayList<>();
         listaSlika.add(p);
         gl.setLocations(listaSlika);
         System.out.println("Location: " + p.getLocation() + " Lat: " + p.getLatitude() + " Lon: " + p.getLongitude());
         */
        GettingClusters gc = new GettingClusters();

        List<String> tagovi = new ArrayList<>();
        tagovi = gc.getClusters("shark");
        for (String string : tagovi) {
            System.out.println(string + " ");
        }

    }

}
