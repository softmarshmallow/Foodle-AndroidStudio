package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.softmarshmallow.foodle.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by yuntaeil on 2018. 1. 31..
 */

public class PhotoSelecterFragment extends Fragment {

    PhotoSelectorActivity.PhotoQueueItemAdapter adapter = new PhotoSelectorActivity.PhotoQueueItemAdapter();
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int SelectPhotoByCamera_REQUEST_CODE = 1;
    private static final int SelectPhotoByGallery_REQUEST_CODE = 2;
    private static final int Plus_CODE = 2;
    private static final int Main_CODE = 1;
    private static final int Nomal_CODE = 0;



    @OnClick(R.id.UploadPhotoButton)
    void confirm_button_Click(){
        File[] files = new File[]{};
        File f3=new File(Environment.getExternalStorageDirectory()+"/inpaint/");
        if(!f3.exists())
            f3.mkdirs();
        OutputStream outStream = null;
        int i = 0;
        try {
            for(PhotoSelectorActivity.PhotoQueueItem item: adapter.mItems) {

                File file = new File(Environment.getExternalStorageDirectory() + "/inpaint/"+i+".png");

                outStream = new FileOutputStream(file);
                item.bitmap.compress(Bitmap.CompressFormat.PNG, 85, outStream);
                outStream.close();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                files[i] = file;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PhotoQueueEditerActivity.Instance.SendImage(files);
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();//\
            } else {

                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_queue_editor, container, false);
        ButterKnife.bind(this, view);



        setupRecyclerView();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
        PhotoQueueEditerActivity.Instance.mBottomSheetDialog.hide();
        if (resultCode == RESULT_OK) {
            if (requestCode == SelectPhotoByCamera_REQUEST_CODE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                addPhoto(photo);
            } else if (requestCode == SelectPhotoByGallery_REQUEST_CODE) {
                try {

                    Uri imageUri = data.getData();
                    InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    addPhoto(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void setupRecyclerView(){
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerView);

        Drawable vectorDrawable = ResourcesCompat.getDrawable(this.getResources(),
                R.drawable.plus, null);
        Bitmap myLogo = ((BitmapDrawable) vectorDrawable).getBitmap();

//        Intent intent = getIntent();
//        try{
//            byte[] byteArray = getIntent().getByteArrayExtra("MainBitmap");
//            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            adapter.mItems.add(new PhotoSelectorActivity.PhotoQueueItem().setId(Main_CODE).setBitmap(bitmap));
//        }catch (Exception e){
//            Log.d("", "onCreate: "+e);
//        }
        //TODO:: GET Bundle Code


        adapter.mItems.add(
                new PhotoSelectorActivity.PhotoQueueItem()
                        .setId(Plus_CODE)
                        .setBitmap(myLogo)
        );

        for (File item :PhotoQueueEditerActivity.Instance.GetImage()){
            Bitmap bitmap = BitmapFactory.decodeFile(item.getAbsolutePath());
            addPhoto(bitmap);
        }
        adapter.notifyDataSetChanged();
    }
    public void setupBottomSheet(){
        LinearLayout BottomSheet_Camera = (LinearLayout) PhotoQueueEditerActivity.Instance.sheetView.findViewById(R.id.fragment_history_bottom_sheet_camera);
        BottomSheet_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(getContext(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);
                }else{
                    Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, SelectPhotoByCamera_REQUEST_CODE);
                }
            }
        });
        LinearLayout BottomSheet_Gallery = (LinearLayout) PhotoQueueEditerActivity.Instance.sheetView.findViewById(R.id.fragment_bottomsheet_gallery);
        BottomSheet_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete code here;

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
    }

    public void LoadImageToFirst(Bitmap bitmap){
        adapter.mItems.add(0,new PhotoSelectorActivity.PhotoQueueItem().setBitmap(bitmap).setId(Main_CODE));
        adapter.notifyDataSetChanged();
    }
    public static void selectImage() {
        PhotoQueueEditerActivity.Instance.mBottomSheetDialog.show();
    }

    private void addPhoto(Bitmap bit) {
        adapter.mItems.add(adapter.mItems.size()-1,
                new PhotoSelectorActivity.PhotoQueueItem().setId(Nomal_CODE).setBitmap(bit));
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
