package com.softmarshmallow.foodle.Views.MenuEditor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuEditorBaseActivity extends AppCompatActivity
{
        
        private static final String TAG = "MenuCreator";
        
        
        @BindView(R.id.menuPhotoImageView)
        ImageView menuPhotoImageView;
        
        @BindView(R.id.saveTextButton)
        TextView saveTextButton;
        
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        
        @NotEmpty
        @BindView(R.id.menuNameEditText)
        EditText menuNameEditText;
        
        @NotEmpty
        @BindView(R.id.menuShortDescriptionEditText)
        EditText menuShortDescriptionEditText;
        
        @NotEmpty
        @BindView(R.id.menuPriceEditText)
        EditText menuPriceEditText;
        
        @BindView(R.id.selectMenuPhotoButton)
        FancyButton selectMenuPhotoButton;
        
        @NotEmpty
        @BindView(R.id.menuNutritionInformationEditText)
        EditText menuNutritionInformationEditText;
        
        @BindView(R.id.saveButton)
        FancyButton saveButton;
        
        
        MenuModel MenuData = new MenuModel();
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_editor_base);
                ButterKnife.bind(this);
        
                imagePicker = new ImagePicker(this);
        
        }
        
        
        
        
        
        
        /////
        
        
        
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                        
                        if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                                imagePicker.submit(data);
                                
                                Log.d(TAG, "data.getData() : " + data.getData()
                                        .toString());
                                // menuPhotoImageView.setImageURI(data.getData());
                        }
                }
        }
        
        
        ImagePicker imagePicker;
        boolean isMenuPhotoSetted;
         @OnClick(R.id.selectMenuPhotoButton)
        void OnMenuMainPhotoImageViewClick() {
                Log.d(TAG, "OnMenuMainPhotoImageViewClick");
                 
                imagePicker.shouldGenerateThumbnails(false);
                imagePicker.setImagePickerCallback(
                        new ImagePickerCallback()
                        {
                                @Override
                                public void onImagesChosen(List<ChosenImage> images) {
                                        String imageLocalUri = images.get(0).getQueryUri();
                                        MenuData.MenuMainLocalPhotoUri = imageLocalUri;
                                        Glide.with(MenuEditorBaseActivity.this).load(imageLocalUri).into(menuPhotoImageView);
                                        isMenuPhotoSetted = true;
                                }
                                
                                @Override
                                public void onError(String message) {
                                        // Do error handling
                                        new SweetAlertDialog(MenuEditorBaseActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Error")
                                                .setContentText(message)
                                                .show();
                                        Log.e(TAG, message);
                                }
                        }
                );
                imagePicker.pickImage();
        }
        
        
        public static final String ResultKey = "MenuCreateResult";
        @OnClick(R.id.saveButton)
        void OnSaveMenuButtonClick() {
                AttemptCreateMenu();
        }
        void AttemptCreateMenu() {
                Validator validator = new Validator(this);
                validator.setValidationListener(new Validator.ValidationListener()
                {
                        @Override
                        public void onValidationSucceeded() {
                                if (AdditionalValidation()){
                                        PerformCRUDAction();
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
        
        void BuildData(){
        
                MenuData.MenuName = menuNameEditText.getText()
                        .toString();
                MenuData.MenuShortDescription = menuShortDescriptionEditText.getText()
                        .toString();
                MenuData.MenuPrice = Integer.valueOf(menuPriceEditText.getText()
                        .toString());
                MenuData.MenuNutritionInformation = menuNutritionInformationEditText.getText().toString();
        
        }
        
        protected void PerformCRUDAction() {
                
                // // FIXME: 9/21/17
                SaveMenu();
        }
        
        
        void SaveMenu(){
                Gson gson = new Gson();
                Intent intent = new Intent();
                intent.putExtra(ResultKey, gson.toJson(MenuData));
                setResult(RESULT_OK, intent);
        }
        
        
        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
        
        
        
        
}
