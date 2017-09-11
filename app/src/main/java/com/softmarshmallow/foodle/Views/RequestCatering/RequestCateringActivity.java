package com.softmarshmallow.foodle.Views.RequestCatering;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setContentText("신청완료")
                        .setConfirmText("확인")
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
