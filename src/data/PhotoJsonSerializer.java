package data;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import photo.Photo;

public class PhotoJsonSerializer {

    public static JsonArray serializePhotos(List<Photo> photos) {
        JsonArray photosArray = new JsonArray();

        for (int i = 0; i < photos.size(); i++) {
            Photo p = photos.get(i);

            JsonObject photoJson = new JsonObject();
            photoJson.addProperty("id", p.getId());
            photoJson.addProperty("userId", p.getUserId());
            photoJson.addProperty("secret", p.getSecret());
            photoJson.addProperty("server", p.getServer());
            photoJson.addProperty("title", p.getTitle());
            photoJson.addProperty("location", p.getLocation());
            photoJson.addProperty("lon", p.getLon());
            photoJson.addProperty("lat", p.getLat());
            photoJson.addProperty("cluster", p.getCluster());
            photosArray.add(photoJson);
        }

        return photosArray;
    }
}
