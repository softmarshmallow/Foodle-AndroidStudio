package com.softmarshmallow.foodle.Views.Test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView.DefaultEditTextContainerView;
import com.softmarshmallow.foodle.Models.RegistrationApplicationModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.ApiController;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuntaeil on 2018. 2. 12..
 */

public class Fragment_Registration_Application extends Fragment {
    @BindView (R.id.registration_store_Description)
    DefaultEditTextContainerView Store_Description;
    @BindView (R.id.registration_store_Name)
    DefaultEditTextContainerView Store_Name;
    @BindView (R.id.registration_Tel)
    DefaultEditTextContainerView Tel;
    @BindView (R.id.registration_store_isOwn)
    CheckBox IsOwn;

    @OnClick (R.id.nextButton)
    void Go_next(){
        GotoAddressForm();
    }

    final String TAG = "Registration_Application";
    RegistrationApplicationModel ApplyData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration_application, container, false);
        ButterKnife.bind(this, view);
        ApplyData = new RegistrationApplicationModel();

        return view;
    }

    public void GotoAddressForm(){
        if (CheckFieldisEmpty()){
            return;
        }
        BindingAll();
        SeverTest retrofitService = ApiController.getRetrofit().create(SeverTest.class);

        Call<RegistrationApplicationModel> call = retrofitService.sendRegistrationApplication(ApplyData);
        call.enqueue(new Callback<RegistrationApplicationModel>() {
            @Override
            public void onResponse(Call<RegistrationApplicationModel> call, Response<RegistrationApplicationModel> response) {
                //Toast.makeText(this, repo+" 라고 서버가 말햇다",Toast.LENGTH_LONG).show();
                Log.d("Sever","Code : "+ response.code()+"\nonResponse: "+response.body()+"\n"+response);

            }

            @Override
            public void onFailure(Call<RegistrationApplicationModel>  call, Throwable t) {
                Log.d("Sever","onResponse: Fail"+call+"\n"+t );

            }
        });

    }
    public void BindingAll(){
        ApplyData.setImages(new File[]{})
                 .setStoreName(String.valueOf(Store_Name.contentEditText.getText()))
                 .setStoreDescription(String.valueOf(Store_Description.contentEditText.getText()))
                 .setStoreTel(String.valueOf(Tel.contentEditText.getText()))
                 .setOwn(IsOwn.isChecked())
                 .setLat(0)
                 .setLat(0)
                 .setLocationDescription("TEST");
    }

    public boolean CheckFieldisEmpty(){
        if(String.valueOf(Store_Name.contentEditText.getText()).isEmpty()){
            Store_Name.contentEditText.setError("Enter your store name");
            return true;
        }

        return false;
    }
}
