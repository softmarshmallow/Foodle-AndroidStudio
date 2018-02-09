package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.ApiController;
import com.softmarshmallow.foodle.Views.Test.SeverTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotoQueueEditerActivity extends AppCompatActivity {
    public String[] filenames;
    public Uri Test;
    PhotoSelecterFragment photoSelecterFragment = new PhotoSelecterFragment();
    PhotoQueueFirstFragment photoQueueFirstFragment = new PhotoQueueFirstFragment();
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
            showPhotoEditerFirstView();
        }
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
            photoQueueFirstFragment.setupBottomSheet();
        }
        public void showPhotoEditerView(){
            containerViewPager.setCurrentItem(1);
            photoSelecterFragment.setupBottomSheet();

        }
        public String SendImage(File file){
            SendImage(new File[] {file});
            return file.getName();
        }
        public void SendImage(File[] files) {

        SeverTest retrofitService = ApiController.getRetrofit().create(SeverTest.class);

        Call<File[]> call = retrofitService.setImage(files);
        call.enqueue(new Callback<File[]>() {
            @Override
            public void onResponse(Call<File[]> call, Response<File[]> response) {
                //Toast.makeText(this, repo+" 라고 서버가 말햇다",Toast.LENGTH_LONG).show();
                Log.d("Sever","Code : "+ response.code()+"\nonResponse: "+response.body()+"\n"+response);

            }

            @Override
            public void onFailure(Call<File[]> call, Throwable t) {
                Log.d("Sever","onResponse: Fail"+call+"\n"+t );

            }
        });
    }
        public void GetImage(){
        SeverTest retrofitService = ApiController.getRetrofit().create(SeverTest.class);
        String[] rtnFiles = new String[]{};
        Call<String[]> call = retrofitService.getImageLink();
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                //Toast.makeText(this, repo+" 라고 서버가 말햇다",Toast.LENGTH_LONG).show();
                //Log.d("Sever","Code : "+ response.code()+"\nonResponse: "+response.body()+"\n"+response);
                PhotoQueueEditerActivity.Instance.photoSelecterFragment.addPhotoonSever(response.body());


            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Log.d("Sever","onResponse: Fail"+call+"\n"+t );

            }

        });

    }
}
