package com.softmarshmallow.foodle.Views.RequestCatering;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.softmarshmallow.foodle.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RequestCateringActivity extends AppCompatActivity
{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_request_catering);
                ButterKnife.bind(this);
        
                NotifyUserThatThisFeatureIsNotSupported();
        }
        
        
        void NotifyUserThatThisFeatureIsNotSupported(){
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("지원안함")
                        .setContentText("이 기능은 아직 지원되지 않습니다.\n그냥 둘러볼까요?")
                        .show();
        }
        

        // region Time
        Calendar cateringRequestCalendar = Calendar.getInstance();
        Date getCateringRequestDateTime(){
                return  cateringRequestCalendar.getTime();
        }

        @BindView(R.id.chooseCateringDateButton)
        FancyButton chooseCateringDateButton;


        @OnClick(R.id.chooseCateringDateButton)
        void OnChooseCateringDateButtonClick(){
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener()
                        {
                                @Override
                                public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                        cateringRequestCalendar.set(year, monthOfYear, dayOfMonth);

                                        SimpleDateFormat cateringDateFormatter = new SimpleDateFormat("yyyy.MM.dd | EEE");
                                        String formatted = cateringDateFormatter.format(getCateringRequestDateTime());
                                        chooseCateringDateButton.setText(formatted);
                                }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
        }


        @BindView(R.id.chooseCateringTimeButton)
        FancyButton chooseCateringTimeButton;

        @OnClick(R.id.chooseCateringTimeButton)
        void OnChooseCateringTimeButtonClick(){
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener()
                        {
                                @Override
                                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                        cateringRequestCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        cateringRequestCalendar.set(Calendar.MINUTE, minute);
                                        cateringRequestCalendar.set(Calendar.SECOND, second);


                                        SimpleDateFormat cateringTimeFormatter = new SimpleDateFormat("hh:mm a");
                                        String formatted = cateringTimeFormatter.format(getCateringRequestDateTime());
                                        chooseCateringTimeButton.setText(formatted);
                                }
                        }, false);
                timePickerDialog.show(getFragmentManager(), "Time Picker");
        }
        // endregion
        
        
        //region
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
        
                if (requestCode == chooseCateringLocationRequestCode) {
                        Place place = PlacePicker.getPlace(data, this);
                        setCateringLoaction(place.getLatLng(), place.getAddress()
                                .toString());
                        isCateringLocationSetted = true;
                }
        }
        
        
        LatLng storeLatLng;
        String storeAddress;
        
        public void setCateringLoaction(LatLng cateringLatLng, String cateringAddress) {
                this.storeLatLng = cateringLatLng;
                this.storeAddress = cateringAddress;
                chooseCateringLocationButton.setText("위치 : " + cateringLatLng.toString());
        }
        
        final static int chooseCateringLocationRequestCode = 2001;
        boolean isCateringLocationSetted = false;
        
        @BindView(R.id.chooseCateringLocationButton)
        FancyButton chooseCateringLocationButton;
        
        @OnClick(R.id.chooseCateringLocationButton)
        void OnChooseCateringLocationButtonClick(){
                OpenPlacePicker();
        }
        
        
        void OpenPlacePicker() {
                
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                
                try {
                        startActivityForResult(builder.build(this),
                                chooseCateringLocationRequestCode);
                }
                catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                }
        }
        
        
        //endregion


        //region AdditionalRequestMessage

        @BindView(R.id.additionalRequestMessageTextView)
        TextView additionalRequestMessageTextView;

        String getAdditionalRequestMessage(){
                return  additionalRequestMessageTextView.getText().toString();
        }
        //endregion


        //region EatersNumber
        DiscreteSeekBar eatersNumberPicker;
        int getEatersNumber(){
                return eatersNumberPicker.getProgress();
        }
        //endregion



        //region
        @OnClick(R.id.requestCateringButton)
        void OnRequestCateringButtonClick(){

                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("지원안됨")
                        .setContentText("이 기능은 아직 지원되지 않아요 ㅠ")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                        {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        finish();
                                }
                        }).show();

        }

        void CreateCateringRequest(){
                
        }
        //endregion






        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
}
