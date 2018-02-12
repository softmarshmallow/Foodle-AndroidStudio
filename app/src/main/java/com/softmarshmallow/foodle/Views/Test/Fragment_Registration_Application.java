package com.softmarshmallow.foodle.Views.Test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;

import com.softmarshmallow.foodle.Models.RegistrationApplicationModel;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yuntaeil on 2018. 2. 12..
 */

public class Fragment_Registration_Application extends Fragment {
    @BindView (R.id.registration_store_Description)
    EditText Store_Description;
    @BindView (R.id.registration_store_Name)
    EditText Store_Name;
    @BindView (R.id.registration_store_Tel)
    EditText Store_Tel;
    @BindView (R.id.registration_Tel)
    EditText Tel;
    @BindView (R.id.registration_store_isOwn)
    CheckBox IsOwn;

    @OnClick (R.id.nextButton)
    void Go_next(){
        GotoAddressForm();
    }

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


    }
    public void BindingAll(){
        ApplyData.setStoreName(String.valueOf(Store_Name.getText()))
                 .setStoreDescription(String.valueOf(Store_Description.getText()))
                 .setTel(String.valueOf(Tel.getText()))
                 .setStoreTel(String.valueOf(Store_Tel.getText()))
                 .setOwn(IsOwn.isChecked());

    }
}
