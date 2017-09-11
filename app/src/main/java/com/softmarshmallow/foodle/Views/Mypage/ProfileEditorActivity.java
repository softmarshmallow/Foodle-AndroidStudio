package com.softmarshmallow.foodle.Views.Mypage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.hdodenhof.circleimageview.CircleImageView;

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
        
                LoadFirebaseUserProfileData();
        }
        
        
        void LoadFirebaseUserProfileData(){
                // Photo
                if (FirebaseUserService.isProfilePhotoSetted()){
                        String profilePhotoUrl = FirebaseUserService.getProfilePhotoUrl();
                        Glide.with(this).load(profilePhotoUrl).into(profilePhotoImageView);
                }
                
                // DisplayName
                String displayName= FirebaseUserService.getDisplayName();
                displaynameEditText.setText(displayName);
                
                // Email
                String email = FirebaseUserService.getUserEmail();
                displaynameEditText.setText(email);
        }
        
        
        
        @OnClick(R.id.profilePhotoImageView)
        public void onViewClicked() {
                
        }
        
        
        boolean isPasswordChanged;
        @OnTextChanged(R.id.passwordEditText)
        void OnPasswordEditTextChanged(CharSequence password){
                
        }
        
        
        
}
