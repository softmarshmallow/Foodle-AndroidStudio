package com.softmarshmallow.foodle.Views.StoreUploadRequestView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView.DefaultEditTextContainerView;
import com.softmarshmallow.foodle.Models.StoreUploadRequestModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.StoreUploadRequestService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

import static android.text.InputType.TYPE_CLASS_PHONE;

/**
 * Created by yuntaeil on 2018. 2. 12..
 */

public class StoreUploadRequestViewFragment extends Fragment {
    @BindView (R.id.registration_store_Description)
    DefaultEditTextContainerView Store_Description;
    @BindView (R.id.registration_store_Name)
    DefaultEditTextContainerView Store_Name;
    @BindView (R.id.registration_Store_Tel)
    DefaultEditTextContainerView Store_Tel;
    @BindView (R.id.registration_Tel)
    DefaultEditTextContainerView Tel;
    @BindView (R.id.registration_store_isOwn)
    CheckBox IsOwn;

    @OnClick (R.id.nextButton)
    void Go_next(){
       
        //GotoAddressForm();
        Log.d(TAG, "UploadStore");
        if(CheckFieldisNotEmpty()){
            progressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("신청 중..");
            progressDialog.show();
            BindingAll();
            UploadStoreUploadRequest();
        }
        
    }
    SweetAlertDialog progressDialog;
    final String TAG = "Registration_Application";
    StoreUploadRequestModel ApplyData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration_application, container, false);
        ButterKnife.bind(this, view);
        Store_Tel.contentEditText.setInputType(TYPE_CLASS_PHONE);
        Tel.contentEditText.setInputType(TYPE_CLASS_PHONE);
        ApplyData = new StoreUploadRequestModel();
        
        return view;
    }

    public void BindingAll(){
        ApplyData.setStoreName(String.valueOf(Store_Name.contentEditText.getText()))
                 .setStoreDescription(String.valueOf(Store_Description.contentEditText.getText()))
                 .setTel(String.valueOf(Tel.contentEditText.getText()))
                 .setStoreTel(String.valueOf(Store_Tel.contentEditText.getText()))
                 .setOwn(IsOwn.isChecked())
                 .setLat(0)
                 .setLat(0)
                 .setLocationDescription("TEST");
    }

    public boolean CheckFieldisNotEmpty(){
        if(String.valueOf(Store_Name.contentEditText.getText()).isEmpty()){
            Store_Name.contentEditText.setError("Enter your store name");
            return false;
        }
        if(String.valueOf(Store_Description.contentEditText.getText()).isEmpty()){
            Store_Description.contentEditText.setError("Enter your store Description");
            return false;
        }

        if(String.valueOf(Store_Tel.contentEditText.getText()).isEmpty()){
            Store_Tel.contentEditText.setError("Enter your store Tel");
            return false;
        }

        if(String.valueOf(Tel.contentEditText.getText()).isEmpty()){
            Tel.contentEditText.setError("Enter your Phone");
            return false;
        }
    
        return true;
    }
    
    public void UploadStoreUploadRequest(){
        
        new StoreUploadRequestService.StoreUploadRequestCreator(
                ApplyData,
                new Consumer<String>()
                {
                    @Override
                    public void accept(String s) throws Exception {
                        progressDialog.dismiss();
                        new SweetAlertDialog (getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("complete")
                                .setConfirmClickListener(
                                        new SweetAlertDialog.OnSweetClickListener()
                                        {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismiss();
                                                getActivity().finish();
                                            }
                                        })
                                .show();
                        
                    }
                },
                new Consumer<String>()
                {
                    @Override
                    public void accept(String s) throws Exception {
                        progressDialog.dismiss();
                        new SweetAlertDialog (getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("ERROR").show();
                    }
                }
        ).CreateStoreUploadRequest();
    }
}
