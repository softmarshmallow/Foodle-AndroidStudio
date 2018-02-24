package com.softmarshmallow.foodle.Views.ExtraOptions_Deprecated;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.CustomViews.OptionItemView.OptionItemView;
import com.softmarshmallow.foodle.Helpers.ContactToFoodleHelper;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.StoreEditor_Deprecated.StoreCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreUploadRequestView.StoreUploadRequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Option_Tab_Fragment extends Fragment
{

        @BindView(R.id.profilePhotoImageView)
        CircleImageView profilePhotoImageView;



        @BindView(R.id.option_setting)
        OptionItemView option_setting;
        @BindView(R.id.option_help)
        OptionItemView option_help;
        @BindView(R.id.option_change)
        OptionItemView option_change;
        @BindView(R.id.option_storeupload)
        OptionItemView option_storeupload;
        @BindView(R.id.option_feedback)
        OptionItemView option_feedback;





        public Option_Tab_Fragment() {
                // Required empty public constructor
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_options_tab, container,
                        false);
                
                ButterKnife.bind(this, view);
                InitButtons();

                return view;
        }

        private void InitButtons() {
                option_setting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });
                option_help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });
                option_change.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });
                option_storeupload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                getActivity().startActivity(
                                        new Intent(getContext(), StoreUploadRequestActivity.class));

                        }
                });
                option_feedback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();
                        }
                });

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
