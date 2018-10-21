package com.softmarshmallow.foodle.Views.Splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabcontrollerV2.MainTabControllerActivity_v2;


public class SplashScreenActivity extends AppCompatActivity
{
    
    static final String TAG = "SplashScreenActivity";
    final int delayMilliSeconds = 750;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_FullScreen);
        setContentView(R.layout.activity_splash_screen);
        
        //SetTextEffect();
        
        
        Test();
    }
    
    
    void Test() {
        
    
    }
    
    
    @Override
    protected void onResume() {
        super.onResume();
        StartWaiting();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }
    
    Handler handler = new Handler();
    Runnable r = new Runnable()
    {
        @Override
        public void run() {
            MoveToNextActivity();
        }
    };
    
    void StartWaiting() {
        handler.postDelayed(r, delayMilliSeconds);
        
    }
    
    void MoveToNextActivity() {
        
        if (LoginPreferences.getIsLoggedIn()) {
            CheckForNetworkBeforeAutoLogin();
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        
    }
    
    void MoveToMainAfterAutoLogin() {
        final SweetAlertDialog autoLoginProgressDialog;
        autoLoginProgressDialog = new SweetAlertDialog(SplashScreenActivity.this,
            SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("로그인중...");
        autoLoginProgressDialog.setCancelable(false);
        autoLoginProgressDialog.show();
        
        LoginService.AutoLogin(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                autoLoginProgressDialog.dismiss();
                
                if (task.getException() == null) {
                    MoveToMainActivity();
                } else {
                    Log.d(TAG, "ERR:: " + task.getException().getMessage());
                    
                    SweetAlertDialog errorDialog = new SweetAlertDialog(SplashScreenActivity.this,
                        SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("로그인 오류!")
                        .setContentText("로그아웃후 재로그인후 시도해보시기 바랍니다. 추가로 인터넷에 연결되어있는지 확인해주세요.")
                        
                        .setConfirmText("로그아웃")
                        .setConfirmClickListener(
                            new SweetAlertDialog.OnSweetClickListener()
                            {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    LoginService.Logout(true);
                                    Intent i = new Intent(SplashScreenActivity.this, SplashScreenActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            })
                        
                        .setCancelText("재시도")
                        .setCancelClickListener(
                            new SweetAlertDialog.OnSweetClickListener()
                            {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    MoveToMainAfterAutoLogin();
                                }
                            });
                    errorDialog.setCancelable(false);
                    errorDialog.show();
                }
            }
        });
    }
    
    void CheckForNetworkBeforeAutoLogin(){
        if (isNetworkAvailable()){
            MoveToMainAfterAutoLogin();
        }else {
    
            SweetAlertDialog errorDialog = new SweetAlertDialog(SplashScreenActivity.this,
                SweetAlertDialog.ERROR_TYPE)
                .setTitleText("네트워크 오류")
                .setContentText("인터넷에 연결되어있지 않습니다. 연결후 재시도해주세요.")
        
                .setConfirmText("오프라인으로 계속")
                .setConfirmClickListener(
                    new SweetAlertDialog.OnSweetClickListener()
                    {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            MoveToMainActivity();
                        }
                    })
        
                .setCancelText("재시도")
                .setCancelClickListener(
                    new SweetAlertDialog.OnSweetClickListener()
                    {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            CheckForNetworkBeforeAutoLogin();
                        }
                    });
            errorDialog.setCancelable(false);
            errorDialog.show();
        }
        
    }
    
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
            = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    void MoveToMainActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainTabControllerActivity_v2.class);
        finish();
        startActivity(intent);
    }
}
