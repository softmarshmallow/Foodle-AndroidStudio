package com.softmarshmallow.foodle.Models;

import java.io.File;

/**
 * Created by yuntaeil on 2018. 2. 12..
 */

public class StoreUploadRequestModel
{
    public File[] images;
    public String storeName;
    public String storeDescription;
    public String storeTel;
    public String tel;
    //요청에 대한 소유권을 나타내는 것이 아님.
    public boolean IsOwn;
    public double lat;
    public double lon;
    public String locationDescription;

    public StoreUploadRequestModel setImages(File[] images) {
        this.images = images;
        return this;
    }

    public StoreUploadRequestModel setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public StoreUploadRequestModel setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
        return this;
    }

    public StoreUploadRequestModel setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public StoreUploadRequestModel setOwn(boolean own) {
        this.IsOwn = own;
        return this;
    }

    public StoreUploadRequestModel setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
        return this;
    }

    public StoreUploadRequestModel setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public StoreUploadRequestModel setStoreTel(String storeTel) {
        this.storeTel = storeTel;
        return this;
    }

    public StoreUploadRequestModel setTel(String tel) {
        this.tel = tel;
        return this;
    }
}
