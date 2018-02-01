

package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.softmarshmallow.foodle.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;


public class PhotoSelectorActivity extends AppCompatActivity
{
    PhotoQueueItemAdapter adapter = new PhotoQueueItemAdapter();
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int SelectPhotoByCamera_REQUEST_CODE = 1;
    private static final int SelectPhotoByGallery_REQUEST_CODE = 2;
    private static final int Plus_CODE = 2;
    private static final int Main_CODE = 1;
    private static final int Nomal_CODE = 0;



    static BottomSheetDialog mBottomSheetDialog;
    RecyclerView recyclerView;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mBottomSheetDialog.hide();
        if (resultCode == RESULT_OK) {
            if (requestCode == SelectPhotoByCamera_REQUEST_CODE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                addPhoto(photo);
            } else if (requestCode == SelectPhotoByGallery_REQUEST_CODE) {
                try {

                    Uri imageUri = data.getData();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    addPhoto(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void addPhoto(Bitmap bit) {
        adapter.mItems.add(adapter.mItems.size()-1,
                new PhotoQueueItem().setId(Nomal_CODE).setBitmap(bit));
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();//\
            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_photo_queue_editor);
        mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.fragment_photoselecter_bottom, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout edit = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_camera);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_REQUEST_CODE);
                    // Edit code here;
                }else{
                    Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, SelectPhotoByCamera_REQUEST_CODE);
                }
            }
        });

        LinearLayout delete = (LinearLayout) sheetView.findViewById(R.id.fragment_bottomsheet_gallery);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete code here;

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);

      RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerView);

        Drawable vectorDrawable = ResourcesCompat.getDrawable(this.getResources(),
                R.drawable.plus, null);
        Bitmap myLogo = ((BitmapDrawable) vectorDrawable).getBitmap();

        Intent intent = getIntent();
        try{
            byte[] byteArray = getIntent().getByteArrayExtra("MainBitmap");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            adapter.mItems.add(new PhotoQueueItem().setId(Main_CODE).setBitmap(bitmap));
        }catch (Exception e){
            Log.d("", "onCreate: "+e);
        }
        adapter.mItems.add(
                new PhotoQueueItem()
                        .setId(Plus_CODE)
                        .setBitmap(myLogo)
        );

        adapter.notifyDataSetChanged();
    }




    static class PhotoQueueItem {
        public int id;
        public Bitmap bitmap;


        public PhotoQueueItem setId(int id) {
            this.id = id;
            return this;
        }

        public PhotoQueueItem setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

    }

    static class PhotoQueueItemViewHolder extends AbstractDraggableItemViewHolder {
        TextView textView;
        ImageView imageView;
        RoundedImageView roundedImageView;
        int id;
        public  PhotoQueueItemViewHolder(final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
            imageView = itemView.findViewById(R.id.ImageSelecterImage);
            roundedImageView = itemView.findViewById(R.id.TextBG);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TEST_CLcick", "onClick: "+id);
                    if (id == Plus_CODE)
                        PhotoSelecterFragment.selectImage();
                }
            });
        }

    }


    public static void selectImage() {

        mBottomSheetDialog.show();

    }


    static class PhotoQueueItemAdapter extends RecyclerView.Adapter< PhotoQueueItemViewHolder> implements DraggableItemAdapter< PhotoQueueItemViewHolder> {
        List<PhotoQueueItem> mItems;

        public PhotoQueueItemAdapter() {
            setHasStableIds(true); // this is required for D&D feature.

            mItems = new ArrayList<>();
//            for (int i = 0; i < 20; i++) {
//                mItems.add(new MyItem(i, "Item " + i, ));
//            }
        }
        @Override
        public long getItemId(int position) {
            return mItems.get(position).id; // need to return stable (= not change even after reordered) value
        }

        @Override
        public  PhotoQueueItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_photo_queue, parent, false);
            return new  PhotoQueueItemViewHolder(v);
        }

        @Override
        public void onBindViewHolder( PhotoQueueItemViewHolder holder, int position) {
            PhotoQueueItem item = mItems.get(position);
            holder.imageView.setImageBitmap(item.bitmap);
            holder.id = item.id;
            if(item.id == Main_CODE){
                holder.textView.setVisibility(View.VISIBLE);
                holder.roundedImageView.setVisibility(View.VISIBLE);
            }else{
                holder.textView.setVisibility(View.INVISIBLE);
                holder.roundedImageView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public void onMoveItem(int fromPosition, int toPosition) {
            Log.d("TEST_Click", fromPosition+" _+_ " +toPosition);
            if(toPosition == mItems.size()-1) {
                PhotoQueueItem movedItem = mItems.remove(fromPosition);
                mItems.add(toPosition-1, movedItem);
                return;
            }
            PhotoQueueItem movedItem = mItems.remove(fromPosition);
            mItems.add(toPosition, movedItem);
            this.notifyDataSetChanged();
        }

        @Override
        public boolean onCheckCanStartDrag( PhotoQueueItemViewHolder holder, int position, int x, int y) {
            if(mItems.get(position).id == Plus_CODE)
                return false;
            return true;
        }

        @Override
        public ItemDraggableRange onGetItemDraggableRange( PhotoQueueItemViewHolder holder, int position) {
            return null;
        }

        @Override
        public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
            if (dropPosition == this.mItems.size()-1)
                return false;
            return true;
        }


        @Override
        public void onItemDragStarted(int position) {
        }

        @Override
        public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {

            for (PhotoQueueItem item:mItems) {
                if(item.id !=Plus_CODE)
                    item.id = Nomal_CODE;
            }
            mItems.get(0).id = Main_CODE;
        }
    }

}
