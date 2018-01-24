package com.softmarshmallow.foodle.Views.PhotoSelectorView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.softmarshmallow.foodle.R;
import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoSelectorActivity extends AppCompatActivity
{
        
        private static final String TAG = PhotoSelectorActivity.class.getSimpleName();
        
        
        @BindView(R.id.board_view)
        BoardView mBoardView;
        
        ArrayList<Pair<Long, Bitmap>> mItemArray = new ArrayList<Pair<Long, Bitmap>>();
        public PhotoSelectorItemAdapter listAdapter = new PhotoSelectorItemAdapter(mItemArray,
                R.layout.column_item, R.id.card, true);
        
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
                
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_photo_selector_view);
                ButterKnife.bind(this);
                
                
                // get passed image data
                Intent intent = getIntent();
                if (intent != null) {
                        String path = intent.getStringExtra("MainBitmap");
                        Bitmap _bit = BitmapFactory.decodeFile(path);
                        addPhoto(_bit);
                }
                
                
                initBoardView();
                addColumnList();
                selectImage();
                
                
        }
        
        void initBoardView(){
                
                Log.d(TAG, "initBoardView");
        
                mBoardView.setSnapToColumnsWhenScrolling(true);
                mBoardView.setSnapToColumnWhenDragging(true);
                mBoardView.setSnapDragItemToTouch(true);
                mBoardView.setCustomDragItem(new PhotoDragItem(this, R.layout.column_item));
                mBoardView.setSnapToColumnInLandscape(false);
                mBoardView.setColumnSnapPosition(BoardView.ColumnSnapPosition.CENTER);
        
                mBoardView.setBoardListener(new BoardView.BoardListener()
                {
                        @Override
                        public void onItemDragStarted(int column, int row) {
                                Toast.makeText(mBoardView.getContext(),
                                        "Start - column: " + column + " row: " + row,
                                        Toast.LENGTH_SHORT)
                                        .show();
                        }
                
                        @Override
                        public void onItemChangedPosition(int oldColumn, int oldRow, int newColumn, int newRow) {
                                Toast.makeText(mBoardView.getContext(),
                                        "Position changed - column: " + newColumn + " row: " + newRow,
                                        Toast.LENGTH_SHORT)
                                        .show();
                        }
                
                        @Override
                        public void onItemChangedColumn(int i, int i1) {
                        
                        }
                
                        @Override
                        public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
                                if (fromColumn != toColumn || fromRow != toRow) {
                                        Toast.makeText(mBoardView.getContext(),
                                                "End - column: " + toColumn + " row: " + toRow,
                                                Toast.LENGTH_SHORT)
                                                .show();
                                }
                        }
                });
                mBoardView.setBoardCallback(new BoardView.BoardCallback()
                {
                        @Override
                        public boolean canDragItemAtPosition(int column, int dragPosition) {
                                // Add logic here to prevent an item to be dragged
                                return true;
                        }
                
                        @Override
                        public boolean canDropItemAtPosition(int oldColumn, int oldRow, int newColumn, int newRow) {
                                // Add logic here to prevent an item to be dropped
                                return true;
                        }
                });
        
        }
        
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                
                if (resultCode == RESULT_OK) {
                        if (requestCode == 1) {
                                File f = new File(Environment.getExternalStorageDirectory()
                                        .toString());
                                for (File temp : f.listFiles()) {
                                        if (temp.getName()
                                                .equals("temp.jpg")) {
                                                f = temp;
                                                break;
                                        }
                                }
                                try {
                                        Bitmap bitmap;
                                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                                        
                                        bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                                                bitmapOptions);
                                        
                                        // viewImage.setImageBitmap(bitmap);
                                        addPhoto(bitmap);
                                        String path = Environment
                                                .getExternalStorageDirectory()
                                                + File.separator
                                                + "Phoenix" + File.separator + "default";
                                        f.delete();
                                        OutputStream outFile = null;
                                        File file = new File(path, String.valueOf(
                                                System.currentTimeMillis()) + ".jpg");
                                        try {
                                                outFile = new FileOutputStream(file);
                                                bitmap.compress(Bitmap.CompressFormat.JPEG, 85,
                                                        outFile);
                                                outFile.flush();
                                                outFile.close();
                                        }
                                        catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                        }
                                        catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                        catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                                catch (Exception e) {
                                        e.printStackTrace();
                                }
                        } else if (requestCode == 2) {
                                
                                Uri selectedImage = data.getData();
                                String[] filePath = {MediaStore.Images.Media.DATA};
                                Cursor c = getContentResolver().query(selectedImage, filePath, null,
                                        null, null);
                                c.moveToFirst();
                                int columnIndex = c.getColumnIndex(filePath[0]);
                                String picturePath = c.getString(columnIndex);
                                c.close();
                                Bitmap _bit = BitmapFactory.decodeFile(picturePath);
                                Log.d("bitmap check",
                                        "pic_path : " + picturePath + "\nfile_path : " + filePath + "\n bitmap : " + _bit);
                                addPhoto(_bit);
                        }
                }
        }
        
        
        private void addColumnList() {
        
                Drawable vectorDrawable = ResourcesCompat.getDrawable(this.getResources(),
                        R.drawable.home_icon, null);
                Bitmap myLogo = ((BitmapDrawable) vectorDrawable).getBitmap();
                Log.d("LOGO", "addColumnList: " + myLogo);
                mItemArray.add(new Pair<>(1L, myLogo));
                
                mBoardView.addColumnList(listAdapter, null, false)
                        .setLayoutManager(
                                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                                        false));
                listAdapter.notifyDataSetChanged();
                
                
        }
        
        private void addPhoto(Bitmap img) {
                mItemArray.add(0, new Pair<>(1L, img));
                Log.d("", "addPhoto" + img);
                listAdapter.notifyDataSetChanged();
                Log.d("", "addPhoto: " + mItemArray.size());
        }
        
        private void selectImage() {
                
                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
                
                AlertDialog.Builder builder = new AlertDialog.Builder(PhotoSelectorActivity.this);
                builder.setTitle("Add Photo!");
                builder.setItems(options, new DialogInterface.OnClickListener()
                {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Take Photo")) {
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        File f = new File(Environment.getExternalStorageDirectory(),
                                                "temp.jpg");
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                                        startActivityForResult(intent, 1);
                                } else if (options[item].equals("Choose from Gallery")) {
                                        Intent intent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(intent, 2);
                                        
                                } else if (options[item].equals("Cancel")) {
                                        dialog.dismiss();
                                }
                        }
                });
                builder.show();
        }
        
        
        private static class PhotoDragItem extends DragItem
        {
                
                PhotoDragItem(Context context, int layoutId) {
                        super(context, layoutId);
                }
                
                @Override
                public void onBindDragView(View clickedView, View dragView) {
                        BitmapDrawable d = (BitmapDrawable) ((ImageView) clickedView.findViewById(
                                R.id.ImageSelecterImage)).getDrawable();
                        Bitmap b = d.getBitmap();
                        ImageView imageView = (ImageView) dragView.findViewById(
                                R.id.ImageSelecterImage);
                        imageView.setImageBitmap(b);
                        
                        //  CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
//            ((TextView) dragView.findViewById(R.id.text)).setText(text);
                        
                        CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
                        CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));
                        
                        dragCard.setMaxCardElevation(40);
                        //    dragCard.setCardElevation(clickedCard.getCardElevation());
                }
                
                @Override
                public void onMeasureDragView(View clickedView, View dragView) {
                        CardView dragCard = (CardView) dragView;
                        CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));
                        
                        
                        int widthDiff = dragCard.getPaddingLeft() - clickedCard.getPaddingLeft() + dragCard.getPaddingRight() -
                                clickedCard.getPaddingRight();
                        int heightDiff = dragCard.getPaddingTop() - clickedCard.getPaddingTop() + dragCard.getPaddingBottom() -
                                clickedCard.getPaddingBottom();
                        int width = clickedView.getMeasuredWidth() + widthDiff;
                        int height = clickedView.getMeasuredHeight() + heightDiff;
                        dragView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
                        
                        
                        int widthSpec = View.MeasureSpec.makeMeasureSpec(width,
                                View.MeasureSpec.EXACTLY);
                        int heightSpec = View.MeasureSpec.makeMeasureSpec(height,
                                View.MeasureSpec.EXACTLY);
                        dragView.measure(widthSpec, heightSpec);
                }
                
                @Override
                public void onStartDragAnimation(View dragView) {
                        CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
                        ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation",
                                dragCard.getCardElevation(), 40f);
                        anim.setInterpolator(new DecelerateInterpolator());
                        anim.setDuration(ANIMATION_DURATION);
                        anim.start();
                }
                
                @Override
                public void onEndDragAnimation(View dragView) {
                        CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
                        ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation",
                                dragCard.getCardElevation(), 6f);
                        anim.setInterpolator(new DecelerateInterpolator());
                        anim.setDuration(ANIMATION_DURATION);
                        anim.start();
                }
        }
}