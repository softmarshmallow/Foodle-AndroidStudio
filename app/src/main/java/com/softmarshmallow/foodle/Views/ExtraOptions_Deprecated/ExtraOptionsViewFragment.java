package com.softmarshmallow.foodle.Views.ExtraOptions_Deprecated;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Helpers.ContactToFoodleHelper;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.StoreEditor_Deprecated.StoreCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreUploadRequestView.StoreUploadRequestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraOptionsViewFragment extends Fragment
{
        
        public ExtraOptionsViewFragment() {
                // Required empty public constructor
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_extra_options_view, container,
                        false);
                
                ButterKnife.bind(this, view);
                
                return view;
        }
        
        
        @OnClick(R.id.CreateStoreButton)
        void OnCreateStoreButtonClicked() {
                getActivity().startActivity(new Intent(getContext(), StoreCreatorActivity.class));
        }
        
        
        @OnClick(R.id.LogoutButton)
        void OnClearLoginDataButtonClicked() {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("로그아웃")
                        .setContentText("로그아웃 하시겠습니까?")
                        .setConfirmText("넵!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                        {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        LoginService.Logout(true);
                                        startActivity(
                                                new Intent(getContext(), LoginActivity.class));
                                        getActivity().finish();
                                }
                        })
                        .setCancelText("한번 눌러봤어요")
                        
                        .show();
                
        }
        
        
        @OnClick(R.id.StoreUploadRequestButton)
        void OnStoreUploadRequestButtonClicked() {
                getActivity().startActivity(
                        new Intent(getContext(), StoreUploadRequestActivity.class));
        }
        
        
        @OnClick(R.id.ContactButton)
        public void onContactButtonClicked() {
                ContactToFoodleHelper.SendEmailToFoodle(getContext());
        }
        
        
        @OnClick(R.id.checkPolicyButton)
        public void onCheckPolicyButtonClicked() {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("준비중")
                        .setContentText("준비중입니다.")
                        .show();
        }
        
        @OnClick(R.id.locationBasedServicePolicyButton)
        public void onLocationBasedServicePolicyButtonClicked() {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("준비중")
                        .setContentText("준비중입니다.")
                        .show();
        }
        
        @OnClick(R.id.checkPrivacyPolicyButton)
        public void onCheckPrivacyPolicyButtonClicked() {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("준비중")
                        .setContentText("준비중입니다.")
                        .show();
        }
        
        @OnClick(R.id.checkVersionButton)
        public void onCheckVersionButtonClicked() {
                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("v0.7.x")
                        .setContentText("축하드립니다. 최신 버전이군요!")
                        .show();
        }
}
