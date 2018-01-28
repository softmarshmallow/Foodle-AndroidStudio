package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Test.DragListTest.MinimalDraggableExampleActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

/**
 * Created by yuntaeil on 2018. 1. 24..
 */

public class PhotoSelectorFirstActivity extends AppCompatActivity {
    static BottomSheetDialog mBottomSheetDialog;

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    @BindView(R.id.RightNowButton)
    FancyButton fancyButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selector_first);
        ButterKnife.bind(this);
        mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.fragment_photoselecter_bottom, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout edit = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);
                    // Edit code here;
                }else{
                    Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);

                }
            }
        });

        LinearLayout delete = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete code here;

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });


    }
    @OnClick(R.id.RightNowButton)
    void RightNowClicked(){
        selectImage();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission Accecept", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

    }

    @SuppressLint("Assert")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent_ = new Intent(PhotoSelectorFirstActivity.this, MinimalDraggableExampleActivity.class);
        Bitmap selectedImage = null;
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                selectedImage = (Bitmap) data.getExtras().get("data");
            } else if (requestCode == 2) {

                final Uri imageUri = data.getData();
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                }catch (Exception e){

                }
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            intent_.putExtra("MainBitmap",byteArray);

            startActivity(intent_);
        }
    }

    private void selectImage() {

        mBottomSheetDialog.show();
    }

}

