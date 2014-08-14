/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class ReadingJson {

    public ReadingJson() {
    }

    public List<Photo> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readPhotoArray(reader);
        } finally {
            reader.close();
        }
    }

    private List<Photo> readPhotoArray(JsonReader reader) throws IOException {
        List<Photo> photos = new ArrayList<Photo>();

        reader.beginArray();
        while (reader.hasNext()) {
            photos.add(readPhoto(reader));
        }
        reader.endArray();
        return photos;
    }

    private Photo readPhoto(JsonReader reader) throws IOException {

        String id = "";
        String userId = "";
        String location = "";
        String title = "";
        String secret = "";
        String server = "";
        double lat = 0;
        double lon = 0;
        int cluster = -1;

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
            } else if (name.equals("userId")) {
                userId = reader.nextString();
            } else if (name.equals("location")) {
                location = reader.nextString();
            } else if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("secret")) {
                secret = reader.nextString();
            } else if (name.equals("server")) {
                server = reader.nextString();
            } else if (name.equals("lon")) {
                lon = reader.nextDouble();
            } else if (name.equals("lat")) {
                lat = reader.nextDouble();
            } else if (name.equals("cluster")) {
                cluster = reader.nextInt();
            }

        }

        reader.endObject();

        //  return new Photo(id, userId, location, title, secret, server);
        return new Photo(id, userId, location, title, secret, server, lon, lat, cluster);
    }

    public void setTitle(Photo photo) {
        if (photo.getTitle().contains("Reef Shark") || photo.getTitle().contains("Reef shark") || photo.getTitle().contains("reef shark") || photo.getTitle().contains("Grey Shark")) {
            photo.setTitle("Reef");
        } else if (photo.getTitle().contains("Tiger Shark") || photo.getTitle().contains("Tiger shark") || photo.getTitle().contains("tiger shark")) {
            photo.setTitle("Tiger");
        } else if (photo.getTitle().contains("White Shark") || photo.getTitle().contains("White shark") || photo.getTitle().contains("white shark") || photo.getTitle().contains("White_shark") || photo.getTitle().contains("white-shark") || photo.getTitle().contains("white_shark") || photo.getTitle().contains("White_Shark") || photo.getTitle().contains("Great white") || photo.getTitle().contains("Great White") || photo.getTitle().contains("GREAT WHITE")) {
            photo.setTitle("White");
        } else if (photo.getTitle().contains("Bull Shark") || photo.getTitle().contains("Bull shark") || photo.getTitle().contains("bull shark")) {
            photo.setTitle("Bull");
        } else if (photo.getTitle().contains("Galapagos Shark") || photo.getTitle().contains("Galapagos shark") || photo.getTitle().contains("galapagos shark")) {
            photo.setTitle("Galapagos");
        } else if (photo.getTitle().contains("Lemon Shark") || photo.getTitle().contains("Lemon shark") || photo.getTitle().contains("lemon shark")) {
            photo.setTitle("Lemon");
        } else if (photo.getTitle().contains("hammerhead") || photo.getTitle().contains("Hammer head") || photo.getTitle().contains("Hammerhead")) {
            photo.setTitle("Hammerhead");
        } else if (photo.getTitle().contains("Grey Nurse") || photo.getTitle().contains("Grey nurse") || photo.getTitle().contains("grey nurse") || photo.getTitle().contains("Sandtiger") || photo.getTitle().contains("Sand Shark")) {
            photo.setTitle("Nurse");
        } else if (photo.getTitle().contains("Blue Shark") || photo.getTitle().contains("Blue shark") || photo.getTitle().contains("blue shark")) {
            photo.setTitle("Blue");
        } else if (photo.getTitle().contains("Whale Shark") || photo.getTitle().contains("Whale shark") || photo.getTitle().contains("whale shark") || photo.getTitle().contains("Wale") || photo.getTitle().contains("whale-shark")) {
            photo.setTitle("Whale");
        } else if (photo.getTitle().contains("Porbeagle shark")) {
            photo.setTitle("Porbeagle");
        } else if (photo.getTitle().contains("Rainbow Shark")) {
            photo.setTitle("Rainbow");
        } else if (photo.getTitle().contains("black tip shark") || photo.getTitle().contains("Blacktip shark") || photo.getTitle().contains("Black Tip") || photo.getTitle().contains("Black tipped")) {
            photo.setTitle("Blacktip");
        } else if (photo.getTitle().contains("Silvertip Shark")) {
            photo.setTitle("Silvertip");
        } else if (photo.getTitle().contains("Whitetip Shark")) {
            photo.setTitle("Whitetip");
        } else if (photo.getTitle().contains("Silky Sharks") || photo.getTitle().contains("Silky shark")) {
            photo.setTitle("Silky");
        } else if (photo.getTitle().contains("MAKO SHARK") || photo.getTitle().contains("Mako_Shark") || photo.getTitle().contains("Mako Shark")) {
            photo.setTitle("Mako");
        } else if (photo.getTitle().contains("Sandbar Shark") || photo.getTitle().contains("Brown Shark")) {
            photo.setTitle("Sandbar");
        } else if (photo.getTitle().contains("Dusky Whaler Shark") || photo.getTitle().contains("greys")) {
            photo.setTitle("Gray");
        } else if (photo.getTitle().contains("Basking Shark")) {
            photo.setTitle("Basking");
        } else if (photo.getTitle().contains("Cow Shark")) {
            photo.setTitle("Cow");
        } else if (photo.getTitle().contains("Zebra Shark")) {
            photo.setTitle("Zebra");
        } else if (photo.getTitle().contains("Thresher Shark")) {
            photo.setTitle("Thresher");
        } else {
            photo.setTitle("Shark");
        }

    }

}
