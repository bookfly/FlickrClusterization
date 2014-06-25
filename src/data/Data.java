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

    private String requestMethod = "https://api.flickr.com/services/rest/?method=";
    private String requestAuth = "https://www.flickr.com/services/auth/?api_key=";

    private String methodGetFrob = "flickr.auth.getFrob";
    private String methodGetToken = "flickr.auth.getToken";
    private String methodGetPublicPhotos = "flickr.people.getPublicPhotos";
    private String methodFindByUsername = "flickr.people.findByUsername";

    private String userid = "29096781@N02";
    private String photoId = "14484711641";

    public Data() {
    }

    public Data(String key, String secret, String userid, String photoId) {
        this.key = key;
        this.secret = secret;
        this.userid = userid;
        this.photoId = photoId;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
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

}
