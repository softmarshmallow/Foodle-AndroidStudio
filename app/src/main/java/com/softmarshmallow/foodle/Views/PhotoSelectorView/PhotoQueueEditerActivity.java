package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoQueueEditerActivity extends AppCompatActivity {


        @BindView(R.id.containerViewPager)
        NonSwipeableViewPager containerViewPager;


        public static PhotoQueueEditerActivity Instance;
        public BottomSheetDialog mBottomSheetDialog;
        public View sheetView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_photo_queue_editer);
            ButterKnife.bind(this);
            Instance = this;
            initContainerViewPager();

            mBottomSheetDialog = new BottomSheetDialog(this);
            sheetView = this.getLayoutInflater().inflate(R.layout.fragment_photoselecter_bottom, null);
            mBottomSheetDialog.setContentView(sheetView);

        }


        PhotoSelecterFragment photoSelecterFragment = new PhotoSelecterFragment();
        PhotoQueueFirstFragment photoQueueFirstFragment = new PhotoQueueFirstFragment();
        Map<Integer, Fragment> pageMapping = new HashMap<Integer, Fragment>(){
            {
                put(0, photoQueueFirstFragment);
                put(1, photoSelecterFragment);
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


        public void showPhotoEditerFirstView(){
            containerViewPager.setCurrentItem(0);
        }


        public void showPhotoEditerView(){
            containerViewPager.setCurrentItem(1);
        }




}
