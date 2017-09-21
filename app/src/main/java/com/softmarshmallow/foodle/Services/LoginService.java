package com.softmarshmallow.foodle.Services;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.softmarshmallow.foodle.Helpers.LoginPreferences;

import io.reactivex.functions.Consumer;

public class LoginService
{
        // for debug
        private static final String TAG = LoginService.class.getName();
        private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

        public static void CreateNewUser(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener)
        {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(authResultOnCompleteListener);
        }

        public static void Login(String email, String password, OnCompleteListener<AuthResult> authResultOnCompleteListener)
        {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(authResultOnCompleteListener);
        }
        
        public static void Logout(){
                mAuth.signOut();
        }

        public static void AutoLogin(OnCompleteListener<AuthResult> authResultOnCompleteListener){
                Login(LoginPreferences.getUserEmail(), LoginPreferences.getUserPassword(), authResultOnCompleteListener);
        }

}
