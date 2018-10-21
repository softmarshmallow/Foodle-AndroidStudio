package com.softmarshmallow.foodle.Views.Mypage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.User.FoodleUserProfileModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;
import com.softmarshmallow.foodle.Services.FoodleUserProfileService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class ProfileEditorActivity extends AppCompatActivity
{
        
        @BindView(R.id.profilePhotoImageView)
        CircleImageView profilePhotoImageView;
        @BindView(R.id.displaynameEditText)
        EditText displaynameEditText;
        @BindView(R.id.emailEditText)
        EditText emailEditText;
        @BindView(R.id.passwordEditText)
        EditText passwordEditText;
        @BindView(R.id.PhoneNumberEditText)
        EditText phoneNumberEditText;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_profile_editor);
                ButterKnife.bind(this);
        
                LoadFoodleUserProfileData();
        }
        
        
        void LoadFoodleUserProfileData(){
                
                final SweetAlertDialog loadingProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("프로필 로딩중");
                
                loadingProgressDialog.show();
                
                FoodleUserProfileService.getUserProfile(FirebaseUserService.GetUserUID(),
                        new Consumer<FoodleUserProfileModel>()
                        {
                                @Override
                                public void accept(FoodleUserProfileModel foodleUserProfileModel) throws Exception {
                                        loadingProgressDialog.dismissWithAnimation();
                                        
                                        Glide.with(ProfileEditorActivity.this).load(foodleUserProfileModel.photoUrl).into(profilePhotoImageView);
        
                                        displaynameEditText.setText(foodleUserProfileModel.displayName);
        
                                        emailEditText.setText(foodleUserProfileModel.email);
        
                                        phoneNumberEditText.setText(foodleUserProfileModel.phoneNumber);
        
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        loadingProgressDialog.dismiss();
                                        new SweetAlertDialog(ProfileEditorActivity.this,SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("프로필 로드 실패")
                                                .setContentText(s)
                                                .show();
                                }
                        });
        }
        
        
        
        @OnClick(R.id.profilePhotoImageView)
        public void onProfilePhotoImageViewClicked() {
                
        }
        
        
        String profilePhotoUrI;
        
        
        @OnClick(R.id.saveTextButton)
        void OnSaveTextButtonClick(){
        
                FoodleUserProfileModel newProfile = new FoodleUserProfileModel()
                        .setDisplayName(displaynameEditText.getText().toString())
                        .setEmail(emailEditText.getText().toString())
                        .setPhoneNumber(phoneNumberEditText.getText().toString())
                        .setPhotoUrl(profilePhotoUrI);
                
                
               final SweetAlertDialog uploadProgressDialog =  new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("프로필 업데이트중...");
        
        
                uploadProgressDialog.show();
                FoodleUserProfileService.updateUserProfile(FirebaseUserService.GetUserUID(),
                        newProfile,
                        new Consumer<FoodleUserProfileModel>()
                        {
                                @Override
                                public void accept(FoodleUserProfileModel foodleUserProfileModel) throws Exception {
                                        uploadProgressDialog.dismissWithAnimation();
                                        new SweetAlertDialog(ProfileEditorActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("업데이트 완료")
                                                .show();
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        uploadProgressDialog.dismissWithAnimation();
                                        new SweetAlertDialog(ProfileEditorActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("업데이트 에러")
                                                .setContentText(s)
                                                .show();
                                }
                        });
        }
}
