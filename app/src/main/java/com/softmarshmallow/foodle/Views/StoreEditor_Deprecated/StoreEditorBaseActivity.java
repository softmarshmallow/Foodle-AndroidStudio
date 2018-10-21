package com.softmarshmallow.foodle.Views.StoreEditor_Deprecated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softmarshmallow.foodle.Helpers.StoreExtraContactsBuilder;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreTransferBaseModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreUploadBundleModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class StoreEditorBaseActivity extends AppCompatActivity
{
        
        private static final String TAG = "StoreEditor";
        
        
        @BindView(R.id.storePhotoImageView)
        ImageView storePhotoImageView;
        
        @NotEmpty
        @BindView(R.id.saveTextButton)
        TextView saveTextButton;
        
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        
        @NotEmpty
        @BindView(R.id.storeNameEditText)
        EditText storeNameEditText;
        
        @NotEmpty
        @BindView(R.id.storeShortDescriptionEditText)
        EditText storeShortDescriptionEditText;
        
        @NotEmpty
        @BindView(R.id.storeFullDescriptionEditText)
        EditText storeFullDescriptionEditText;
        
        @BindView(R.id.selectStoreLocationButton)
        FancyButton selectStoreLocationButton;
        
        @NotEmpty
        @BindView(R.id.storeAddressEditText)
        EditText storeAddressEditText;
        
        @NotEmpty
        @BindView(R.id.PhoneNumberEditText)
        EditText phoneNumberEditText;
        
        @NotEmpty
        @BindView(R.id.facebookUrlEditText)
        EditText facebookUrlEditText;
        
        @NotEmpty
        @BindView(R.id.instagramUrlEditText)
        EditText instagramUrlEditText;
        
        @BindView(R.id.saveButton)
        FancyButton saveButton;
        
        
        // // FIXME: 9/14/17 add Transferdata field
        static StoreUploadBundleModel StoreUploadBundleData = new StoreUploadBundleModel();
        StoreTransferBaseModel getStoreUploadData (){
                return StoreUploadBundleData.storeTransferBaseModel;
        }
        static List<MenuModel> StoreMenuDatasToUpdate = new ArrayList<>();
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_editor);
                ButterKnife.bind(this);
                
        }
        
        
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                Log.d(TAG, "requestCode : " + requestCode);
                
                if (resultCode == Activity.RESULT_OK) {
                        if (requestCode == selectStorePhotoRequestCode) {
                                storePhotoImageView.setImageURI(data.getData());
                                StoreUploadBundleData.StorePhotoLocalUris.add(data.getData()
                                        .toString());
                                isStorePhotoSetted = true;
                        } else if (requestCode == chooseStoreLocationRequestCode) {
                                Place place = PlacePicker.getPlace(data, this);
                                SetStoreLocationData(place.getLatLng(), place.getAddress()
                                        .toString());
                                isStoreLocationSetted = true;
                        }
                }
        }
        
        
        //region StorePhoto
        private static final int selectStorePhotoRequestCode = 1001;
        boolean isStorePhotoSetted = false;
        
        @OnClick(R.id.selectStorePhotoButton)
        void OnStorePhotoImageViewClick() {
                StartStorePhotoPicker();
        }
        
        void StartStorePhotoPicker() {
                
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                
                startActivityForResult(
                        Intent.createChooser(
                                imageIntent, "Choose Store Image"
                        ),
                        selectStorePhotoRequestCode
                );
        }
        //endregion
        
        
        //region ChooseLocation
        final static int chooseStoreLocationRequestCode = 2001;
        boolean isStoreLocationSetted = false;
        
        @OnClick(R.id.selectStoreLocationButton)
        void OnStoreChooseLocationButtonClicked() {
                OpenPlacePicker();
        }
        
        void OpenPlacePicker() {
                
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                
                try {
                        startActivityForResult(builder.build(this),
                                chooseStoreLocationRequestCode);
                }
                catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                }
        }
        
        
        LatLng storeLatLng;
        String storeAddress;
        
        public void SetStoreLocationData(LatLng storeLatLng, String storeAddress) {
                this.storeLatLng = storeLatLng;
                this.storeAddress = storeAddress;
                selectStoreLocationButton.setText("위치 : " + storeLatLng.toString());
                storeAddressEditText.setText(storeAddress);
        }
        //endregion
        
        
        //region StoreExtraContacts
        String getStoreExtraContacs(){
                String extraContacts = StoreExtraContactsBuilder.BuildExtraContacts(
                        new HashMap<String, String>()
                        {
                                {
                                        put(StoreExtraContactsBuilder.facebookKey, facebookUrlEditText.getText().toString());
                                        put(StoreExtraContactsBuilder.instagramKey, instagramUrlEditText.getText().toString());
                                }
                        }
                );
                return extraContacts;
                
        }
        
        //endregion
        
        
        // region Menus
        @OnClick(R.id.endterEditMenuPageButton)
        void OnEndterEditMenuPageButtonClick(){
        }
        //endregion
        
        
        
        
        @OnClick(R.id.saveButton)
        void OnSaveButtonClick(){
                ValidateFormAndContinue();
        }
        
        
        void ValidateFormAndContinue(){
                Validator validator  = new Validator(this);
                validator.setValidationListener(new Validator.ValidationListener()
                {
                        @Override
                        public void onValidationSucceeded() {
                                AdditionalValidationAndCreateStore();
                        }
                        
                        @Override
                        public void onValidationFailed(List<ValidationError> errors) {
                                for (ValidationError error : errors) {
                                        View view = error.getView();
                                        String message = error.getCollatedErrorMessage(StoreEditorBaseActivity.this);
                                        
                                        // Display error messages
                                        if (view instanceof EditText) {
                                                ((EditText) view).setError(message);
                                        } else {
                                                Toast.makeText(StoreEditorBaseActivity.this, message, Toast.LENGTH_LONG).show();
                                        }
                                }
                        }
                });
                validator.validate();
        }
        
        void AdditionalValidationAndCreateStore(){
                boolean isValidationFailed = false;
                String errorMessage = null;
                View viewToFocus = null;
                
                if(!isStoreLocationSetted){
                        isValidationFailed = true;
                        errorMessage = "위치를 설정해 주세요.";
                        viewToFocus = selectStoreLocationButton;
                }
                
                if (!isStorePhotoSetted){
                        isValidationFailed = true;
                        errorMessage = "사진을 선택해 주세요.";
                        viewToFocus = storePhotoImageView;
                }
                
                if (isValidationFailed){
                        viewToFocus.requestFocus();
                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("오류")
                                .setContentText(errorMessage)
                                .show();
                        
                }else {
                        
                        BuildData();
        
                        PerformCRUDAction();
                }
        }
        
        
        protected void PerformCRUDAction(){}
        
        void BuildData(){
                StoreTransferBaseModel StoreDataToUpdate = StoreUploadBundleData.storeTransferBaseModel;
                StoreDataToUpdate.setStoreOwnerId(FirebaseUserService.GetUserUID());
                StoreDataToUpdate.setStoreName(storeNameEditText.getText().toString());
                StoreDataToUpdate.setStoreFullDescription(storeFullDescriptionEditText.getText().toString());
                
                StoreDataToUpdate.setStoreLocation(storeLatLng.latitude + ", " + storeLatLng.longitude);
                StoreDataToUpdate.setStoreAddress(storeAddress);
                StoreDataToUpdate.setStorePhoneNumber(phoneNumberEditText.getText().toString());
                StoreDataToUpdate.setStoreExtraContacts(getStoreExtraContacs());
        }
        
}
