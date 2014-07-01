/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class ChangingTitle {

    List<Photo> listPhoto = new ArrayList<>();

    public ChangingTitle() {
    }

    public List<Photo> changeTitle() throws IOException {

        System.out.println("Reading JSON from a file");
        System.out.println("----------------------------");

        ReadingJson rjson = new ReadingJson();
        List<Photo> photos = rjson.readJsonStream(new FileInputStream("sharks.json"));

        for (Photo photo : photos) {
            String title = photo.getTitle();
            rjson.setTitle(photo);
            listPhoto.add(photo);
        }

        return listPhoto;

    }
}
