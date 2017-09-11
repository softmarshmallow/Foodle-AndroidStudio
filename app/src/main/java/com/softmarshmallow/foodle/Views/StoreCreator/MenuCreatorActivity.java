package com.softmarshmallow.foodle.Views.StoreCreator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.MenuService;

import java.util.List;
// import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuCreatorActivity extends AppCompatActivity
{
        
        private static final String TAG = "MenuCreatorActivity";
        MenuModel MenuData = new MenuModel();
        
        
        //region
        @NotEmpty
        @BindView(R.id.menuNameEditText)
        EditText menuNameEditText;
        
        @NotEmpty
        @BindView(R.id.menuShortDescriptionEditText)
        EditText menuShortDescriptionEditText;
        
        
        @BindView(R.id.menuMainPhotoImageView)
        ImageView menuMainPhotoImageView;
        boolean isMenuPhotoSetted;
        
        @NotEmpty
        @BindView(R.id.menuPriceEditText)
        EditText menuPriceEditText;
        
        @NotEmpty
        @BindView(R.id.menuCaloriesEditText)
        EditText menuCaloriesEditText;
        //endregion
        
        
        Gson gson = new Gson();
        public static final int selectMenuPhotoRequestCode = 478;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_creator);
                
                ButterKnife.bind(this);
                
                imagePicker = new ImagePicker(MenuCreatorActivity.this);
        }
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                        if (requestCode == selectMenuPhotoRequestCode) {
                                menuMainPhotoImageView.setImageURI(data.getData());
                                MenuData.MenuMainLocalPhotoUri = data.getData().toString();
                                isMenuPhotoSetted = true;
                        }
                        
                        if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                                imagePicker.submit(data);
                                // // FIXME: 25/08/2017
                                Log.d(TAG, "data.getData() : " + data.getData()
                                        .toString());
                                menuMainPhotoImageView.setImageURI(data.getData());
                        }
                }
        }
        
        ImagePicker imagePicker;
        
        @OnClick(R.id.menuMainPhotoImageView)
        void OnMenuMainPhotoImageViewClick() {
                Log.d(TAG, "OnMenuMainPhotoImageViewClick");
                StartMenuPhotoPicker();
                /*
                imagePicker.shouldGenerateThumbnails(true);
                imagePicker.setImagePickerCallback(
                        new ImagePickerCallback()
                        {
                                @Override
                                public void onImagesChosen(List<ChosenImage> images) {
                                        
                                }
                                
                                @Override
                                public void onError(String message) {
                                        // Do error handling
                                        Log.e(TAG, message);
                                }
                        }
                );
                imagePicker.pickImage();*/
        }
        
        
        void StartMenuPhotoPicker() {
                
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                
                
                startActivityForResult(
                        Intent.createChooser(
                                imageIntent, "Choose Menu Image"
                        ),
                        selectMenuPhotoRequestCode
                );
        }
        
        
        public static final String ResultKey = "MenuCreateResult";
        
        @OnClick(R.id.createMenuButton)
        void OnCreateMenuButtonClick() {
                AttemptCreateMenu();
        }
        
        void AttemptCreateMenu() {
                Validator validator = new Validator(this);
                validator.setValidationListener(new Validator.ValidationListener()
                {
                        @Override
                        public void onValidationSucceeded() {
                                if (AdditionalValidation()){
                                        CreateMenu();
                                }
                        }
                        
                        @Override
                        public void onValidationFailed(List<ValidationError> errors) {
                                for (ValidationError error : errors) {
                                        View view = error.getView();
                                        String message = error.getCollatedErrorMessage(
                                                getApplicationContext());
                                        
                                        // Display error messages ;)
                                        if (view instanceof EditText) {
                                                ((EditText) view).setError(message);
                                        } else {
                                                Toast.makeText(getApplicationContext(), message,
                                                        Toast.LENGTH_LONG)
                                                        .show();
                                        }
                                }
                        }
                });
                validator.validate();
                
        }
        
        boolean AdditionalValidation(){
                if (!isMenuPhotoSetted){
                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("오류")
                                .setContentText("사진을 선택해 주세요.")
                                .show();
                        return false;
                }
                return true;
        }
        
        void CreateMenu() {
                
                MenuData.MenuName = menuNameEditText.getText()
                        .toString();
                MenuData.MenuShortDescription = menuShortDescriptionEditText.getText()
                        .toString();
                MenuData.MenuPrice = Integer.valueOf(menuPriceEditText.getText()
                        .toString());


                // Show progress dialog
                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();

                new MenuService.MenuCreator(
                        MenuData,
                        StoreCreatorActivity.CreatingStoreId,
                        new Consumer<MenuModel>()
                        {
                                @Override
                                public void accept(MenuModel menuModel) {
                                        pDialog.dismiss();
                                        Log.d(TAG, "New Menu Created");
                                        Intent intent = new Intent();
                                        intent.putExtra(ResultKey, gson.toJson(MenuData));
                                        setResult(RESULT_OK, intent);
                                        finish();
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) {
                                        pDialog.dismiss();
                                        setResult(RESULT_CANCELED);
                                        finish();
                                }
                        }
                ).CreateMenu();


                /*
                MenuService.UploadPhotoAndCreateMenu(MenuData, StoreCreatorActivity.CreatingStoreId
                        , new Consumer<MenuModel>()
                        {
                                @Override
                                public void accept(MenuModel menuModel) {
                                        Log.d(TAG, "New Menu Created");
                                        Intent intent = new Intent();
                                        intent.putExtra(ResultKey, gson.toJson(MenuData));
                                        setResult(RESULT_OK, intent);
                                        finish();
                                }
                        }, new Consumer<DatabaseError>()
                        {
                                @Override
                                public void accept(DatabaseError databaseError) {
                                        setResult(RESULT_CANCELED);
                                        finish();
                                }
                        });
                
                */
        }


        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
        
}
