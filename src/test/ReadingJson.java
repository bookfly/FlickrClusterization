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
            }

        }

        reader.endObject();

        return new Photo(id, userId, location, title, secret, server);
    }

    public void setTitle(Photo photo) {
        if (photo.getTitle().contains("Reef Shark") || photo.getTitle().contains("Reef shark") || photo.getTitle().contains("reef shark")) {
            photo.setTitle("Reef");
        } else if (photo.getTitle().contains("Tiger Shark") || photo.getTitle().contains("Tiger shark") || photo.getTitle().contains("tiger shark")) {
            photo.setTitle("Tiger");
        } else if (photo.getTitle().contains("White Shark") || photo.getTitle().contains("White shark") || photo.getTitle().contains("white shark")) {
            photo.setTitle("White");
        } else if (photo.getTitle().contains("Bull Shark") || photo.getTitle().contains("Bull shark") || photo.getTitle().contains("bull shark")) {
            photo.setTitle("Bull");
        } else if (photo.getTitle().contains("Galapagos Shark") || photo.getTitle().contains("Galapagos shark") || photo.getTitle().contains("galapagos shark")) {
            photo.setTitle("Galapagos");
        } else if (photo.getTitle().contains("Lemon Shark") || photo.getTitle().contains("Lemon shark") || photo.getTitle().contains("lemon shark")) {
            photo.setTitle("Lemon");
        } else if (photo.getTitle().contains("Hammerhead Shark") || photo.getTitle().contains("Hammerhead shark") || photo.getTitle().contains("hammerhead shark")) {
            photo.setTitle("Hammerhead");
        } else if (photo.getTitle().contains("Grey Nurse Shark") || photo.getTitle().contains("Grey nurse shark") || photo.getTitle().contains("grey nurse shark")) {
            photo.setTitle("Nurse");
        } else if (photo.getTitle().contains("Gray Nurse Shark") || photo.getTitle().contains("Gray nurse shark") || photo.getTitle().contains("gray nurse shark")) {
            photo.setTitle("Nurse");
        } else if (photo.getTitle().contains("Blue Shark") || photo.getTitle().contains("Blue shark") || photo.getTitle().contains("blue shark")) {
            photo.setTitle("Blue");
        } else if (photo.getTitle().contains("Whale Shark") || photo.getTitle().contains("Whale shark") || photo.getTitle().contains("whale shark")) {
            photo.setTitle("Whale");
        }
    }

}
