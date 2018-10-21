package com.softmarshmallow.foodle.Views.Test;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.StoreService;

import java.util.List;

//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import io.reactivex.functions.Consumer;

public class TestMapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    
    private GoogleMap mMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    
    
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney)
            .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        
        loadData();
    }
    
    void loadData() {
        
        StoreService.GetAllStores(
            new Consumer<List<StoreContainerModel>>()
            {
                @Override
                public void accept(List<StoreContainerModel> storeContainerModels) throws Exception {
                    for (StoreContainerModel storeData : storeContainerModels) {
                        LatLng storeLatLng = (storeData.getStoreLatLng());
                        
                        MarkerOptions markerOptions = new MarkerOptions().position(storeLatLng)
                            .title(storeData.StoreName)
                            .snippet(storeData.StoreShortDescription);
                        Marker marker = mMap.addMarker(markerOptions);
                        
                        loadMarkerIcon(marker, storeData.getMainStorePhotoUrl());
                        
                    }
                    
                }
            }, new Consumer<String>()
            {
                @Override
                public void accept(String s) throws Exception {
                    new SweetAlertDialog(TestMapsActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(s)
                        .show();
                }
            }
        );
    }
    
    private void loadMarkerIcon(final Marker marker, String imageUrl) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .apply(new RequestOptions().fitCenter()
                .override(500, 500))
            .into(new SimpleTarget<Bitmap>()
            {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resource);
                    marker.setIcon(icon);
                }
            });
    }
    
    void initBannerStoreDisplayer() {
    
    }
    
    
}
