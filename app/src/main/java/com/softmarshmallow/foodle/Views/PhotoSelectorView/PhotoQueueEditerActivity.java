package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.FoodleApp;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Test.SeverTest;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
            showPhotoEditerFirstView();
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
            photoQueueFirstFragment.setupBottomSheet();
        }


        public void showPhotoEditerView(){
            containerViewPager.setCurrentItem(1);
            photoSelecterFragment.setupBottomSheet();

        }


    public void post() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(FoodleApp.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SeverTest retrofitService = retrofit.create(SeverTest.class);

        Call<PhotofitRepo> call = retrofitService.getPost("POST /photo");
        call.enqueue(new Callback<PhotofitRepo>() {
            @Override
            public void onResponse(Call<PhotofitRepo> call, Response<PhotofitRepo> response) {
                PhotofitRepo repo = response.body();
                //Toast.makeText(this, repo+" 라고 서버가 말햇다",Toast.LENGTH_LONG).show();
                Log.d("Sever","onResponse: "+repo+"\n"+response);

            }

            @Override
            public void onFailure(Call<PhotofitRepo> call, Throwable t) {
                Log.d("Sever","onResponse: Fail");

            }
        });
    }




}
