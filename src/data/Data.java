/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author jelena
 */
public class Data {

    private String key = "64a298d057676a6d7298262797a23440";
    private String secret = "2df7583cb4c1241d";
    private String userid;

    private String requestMethod = "https://api.flickr.com/services/rest/?method=";
    private String requestAuth = "https://www.flickr.com/services/auth/?api_key=";

    private String methodGetFrob = "flickr.auth.getFrob";
    private String methodGetToken = "flickr.auth.getToken";
    private String methodGetPublicPhotos = "flickr.people.getPublicPhotos";
    private String methodFindByUsername = "flickr.people.findByUsername";
    private String methodGeoLocation = "flickr.photos.geo.getLocation";
    private String methodFindByLatLon = "flickr.places.findByLatLon";
    private String methodGetClusters = "flickr.tags.getClusters";
    private String methodGetClusterPhotos = "flickr.tags.getClusterPhotos";
    private String methodSearchPhotos = "flickr.photos.search";
    private String methodGetInfo = "flickr.photos.getInfo";

    public Data() {
    }

    public Data(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getMethodGetFrob() {
        return methodGetFrob;
    }

    public void setMethodGetFrob(String methodGetFrob) {
        this.methodGetFrob = methodGetFrob;
    }

    public String getRequestAuth() {
        return requestAuth;
    }

    public void setRequestAuth(String requestAuth) {
        this.requestAuth = requestAuth;
    }

    public String getMethodGetToken() {
        return methodGetToken;
    }

    public void setMethodGetToken(String methodGetToken) {
        this.methodGetToken = methodGetToken;
    }

    public String getMethodGetPublicPhotos() {
        return methodGetPublicPhotos;
    }

    public void setMethodGetPublicPhotos(String methodGetPublicPhotos) {
        this.methodGetPublicPhotos = methodGetPublicPhotos;
    }

    public String getMethodFindByUsername() {
        return methodFindByUsername;
    }

    public void setMethodFindByUsername(String methodFindByUsername) {
        this.methodFindByUsername = methodFindByUsername;
    }

    public String getMethodGeoLocation() {
        return methodGeoLocation;
    }

    public void setMethodGeoLocation(String methodGeoLocation) {
        this.methodGeoLocation = methodGeoLocation;
    }

    public String getMethodFindByLatLon() {
        return methodFindByLatLon;
    }

    public void setMethodFindByLatLon(String methodFindByLatLon) {
        this.methodFindByLatLon = methodFindByLatLon;
    }

    public String getMethodGetClusters() {
        return methodGetClusters;
    }

    public void setMethodGetClusters(String methodGetClusters) {
        this.methodGetClusters = methodGetClusters;
    }

    public String getMethodGetClusterPhotos() {
        return methodGetClusterPhotos;
    }

    public void setMethodGetClusterPhotos(String methodGetClusterPhotos) {
        this.methodGetClusterPhotos = methodGetClusterPhotos;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMethodSearchPhotos() {
        return methodSearchPhotos;
    }

    public void setMethodSearchPhotos(String methodSearchPhotos) {
        this.methodSearchPhotos = methodSearchPhotos;
    }

    public String getMethodGetInfo() {
        return methodGetInfo;
    }

    public void setMethodGetInfo(String methodGetInfo) {
        this.methodGetInfo = methodGetInfo;
    }

}
