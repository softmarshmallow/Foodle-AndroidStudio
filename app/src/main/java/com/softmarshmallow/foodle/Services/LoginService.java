package com.softmarshmallow.foodle.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginType;

public class LoginService
{
    private static final String TAG = LoginService.class.getSimpleName();
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    
    public static void CreateNewUser(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(authResultOnCompleteListener);
    }
    
    public static void LoginWithEmail(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(authResultOnCompleteListener);
    }
    
    public static void Logout(Boolean clearData) {
        mAuth.signOut();
        if (clearData){
            LoginPreferences.clearLoginPreference();
        }
    
        if (LoginPreferences.getLoginType() == LoginType.Facebook) {
            LoginManager.getInstance()
                .logOut();
            AccessToken.setCurrentAccessToken(null);
        }
    }
    
    public static void AutoLogin(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        LoginType lType = LoginPreferences.getLoginType();
        Log.d(TAG, "Auto Login, Login type :: " + lType.name());
        
        switch (lType) {
            
            case Email:
                LoginWithEmail(LoginPreferences.getUserEmail(), LoginPreferences.getUserPassword(),
                    authResultOnCompleteListener);
                break;
            case Facebook:
                LoginWithFacebook(LoginPreferences.getFacebookToken(),
                    authResultOnCompleteListener);
                break;
            case Google:
                LoginWithGoogle(LoginPreferences.getFacebookToken(), authResultOnCompleteListener);
                break;
        }
        
    }
    
    public static void LoginWithFacebook(String token, OnCompleteListener onCompleteListener) {
        
        AuthCredential credential = FacebookAuthProvider.getCredential(token);
        LoginWithCredential(credential, onCompleteListener);
    }
    
    public static void LoginWithGoogle(String token, OnCompleteListener onCompleteListener) {
    
        AuthCredential credential = FacebookAuthProvider.getCredential(token);
        LoginWithCredential(credential, onCompleteListener);
    }
    
    public static void LoginWithCredential(AuthCredential credential, OnCompleteListener onCompleteListener) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(onCompleteListener);
    }
    
}
