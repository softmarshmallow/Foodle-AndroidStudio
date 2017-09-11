package com.softmarshmallow.foodle.Views.Shared;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.softmarshmallow.foodle.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;

public class ImageChooserActivity extends AppCompatActivity
{

        private static final String TAG = "ImageChooserActivity";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_image_chooser);

                ButterKnife.bind(this);

                StartImagePicker(chooseImagesRequestCode);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                Log.d(TAG, "requestCode : " + requestCode);

                if (resultCode == RESULT_OK)
                {
                        if (requestCode == chooseImagesRequestCode)
                        {
                                // storeIconImageView.SetImageURI(data.Data);
                                Log.d(TAG, data.getData().toString());
                                //storeIconImageByteArray = URIToByteArray (data.Data);
                        }

                }


                //if (resultCode == Result.Ok)
                //{
                //	// Get the Image from data

                //	String[] filePathColumn = { MediaStore.Images.Media.};
                //	imagesEncodedList = new ArrayList<String>();
                //	if (data.getData() != null)
                //	{

                //		Uri mImageUri = data.getData();

                //		// Get the cursor
                //		Cursor cursor = getContentResolver().query(mImageUri,
                //			    filePathColumn, null, null, null);
                //		// Move to first row
                //		cursor.moveToFirst();

                //		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //		imageEncoded = cursor.getString(columnIndex);
                //		cursor.close();

                //	}
                //	else
                //	{
                //		if (data.getClipData() != null)
                //		{
                //			ClipData mClipData = data.getClipData();
                //			ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                //			for (int i = 0; i < mClipData.getItemCount(); i++)
                //			{

                //				ClipData.Item item = mClipData.getItemAt(i);
                //				Uri uri = item.getUri();
                //				mArrayUri.add(uri);
                //				// Get the cursor
                //				Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                //				// Move to first row
                //				cursor.moveToFirst();

                //				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //				imageEncoded = cursor.getString(columnIndex);
                //				imagesEncodedList.add(imageEncoded);
                //				cursor.close();

                //			}
                //			Log.Verbose("LOG_TAG", "Selected Images" + mArrayUri.size());
                //		}
                //	}
                //}
                //else
                //{
                //	Toast.MakeText(this, "You haven't picked Image",
                //		       ToastLength.Long).Show();
                //}






        }


        final static int chooseImagesRequestCode = 100001;
        void StartImagePicker(int imageRequestCode)
        {
                String imagePickerTitle = "";
                if (imageRequestCode == chooseImagesRequestCode)
                {
                        imagePickerTitle = "Select images";
                }

                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(imageIntent, imagePickerTitle) , imageRequestCode);
        }




        byte[] URIToByteArray(Uri uri) throws IOException {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                return BitmapToByteArray(bitmap);
        }

        byte[] BitmapToByteArray(Bitmap bitmap) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
        }



}
