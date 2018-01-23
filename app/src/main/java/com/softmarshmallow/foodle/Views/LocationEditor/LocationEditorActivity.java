package com.softmarshmallow.foodle.Views.LocationEditor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationEditorActivity extends AppCompatActivity
{
        public static LocationEditorActivity Instance;
        @BindView(R.id.nonSwipeableViewPager)
        NonSwipeableViewPager nonSwipeableViewPager;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_location_editor);
                ButterKnife.bind(this);
                Instance = this;
        
                SetViewPager();
        }
        
        Fragment addressFormsFragment = new AddressFormsFragment();
        Fragment locationChooserFragment = new LocationChooserFragment();
        
        void SetViewPager(){
                nonSwipeableViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
                {
                        @Override
                        public Fragment getItem(int i) {
                                LocationEditorStepsType locationEditorStepsType = LocationEditorStepsType.valueOf(i);
                                
                                switch (locationEditorStepsType){
                                        case AddressForms:
                                                return addressFormsFragment;
                                        case LocationChooser:
                                                return  locationChooserFragment;
                                }
                                return null;
                        }
        
                        @Override
                        public int getCount() {
                                return LocationEditorStepsType.values().length;
                        }
                });
        }
        
        

        public void ShowLocationChooser() {
                nonSwipeableViewPager.setCurrentItem(LocationEditorStepsType.LocationChooser.getValue());
        }
        
        
        public void ShowAddreessForms() {
                nonSwipeableViewPager.setCurrentItem(LocationEditorStepsType.AddressForms.getValue());
        }
        
        
        @Override
        public void onBackPressed() {
                // go to previous step, Not Finish activity
                int currentStepIndex = nonSwipeableViewPager.getCurrentItem();
                if (currentStepIndex <= 0){
                        finish();
                }else {
                        nonSwipeableViewPager.setCurrentItem(currentStepIndex - 1);
                }
        }
        
        enum LocationEditorStepsType{
                AddressForms (0),
                LocationChooser  (1);
        
        
                private int value;
                private static Map map = new HashMap<>();
        
                private LocationEditorStepsType(int value) {
                        this.value = value;
                }
        
                static {
                        for (LocationEditorStepsType pageType : LocationEditorStepsType.values()) {
                                map.put(pageType.value, pageType);
                        }
                }
        
                public static LocationEditorStepsType valueOf(int pageType) {
                        return (LocationEditorStepsType) map.get(pageType);
                }
        
                public int getValue() {
                        return value;
                }
        
        }
}
