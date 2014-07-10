/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import photo.Photo;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 *
 * @author jelena
 */
public class CreateArff {

    private FastVector attributes;
    List<String> sharkTypes;
    List<String> locations;
    List<FastVector> fvList;
    List<Attribute> attList;

    public CreateArff() {
        sharkTypes = new ArrayList<>();
        locations = new ArrayList<>();
        fvList = new ArrayList<>();
        attList = new ArrayList<>();
    }

    private void gettingSharkTypes(List<Photo> photos) {
        for (Photo photo : photos) {
            if (!checkAvailable(photo.getTitle(), sharkTypes)) {
                sharkTypes.add(photo.getTitle());
            }
        }
    }

    private void gettingLocations(List<Photo> photos) {
        for (Photo photo : photos) {
            if (!checkAvailable(photo.getLocation(), locations)) {
                locations.add(photo.getLocation());
            }
        }
    }

    private void getTypeValues(String name, List<String> typeVal) {
        FastVector fv = new FastVector(typeVal.size());
        for (String string : typeVal) {
            fv.addElement(string);
        }

        Attribute att = new Attribute(name, fv);
        attList.add(att);
    }

    private void addAttribute(List<Attribute> atts) {
        attributes = new FastVector(atts.size());
        for (Attribute attr : atts) {
            attributes.addElement(attr);
        }
    }

    private void createInstances(List<Photo> photos) throws IOException {

        Instances data = new Instances("TrainingSet", attributes, photos.size());
        Instance[] instances = new Instance[photos.size()];

        for (int i = 0; i < photos.size();) {
            for (int j = 0; j < instances.length; j++) {
                instances[j] = createInstance(photos.get(i).getTitle(), photos.get(i).getLocation());
                i++;
            }
        }

        for (int i = 0; i < instances.length; i++) {
            data.add(instances[i]);
        }

        createArffFile(data, "proba");
    }

    private void createArffFile(Instances data, String fileName) throws IOException {
        ArffSaver arffSaverInstance = new ArffSaver();
        arffSaverInstance.setInstances(data);
        arffSaverInstance.setFile(new File(fileName + ".arff"));
        arffSaverInstance.setDestination(new File(fileName + ".arff"));
        arffSaverInstance.writeBatch();
    }

    public void general(List<Photo> photos) throws IOException {
        gettingSharkTypes(photos);
        gettingLocations(photos);
        getTypeValues("Type", sharkTypes);
        getTypeValues("Location", locations);
        addAttribute(attList);

        createInstances(photos);
    }

    private Instance createInstance(String type, String location) {
        Instance i1 = new Instance(2);
        i1.setValue((Attribute) attributes.elementAt(0), type);
        i1.setValue((Attribute) attributes.elementAt(1), location);
        return i1;
    }

    private boolean checkAvailable(String title, List<String> listStrings) {
        for (String type : listStrings) {
            if (type.equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

}
