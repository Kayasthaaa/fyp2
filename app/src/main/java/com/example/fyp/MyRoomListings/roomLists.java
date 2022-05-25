package com.example.fyp.MyRoomListings;

public class roomLists {

    private String id;
    private String poster;
    private String posterId;
    private String title;
    private String created;
    private String description;
    private String email;
    private String phone_number;
    private String location;
    private String price;
    private String internet;
    private String parking;
    private String photo1;

    public roomLists(){}

    public roomLists(String id, String poster, String posterId, String title, String created, String description, String email, String phone_number, String location, String price, String internet, String parking, String photo1) {
        this.id = id;
        this.poster = poster;
        this.posterId = posterId;
        this.title = title;
        this.created = created;
        this.description = description;
        this.email = email;
        this.phone_number = phone_number;
        this.location = location;
        this.price = price;
        this.internet = internet;
        this.parking = parking;
        this.photo1 = photo1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
}
