package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.softmarshmallow.foodle.FoodleApp;
import com.softmarshmallow.foodle.Manifest;
import com.softmarshmallow.foodle.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import mehdi.sakout.fancybuttons.FancyButton;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class PhotoQueueFirstFragment extends Fragment {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int SelectPhotoByCamera_REQUEST_CODE = 1;
    private static final int SelectPhotoByGallery_REQUEST_CODE = 2;

    @OnClick(R.id.RightNowButton)
    void RightNowClicked(){
        selectImage();
    }

    @OnClick(R.id.LaterButton)
    void LaterClicked(){
        //ToDo :: CHange Flagment
        PhotoQueueEditerActivity.Instance.finish();
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "camera permission Accecept", Toast.LENGTH_LONG).show();
            Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
            this.startActivityForResult(cameraIntent, SelectPhotoByGallery_REQUEST_CODE);

        } else {
            Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
        }

    }
    public void setupBottomSheet(){
        LinearLayout BottomSheet_Camera = (LinearLayout) PhotoQueueEditerActivity.Instance.sheetView.findViewById(R.id.fragment_history_bottom_sheet_camera);
        BottomSheet_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(getContext(), android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);
                }else{
                    Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                    cameraIntent.setType("image/*");

                    PhotoQueueEditerActivity.Instance.photoQueueFirstFragment.
                    startActivityForResult(cameraIntent, SelectPhotoByCamera_REQUEST_CODE);
                }
            }
        });
        LinearLayout BottomSheet_Gallery = (LinearLayout) PhotoQueueEditerActivity.Instance.sheetView.findViewById(R.id.fragment_bottomsheet_gallery);
        BottomSheet_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                gallery_intent.setType("image/*");
                PhotoQueueEditerActivity.Instance.photoQueueFirstFragment.startActivityForResult(gallery_intent, SelectPhotoByGallery_REQUEST_CODE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_queue_first, container, false);
        ButterKnife.bind(this, view);

        setupBottomSheet();
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: Is call");
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap selectedImage = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == SelectPhotoByCamera_REQUEST_CODE) {
                selectedImage = (Bitmap) data.getExtras().get("data");
                PhotoQueueEditerActivity.Instance.showPhotoEditerView();
                PhotoQueueEditerActivity.Instance.photoSelecterFragment.LoadImageToFirst(selectedImage);


            } else if (requestCode == SelectPhotoByGallery_REQUEST_CODE) {
               try {
                   Uri imageUri = data.getData();

                   Log.d(TAG, "URI: "+imageUri);
                   PhotoQueueEditerActivity.Instance.Test=imageUri;
                   InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                   selectedImage = BitmapFactory.decodeStream(imageStream);
                   PhotoQueueEditerActivity.Instance.showPhotoEditerView();
                   PhotoQueueEditerActivity.Instance.photoSelecterFragment.LoadImageToFirst(selectedImage);

               }catch (Exception e){
                   Log.d(TAG, "onActivityResult: "+e);
               }
            }
        }




    }
    private void selectImage() {
        PhotoQueueEditerActivity.Instance.mBottomSheetDialog.show();
    }
}
