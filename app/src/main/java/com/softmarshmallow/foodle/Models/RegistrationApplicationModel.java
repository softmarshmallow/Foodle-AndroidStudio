package com.softmarshmallow.foodle.Models;

import java.io.File;

/**
 * Created by yuntaeil on 2018. 2. 12..
 */

public class RegistrationApplicationModel {
    public File[] images;
    public String storeName;
    public String storeDescription;
    public String storeTel;
    public boolean own;
    public double lat;
    public double lon;
    public String locationDescription;


    public RegistrationApplicationModel setImages(File[] images) {
        this.images = images;
        return this;
    }
    public RegistrationApplicationModel setFile(File[] file){
        this.images = file;
        return this;
    }
    public RegistrationApplicationModel setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public RegistrationApplicationModel setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
        return this;
    }

    public RegistrationApplicationModel setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public RegistrationApplicationModel setOwn(boolean own) {
        this.own = own;
        return this;
    }

    public RegistrationApplicationModel setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
        return this;
    }

    public RegistrationApplicationModel setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }


    public RegistrationApplicationModel setStoreTel(String storeTel) {
        this.storeTel = storeTel;
        return this;
    }
}
