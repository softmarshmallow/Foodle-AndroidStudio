package com.softmarshmallow.foodle.Views.Festival;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FestivalCreatorActivity extends AppCompatActivity
{

        private static final String TAG = "FestivalCreatorActivity";
        //region Views
        @BindView(R.id.eventNameTextField)
        EditText eventNameTextField;

        @BindView(R.id.eventShortDescriptionTextField)
        EditText eventShortDescriptionTextField;

        @BindView(R.id.eventFullDescriptionTextField)
        EditText eventFullDescriptionTextField;

        @BindView(R.id.locationPreviewMap)
        MapView locationPreviewMap;

        @BindView(R.id.eventAddressTextView)
        TextView eventAddressTextView;

        @BindView(R.id.eventCoordinateTextView)
        TextView eventCoordinateTextView;

        @BindView(R.id.eventIconImageView)
        ImageView eventIconImageView;

        @BindView(R.id.eventFeatureGraphicImageView)
        ImageView eventFeatureGraphicImageView;

        @BindView(R.id.eventPhoneNumberEditText)
        EditText eventPhoneNumberEditText;

        @BindView(R.id.eventExtraContanctInfoEditText)
        EditText eventExtraContanctInfoEditText;
//endregion



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_festival_creator);
                ButterKnife.bind(this);
        }


        // Choose Location
        @OnClick(R.id.createEventButton)
        void OnCreateEventButtonClick() {
                ProgressDialog progressDialog = ProgressDialog.show(this, "uploading",
                        "sending data...");
        }




        private static final int ChooseEventImagesRequestCode = 20000;

        @OnClick(R.id.chooseEventImagesButton)
        void OnChooseEventImagesButtonClick() {
                // FIXME: 22/08/2017
        }

        private static final int ChooseEventLocationRequestCode = 10000;

        @OnClick(R.id.chooseEventLocationButtonView)
        void OnChooseEventLocationButtonClick() {
                OpenPlacePicker();
        }


        void OpenPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
                startActivityForResult(builder.build(this),
                        ChooseEventLocationRequestCode);
        }
        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
        }
}

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == ChooseEventLocationRequestCode) {
                        if (resultCode == RESULT_OK) {
                                Place place = PlacePicker.getPlace(data, this);

                                // TODO: 22/08/2017 add logic under
                        }
                }
        }



}
