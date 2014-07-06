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

    public void setLocation(Photo photo) {
        if (photo.getLocation().contains("Australia")) {
            photo.setLocation("Australia");
        } else if (photo.getLocation().contains("USA")
                || photo.getLocation().contains("US")
                || photo.getLocation().contains("usa")
                || photo.getLocation().contains(" United States of America")) {
            photo.setLocation("USA");
        } else if (photo.getLocation().contains("France")
                || photo.getLocation().contains("france")
                || photo.getLocation().contains("FRANCE")) {
            photo.setLocation("France");
        } else if (photo.getLocation().contains("New Zealand")) {
            photo.setLocation("New Zealand");
        } else if (photo.getLocation().contains("Kuwait") || photo.getLocation().contains("kuwait")) {
            photo.setLocation("Kuwait");
        } else if (photo.getLocation().contains("Hong Kong")) {
            photo.setLocation("Hong Kong");
        } else if (photo.getLocation().contains("Chicago")
                || photo.getLocation().contains("Santa Barbara")
                || photo.getLocation().contains("Seattle")
                || photo.getLocation().contains("Pacific Grove")
                || photo.getLocation().contains("Atlanta")
                || photo.getLocation().contains("Tucson")
                || photo.getLocation().contains("Honolulu")
                || photo.getLocation().contains("Maui")
                || photo.getLocation().contains("California")
                || photo.getLocation().contains("Roaring Spring")
                || photo.getLocation().contains("Maryland")
                || photo.getLocation().contains("Savannah")
                || photo.getLocation().contains("San Francisco")
                || photo.getLocation().contains("Dallas")
                || photo.getLocation().contains("Massachusetts")
                || photo.getLocation().contains("Boston")) {
            photo.setLocation("USA");
        } else if (photo.getLocation().contains("Japan")) {
            photo.setLocation("Japan");
        } else if (photo.getLocation().contains("Canada")
                || photo.getLocation().contains("canada")) {
            photo.setLocation("Canada");
        } else if (photo.getLocation().contains("THAILAND")
                || photo.getLocation().contains("Thailand")) {
            photo.setLocation("Thailand");
        } else if (photo.getLocation().contains("Switzerland")
                || photo.getLocation().contains("Schweiz")) {
            photo.setLocation("Switzerland");
        } else if (photo.getLocation().contains("Korea")) {
            photo.setLocation("Korea");
        } else if (photo.getLocation().contains("UK")) {
            photo.setLocation("UK");
        } else if (photo.getLocation().contains("Maldives")) {
            photo.setLocation("Maldives");
        } else if (photo.getLocation().contains("UK")
                || photo.getLocation().contains("United Kingdom")
                || photo.getLocation().contains("Scotland")
                || photo.getLocation().contains("CORNWALL")
                || photo.getLocation().contains("England")
                || photo.getLocation().contains(" U.K.")
                || photo.getLocation().contains("Britain")) {
            photo.setLocation("UK");
        } else if (photo.getLocation().contains("Taiwan")) {
            photo.setLocation("Taiwan");
        } else if (photo.getLocation().contains("netherlands")
                || photo.getLocation().contains("Netherlands")) {
            photo.setLocation("Netherlands");
        } else if (photo.getLocation().contains("Pakistan")) {
            photo.setLocation("Pakistan");
        } else if (photo.getLocation().contains("Madalena do Pico")
                || photo.getLocation().contains("Portugal")) {
            photo.setLocation("Portual");
        } else if (photo.getLocation().contains("Spain")
                || photo.getLocation().contains("Coruña")
                || photo.getLocation().contains("Palma de Mallorca")) {
            photo.setLocation("Spain");
        } else if (photo.getLocation().contains("Andorra")) {
            photo.setLocation("Andorra");
        } else if (photo.getLocation().contains("Malta")) {
            photo.setLocation("Malta");
        } else if (photo.getLocation().contains("Malaysia")) {
            photo.setLocation("Malaysia");
        } else if (photo.getLocation().contains("Belize")) {
            photo.setLocation("Belize");
        } else if (photo.getLocation().contains("México")
                || photo.getLocation().contains("Mexico")) {
            photo.setLocation("Mexico");
        } else if (photo.getLocation().contains("Italia")) {
            photo.setLocation("Italia");
        } else if (photo.getLocation().contains("Maroc")) {
            photo.setLocation("Maroco");
        } else if (photo.getLocation().contains("Singapore")) {
            photo.setLocation("Singapore");
        } else if (photo.getLocation().contains("Greece")) {
            photo.setLocation("Greece");
        } else if (photo.getLocation().contains("Germany")
                || photo.getLocation().contains("Deutschland")) {
            photo.setLocation("Germany");
        } else if (photo.getLocation().contains("South Africa")) {
            photo.setLocation("South Africa");
        } else if (photo.getLocation().contains("Shanghai")
                || photo.getLocation().contains("China")) {
            photo.setLocation("China");
        } else if (photo.getLocation().contains("Sweden")) {
            photo.setLocation("Sweden");
        } else if (photo.getLocation().contains("Philippines")) {
            photo.setLocation("Philippines");
        } else if (photo.getLocation().contains("South Korea")) {
            photo.setLocation("South Korea");
        } else if (photo.getLocation().contains("Brasil")) {
            photo.setLocation("Brasil");
        } else if (photo.getLocation().contains("Russia")) {
            photo.setLocation("Russia");
        } else if (photo.getLocation().contains("Mozambique")) {
            photo.setLocation("Mozambique");
        } else if (photo.getLocation().contains(" Costa Rica")) {
            photo.setLocation(" Costa Rica");
        } else if (photo.getLocation().contains("Norway")) {
            photo.setLocation("Norway");
        }

    }

}
