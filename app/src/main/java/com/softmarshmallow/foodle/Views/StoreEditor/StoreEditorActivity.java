package com.softmarshmallow.foodle.Views.StoreEditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class StoreEditorActivity extends AppCompatActivity
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
        
        @BindView(R.id.selectStoreLocationButton)
        FancyButton selectStoreLocationButton;
        
        @NotEmpty
        @BindView(R.id.storeAddressEditText)
        EditText storeAddressEditText;
        
        @NotEmpty
        @BindView(R.id.PhoneNumberEditText)
        EditText PhoneNumberEditText;
        
        @NotEmpty
        @BindView(R.id.facebookUrlEditText)
        EditText facebookUrlEditText;
        
        @NotEmpty
        @BindView(R.id.instagramUrlEditText)
        EditText instagramUrlEditText;
        
        StoreModel StoreDataToUpdate = new StoreModel();
        
        //region edit mode
        public enum EditorType{
                Create,
                Update
        }
        static EditorType editorType;
        public static void SetEditingMode(EditorType editorType){
                StoreEditorActivity.editorType = editorType;
        }
        //endregion
        
        
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
                                StoreDataToUpdate.StorePhotoLocalUris.add(data.getData()
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
                String extraContacts = "페이스북 : " + facebookUrlEditText.getText().toString();
                extraContacts += "\n" + "인스타그램 : " + instagramUrlEditText.getText().toString();
                return extraContacts;
        }
        
        //endregion
        
        
        // region Menus
        @OnClick(R.id.endterEditMenuPageButton)
        void OnEndterEditMenuPageButtonClick(){
                
                // // FIXME: 9/12/17  for debug
                startActivity(new Intent(this, MenuCreatorActivityV2.class));
        }
        //endregion
        
        
        
}
