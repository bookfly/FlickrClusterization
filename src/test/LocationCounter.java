/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import photo.Photo;

/**
 *
 * @author jelena
 */
public class LocationCounter {

    List<Photo> listWithLoc;
    List<Photo> listWithoutLoc;

    public LocationCounter() {
        listWithLoc = new ArrayList<>();
        listWithoutLoc = new ArrayList<>();
    }

    public List<Photo> countWithLoc(List<Photo> photos) {

        for (Photo photo : photos) {
            if (photo.getLocation().equals("") || photo.getLocation() == null) {
                listWithoutLoc.add(photo);
            } else {
                listWithLoc.add(photo);
            }
        }
        return listWithLoc;
    }

}
