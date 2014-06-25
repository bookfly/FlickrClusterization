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
            photoJson.addProperty("user_id", p.getUserId());
            photoJson.addProperty("secret", p.getSecret());
            photoJson.addProperty("server", p.getServer());
            photoJson.addProperty("title", p.getTitle());

            photosArray.add(photoJson);
        }

        return photosArray;
    }
}
