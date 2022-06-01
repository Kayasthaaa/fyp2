package com.example.fyp.RecyclerView;

import android.net.Uri;

public class Lists {
    private String id;
    private String Name;
    private String poster_id;
    private String tle;
    private String crt;
    private String des;
    private String mail;
    private String pnumber;
    private String Address;
    private String prc;
    private String net;
    private String garage;
    private String mimage;

    public Lists(){

    }
    public Lists(String id,String Name, String poster_id,
                 String tle,String crt,String des, String mail, String pnumber,
                 String Address,String prc, String net, String garage, String mimage){
        this.id = id;
        this.Name = Name;
        this.poster_id = poster_id;
        this.tle = tle;
        this.crt = crt;
        this.des = des;
        this.mail = mail;
        this.pnumber = pnumber;
        this.Address = Address;
        this.prc = prc;
        this.net = net;
        this.garage = garage;
        this.mimage = mimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(String poster_id) {
        this.poster_id = poster_id;
    }

    public String getTle() {
        return tle;
    }

    public void setTle(String tle) {
        this.tle = tle;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPrc() {
        return prc;
    }

    public void setPrc(String prc) {
        this.prc = prc;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getMimage() {
        return mimage;
    }

    public void setMimage(String mimage) {
        this.mimage = mimage;
    }
}
