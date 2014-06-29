/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

import java.util.List;

/**
 *
 * @author jelena
 */
public class Photo {

    private String id;
    private String userId;
 //   private String username;
    private String location;
    private String title;
    private String secret;
    private String server;
//    private double longitude;
 //   private double latitude;

    public Photo(String id) {
        this.id = id;
    }

    public Photo(String id, String userId, String location, String title, String secret, String server) {
        this.id = id;
        this.userId = userId;
        this.location = location;
        this.title = title;
        this.secret = secret;
        this.server = server;
    }

    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

/*    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
*/
}
