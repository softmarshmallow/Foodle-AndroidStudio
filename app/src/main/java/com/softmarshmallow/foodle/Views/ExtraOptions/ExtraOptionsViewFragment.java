package com.softmarshmallow.foodle.Views.ExtraOptions;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Helpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.Festival.FestivalCreatorActivity;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.StoreEditor.StoreCreatorActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
                        .setContentText("넵!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                        {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        LoginPreferences.clearLoginPreference();
                                        LoginService.Logout();
                                        startActivity(
                                                new Intent(getContext(), LoginActivity.class));
                                        getActivity().finish();
                                }
                        })
                        .setCancelText("한번 눌러봤어요")
                        
                        .show();
                
        }
        
        
        @OnClick(R.id.CreateNewFestivalButton)
        void OnCreateNewFestivalButtonClicked() {
                getActivity().startActivity(
                        new Intent(getContext(), FestivalCreatorActivity.class));
        }
        
        
        @OnClick(R.id.ContactButton)
        public void onContactButtonClicked() {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"woojoo@softmarshmallow.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "푸들에게 한마디");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "내용을 입력해주세요");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
        }
}
