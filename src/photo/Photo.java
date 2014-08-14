/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo;

/**
 *
 * @author jelena
 */
public class Photo {

    private String id;
    private String userId;
    private String location;
    private String title;
    private String secret;
    private String server;
    private double lon;
    private double lat;
    private int cluster;

    public Photo(String id) {
        this.id = id;
    }

    public Photo(String id, String userId, String location, String title, String secret, String server, double lon, double lat, int cluster) {
        this.id = id;
        this.userId = userId;
        this.location = location;
        this.title = title;
        this.secret = secret;
        this.server = server;
        this.lon = lon;
        this.lat = lat;
        this.cluster = cluster;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

}
