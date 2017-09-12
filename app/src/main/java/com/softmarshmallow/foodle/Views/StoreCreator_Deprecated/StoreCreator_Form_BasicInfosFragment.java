package com.softmarshmallow.foodle.Views.StoreCreator_Deprecated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.mobsandgeeks.saripaar.annotation.AssertTrue;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;
import com.softmarshmallow.foodle.Services.StoreService;

import java.util.List;
// import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class StoreCreator_Form_BasicInfosFragment extends Fragment
{
        
        private static final String TAG = "BasicInfosFragment";
        
        @NotEmpty
        @BindView(R.id.storeNameTextField)
        EditText storeNameTextField;
        
        @NotEmpty
        @BindView(R.id.storeShortDescriptionTextField)
        EditText storeShortDescriptionTextField;
        
        @NotEmpty
        @BindView(R.id.storeFullDescriptionTextField)
        EditText storeFullDescriptionTextField;
        
        @BindView(R.id.storeAddressTextView)
        TextView storeAddressTextView;

        @AssertTrue
        boolean isStoreLocationSetted = false;

        
        @BindView(R.id.storePhotoImageView)
        ImageView storePhotoImageView;

        @AssertTrue
        boolean isStorePhotoSetted = false;


        @NotEmpty
        @BindView(R.id.storePhoneNumberEditText)
        EditText storePhoneNumberEditText;
        
        @NotEmpty
        @BindView(R.id.storeExtraContactInfoEditText)
        EditText storeExtraContactInfoEditText;
        
        static StoreModel StoreDataToCreate = new StoreModel();
        
        String GetFormattedStorePhoneNumber() {
                return "+82" + storePhoneNumberEditText.getText();
        }
        
        
        
        public StoreCreator_Form_BasicInfosFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_store_creator__form__basic_infos,
                        container, false);
                
                ButterKnife.bind(this, view);
        
                return view;
        }
        
        
        @Override
        public void onAttach(Context context) {
                super.onAttach(context);
        }
        
        @Override
        public void onDetach() {
                super.onDetach();
        }
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                Log.d(TAG, "requestCode : " + requestCode);
                
                if (resultCode == Activity.RESULT_OK) {
                        if (requestCode == selectStorePhotoRequestCode) {
                                storePhotoImageView.setImageURI(data.getData());
                                StoreDataToCreate.StorePhotoLocalUris.add(data.getData().toString());
                                isStorePhotoSetted = true;
                        } else if (requestCode == chooseStoreLocationRequestCode) {
                                Place place = PlacePicker.getPlace(data, getActivity());
                                SetStoreLocationData(place.getLatLng(), place.getAddress()
                                        .toString());
                                isStoreLocationSetted = true;
                        }
                }
        }
        
        
        //region StorePhoto
        private static final int selectStorePhotoRequestCode = 1001;
        
        @OnClick(R.id.storePhotoImageView)
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
        
        @OnClick(R.id.storeChooseLocationButtonView)
        void OnStoreChooseLocationButtonClicked() {
                OpenPlacePicker();
        }
        
        void OpenPlacePicker() {
                
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                
                try {
                        startActivityForResult(builder.build(getActivity()),
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
                storeAddressTextView.setText(storeAddress);
        }
        //endregion
        
        
        @OnClick(R.id.moveNextButton)
        void OnMoveNextButtonClick() {
                Log.d(TAG, "OnMoveNextButtonClick");
                ValidateFormAndCreateStore();
        }
        
        void ValidateFormAndCreateStore(){
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
                                        String message = error.getCollatedErrorMessage(getContext());
                
                                        // Display error messages ;)
                                        if (view instanceof EditText) {
                                                ((EditText) view).setError(message);
                                        } else {
                                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
                        viewToFocus = storeAddressTextView;
                }
        
                if (!isStorePhotoSetted){
                        isValidationFailed = true;
                        errorMessage = "사진을 선택해 주세요.";
                        viewToFocus = storePhotoImageView;
                }
        
                if (isValidationFailed){
                        viewToFocus.requestFocus();
                        new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("오류")
                                .setContentText(errorMessage)
                                .show();
                
                }else {
                        CreateStore();
                }
        }
        
        void CreateStore() {
                final SweetAlertDialog createStoreProgressDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("푸드트럭 업로드중..");
                createStoreProgressDialog.show();

                new StoreService.StoreUploader(
                        StoreDataToCreate
                                .setStoreOwnerId(FirebaseUserService.GetUserUID())
                                .setStoreName(storeNameTextField.getText()
                                        .toString())
                                .setStoreShortDescription(storeShortDescriptionTextField.getText()
                                        .toString())
                                .setStoreFullDescription(storeFullDescriptionTextField.getText()
                                        .toString())
                                //.setStorePhotoUrls
                                .setStoreLocation(
                                        storeLatLng.latitude + ", " + storeLatLng.longitude)
                                .setStoreAddress(storeAddress)
                                .setStorePhoneNumber(GetFormattedStorePhoneNumber())
                                .setStoreExtraContacts(storeExtraContactInfoEditText.getText()
                                        .toString()),
                        new Consumer<StoreModel>()
                        {
                                @Override
                                public void accept(StoreModel storeModel) {
                                        createStoreProgressDialog.dismissWithAnimation();
                                        StoreCreatorActivity_Deprecated.CreatingStoreId = storeModel.Id;
                                        Toast.makeText(getContext(),
                                                "New store created!", Toast.LENGTH_SHORT)
                                                .show();
                                }
                        }, new Consumer<String>()
                {
                        @Override
                        public void accept(String errorMessage) {
                                createStoreProgressDialog.dismissWithAnimation();
                                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("업로드 실패")
                                        .setContentText(errorMessage)
                                        .setConfirmClickListener(
                                                new SweetAlertDialog.OnSweetClickListener()
                                                {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                getActivity().finish();

                                                        }
                                                }).show();
                        }
                }


                ).CreateStore();
                
                

        }
        
}
