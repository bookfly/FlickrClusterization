package test;

import weka.ReadArff;

public class Test {

    public Test() {

    }

    public static void main(String[] args) throws Exception {

        /*    CreateArff ca = new CreateArff();
         ca.createNominalAtt();
         */
        /*
         //get photos from flickr
         //put photos into json file
         GettingSharkJson gsj = new GettingSharkJson();
         //gsj.getSharkPhotos("sharks");

         //create list of photos
         //change photos title
         List<Photo> listPhoto = new ArrayList<>();
         ChangingTitle ct = new ChangingTitle();
         listPhoto = ct.changeTitle("data/sharks");
         System.out.println("Size of photo list: " + listPhoto.size());
        
         LocationCounter lc = new LocationCounter();
         List<Photo> withLoc = lc.countWithLoc(listPhoto);
         System.out.println("Number of photos with location: "+withLoc.size());

         //serialize photos
         JsonArray ja = PhotoJsonSerializer.serializePhotos(listPhoto);
         gsj.makeSharkJson(listPhoto, "data/shark");
        
         ja = PhotoJsonSerializer.serializePhotos(withLoc);
         gsj.makeSharkJson(withLoc, "data/sharkLoc");
         */
        /*
         //getting photos from sharkLoc.json
         //getting location with geonames
         List<Photo> listPhoto = new ArrayList<>();
         ReadingJson rj = new ReadingJson();
         String file = "data/sharkLoc.json";
         listPhoto = rj.readJsonStream(new FileInputStream(file));
         SettingCountryGeo scg = new SettingCountryGeo();
         listPhoto = scg.setLocation(listPhoto);

         GettingSharkJson gsj = new GettingSharkJson();
         gsj.makeSharkJson(listPhoto, "data/changedLocation");
         */
        //making arff file
     /*   List<Photo> listPhoto = new ArrayList<>();
         ReadingJson rj = new ReadingJson();
         String file = "data/changedLocation.json";
         listPhoto = rj.readJsonStream(new FileInputStream(file));

         CreateArff ca = new CreateArff();
         ca.general(listPhoto);
         */
        ReadArff ra = new ReadArff();
        ra.readArff("data/proba", 8);

    }

}
