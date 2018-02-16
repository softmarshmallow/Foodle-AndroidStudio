package com.softmarshmallow.foodle.Views.StoreUploadRequestView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by softmarshmallow on 2/15/18.
 */

public class StoreUploadRequestActivity extends AppCompatActivity
{
        
        @BindView(R.id.containerViewPager)
        NonSwipeableViewPager containerViewPager;
        
        
        public static StoreUploadRequestActivity Instance;
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_upload_request);
                ButterKnife.bind(this);
                Instance = this;
                
                initContainerViewPager();
                showUploadRequestFormView();
        }
        StoreUploadRequestViewFragment storeUploadRequestViewFragment = new StoreUploadRequestViewFragment();
        Map<Integer, Fragment> pageMapping = new HashMap<Integer, Fragment>(){
                {
                        put(0,storeUploadRequestViewFragment);
                }
        };
        
        void initContainerViewPager(){
                containerViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
                {
                        @Override
                        public Fragment getItem(int i) {
                                return pageMapping.get(i);
                        }
                        
                        @Override
                        public int getCount() {
                                return pageMapping.size();
                        }
                });
        }
        
        
        public void showUploadRequestFormView(){
                containerViewPager.setCurrentItem(0);
        }
        
       
        
}
