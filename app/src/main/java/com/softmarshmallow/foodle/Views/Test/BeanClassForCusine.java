package com.softmarshmallow.foodle.Views.Test;

/**
 * Created by Wolf Soft on 8/3/2017.
 */

public class BeanClassForCusine
{

    Integer image;
    String price;
    String cuine_name;
    String city;

    public BeanClassForCusine(Integer image, String price, String cuine_name, String city) {
        this.image = image;
        this.price = price;
        this.cuine_name = cuine_name;
        this.city = city;
    }


    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCuine_name() {
        return cuine_name;
    }

    public void setCuine_name(String cuine_name) {
        this.cuine_name = cuine_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
