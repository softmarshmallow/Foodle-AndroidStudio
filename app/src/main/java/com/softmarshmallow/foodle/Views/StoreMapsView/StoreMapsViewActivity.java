package com.softmarshmallow.foodle.Views.StoreMapsView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
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
import com.softmarshmallow.foodle.Views.Test.TestMapsActivity;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StoreMapsViewActivity extends AppCompatActivity implements OnMapReadyCallback, DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, GoogleMap.OnMarkerClickListener
{
        
        
        private static final String TAG = "StoreMapsViewActivity";
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_maps_view);
                ButterKnife.bind(this);
        
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        
                mapFragment.getMapAsync(this);
        }
        
        private GoogleMap mMap;
        
        @Override
        public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
               
                mMap.setOnMarkerClickListener(this);
        
                initCarousel();
        
                updateStoreDatas();
        }
        
        
        List<StoreContainerModel> storeDatas = new ArrayList<>();
        List<Marker> storeMarkers = new ArrayList<>();
        
        private void loadMarkerIcon(final Marker marker) {
                Glide.with(this).load(R.drawable.placeholder)
                        .asBitmap().fitCenter().override(64, 64).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmap);
                                marker.setIcon(icon);
                        }
                });
        }
        
        
        @BindView(R.id.recyclerView)
        DiscreteScrollView recyclerView;
        
        MapsStoreDisplayBannerAdapter mapsStoreDisplayBannerAdapter;
        void initCarousel(){
//                final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
                mapsStoreDisplayBannerAdapter = new MapsStoreDisplayBannerAdapter(this, storeDatas);
//                recyclerView.setAdapter(mapsStoreDisplayBannerAdapter);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setHasFixedSize(true);
//                recyclerView.addOnScrollListener(new CenterScrollListener());
        
        
        
                recyclerView.setSlideOnFling(true);
                recyclerView.setAdapter(mapsStoreDisplayBannerAdapter);
                recyclerView.addOnItemChangedListener(this);
//                recyclerView.addScrollStateChangeListener(this);
//                recyclerView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
                recyclerView.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
        
//                forecastView.setForecast(forecasts.get(0));
        
        }
        
        void updateStoreDatas(){
                StoreService.GetAllStores(new Consumer<List<StoreContainerModel>>()
                {
                        @Override
                        public void accept(List<StoreContainerModel> storeContainerModels) throws Exception {
                                storeDatas = storeContainerModels;
                                mapsStoreDisplayBannerAdapter.updateStoreDatas(storeDatas);
                                
                                for (StoreContainerModel storeData : storeDatas){
                
                                        LatLng storeLatLng = (storeData.getStoreLatLng());
                
                                        MarkerOptions markerOptions =  new MarkerOptions().position(storeLatLng)
                                                .title(storeData.StoreName)
                                                .snippet(storeData.StoreShortDescription);
                                        Marker marker = mMap.addMarker(markerOptions);
                                        loadMarkerIcon(marker);
                
                                        storeMarkers.add(marker);
                                }
                                
                        }
                }, new Consumer<String>()
                {
                        @Override
                        public void accept(String s) throws Exception {
                                
                        }
                });
        }
        
        @Override
        public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                if (adapterPosition >= 0){
                        StoreContainerModel currentStoreData = storeDatas.get(adapterPosition);
                        Marker currentStoreMarker = storeMarkers.get(adapterPosition);
        
        
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentStoreData.getStoreLatLng(), 15));
        
                }
                Log.d(TAG,  "adapterPosition = " + adapterPosition);
           }
        
        @Override
        public boolean onMarkerClick(Marker marker) {
                int index = storeMarkers.indexOf(marker);
                Log.d(TAG, "index  = " + index);

                recyclerView.smoothScrollToPosition(index);
                // onCurrentItemChanged(null, index);

                return false;
        }
        
        
        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
        
}
