package com.softmarshmallow.foodle.Views.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginType;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabControllerActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;


public class
LoginActivity_v2 extends AppCompatActivity
{
        static final String TAG = "LoginActivity";
        public int Goole_SIGN_IN;

        static final Class nextActivity = MainTabControllerActivity.class;
        // UI references.
        @BindView(R.id.emailEditText)
        EditText mEmailView;
        @BindView(R.id.passwordEditText)
        EditText mPasswordView;

        @BindView(R.id.login_form)
        View mLoginFormView;
        
        LoginType loginType;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_FullScreen);
                setContentView(R.layout.activity_login);
                ButterKnife.bind(this);
        
                initFacebookLogin();




        }

        @OnClick(R.id.loginButton)
        void OnLoginButtonClick(){
                attemptLoginWithEmail();
        }
        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are f orm errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        boolean isAttemptingLogin;
        
        String email;
        String password;
        private void attemptLoginWithEmail() {
                loginType = LoginType.Email;
                
                Log.d(TAG, "attemptLoginWithEmail");
                
                if (isAttemptingLogin) {
                        return;
                }
                
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);
                
                // Store values at the time of the login attempt.
                email = mEmailView.getText()
                        .toString();
                password = mPasswordView.getText()
                        .toString();
                
                boolean cancel = false;
                View focusView = null;
                

                // Check for a valid password, if the user entered one.
                if (!isPasswordValid(password)) {
                        mPasswordView.setError(getString(R.string.error_invalid_password));
                        focusView = mPasswordView;
                        cancel = true;
                }
                
                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                        mEmailView.setError(getString(R.string.error_field_required));
                        focusView = mEmailView;
                        cancel = true;
                } else if (!isEmailValid(email)) {
                        mEmailView.setError(getString(R.string.error_invalid_email));
                        focusView = mEmailView;
                        cancel = true;
                }
                
                if (cancel) {
                        // There was an error; don't attempt login and focus the first
                        // form field with an error.
                        focusView.requestFocus();
                } else {
                        // Show a progress spinner, and kick off a background task to
                        // perform the user login attempt.
                        showProgress(true);
                        
                        isAttemptingLogin = true;
                        LoginService.LoginWithEmail(email, password, onLoginCompleteListener);
                        
                }
        }
        
        OnCompleteListener onLoginCompleteListener = new OnCompleteListener()
        {
                @Override
                public void onComplete(@NonNull Task task) {
                        isAttemptingLogin = false;
                        showProgress(false);
        
                        Log.d(TAG, "Login isSuccessful : " + String.valueOf(
                                task.isSuccessful()));
                        if (task.isSuccessful()) {
                                
                                LoginPreferences.setIsLoggedIn(true);
                                LoginPreferences.setLoginType(loginType);
                                switch (loginType){
                                        case Email:
                                                // Save login datas
                                                LoginPreferences.setUserEmail(email);
                                                LoginPreferences.setUserPassword(password);
                                                break;
                                        
                                        case Facebook:
                                                LoginPreferences.setFacebookToken(facebookToken);
                                                break;
                                }
                               
                                // move to next activity
                                Intent intent = new Intent(LoginActivity_v2.this,
                                        nextActivity);
                                startActivity(intent);
                
                                finish();
                        } else {
                                Exception exception = task.getException();
                                new SweetAlertDialog(LoginActivity_v2.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("로그인 오류")
                                        .setContentText(exception.getMessage())
                                        .show();
                                mPasswordView.setError((exception.getMessage()));
                                mPasswordView.requestFocus();
                        }
                }
        };
        
        
        private boolean isEmailValid(String email) {
                //TODO: Replace this with your own logic
                return email.contains("@");
        }
        
        private boolean isPasswordValid(String password) {
                //TODO: Replace this with your own logic
                return !TextUtils.isEmpty(password) || password.length() > 5;
        }
        
        /**
         * Shows the progress UI and hides the login form.
         */
        SweetAlertDialog loginProgressDialog;

        private void showProgress(final boolean show) {

                int shortAnimTime = getResources().getInteger(
                        android.R.integer.config_shortAnimTime);
                        
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                mLoginFormView.animate()
                        .setDuration(shortAnimTime)
                        .alpha(
                                show ? 0 : 1)
                        .setListener(new AnimatorListenerAdapter()
                        {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                        mLoginFormView.setVisibility(
                                                show ? View.GONE : View.VISIBLE);
                                }
                        });

                if (loginProgressDialog == null){
                        loginProgressDialog = new SweetAlertDialog(LoginActivity_v2.this, SweetAlertDialog.PROGRESS_TYPE)
                                .setTitleText("접속중...");
                }

                if (show){
                        loginProgressDialog.show();
                }else {
                        loginProgressDialog.hide();
                }

        }

        
        
        private static final int ShowSignupRequestCode = 943;
        
        @OnClick(R.id.moveToSignupButton)
        void OnMoveToSignupClick() {
                Intent intent = new Intent(LoginActivity_v2.this, SignupActivity.class);
                startActivityForResult(intent, ShowSignupRequestCode);
        }
        

        
        //region facebooklogin
        
        CallbackManager mCallbackManager;
        LoginManager loginManager;
        void initFacebookLogin(){
                // Initialize Facebook LoginWithEmail button
                mCallbackManager = CallbackManager.Factory.create();
        
                FancyButton facebookLoginButton = findViewById(R.id.facebook_login);
                facebookLoginButton.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                loginManager.logInWithReadPermissions(LoginActivity_v2.this, Arrays.asList("email", "public_profile"));
                        }
                });
                AccessToken.setCurrentAccessToken(null);

//                LoginButton loginButton = findViewById(R.id.loginWithFacebookButton);
//                loginButton.setReadPermissions("email", "public_profile");
        
               loginManager= LoginManager.getInstance();
        
        
                final FacebookCallback callback = new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                                handleFacebookAccessToken(loginResult.getAccessToken());
                                new SweetAlertDialog(LoginActivity_v2.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("성공")
                                        .setContentText("페이스북으로 로그인 하였습니다.")
                                        .show();
                        }
        
                        @Override
                        public void onCancel() {
                                new SweetAlertDialog(LoginActivity_v2.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("취소")
                                        .setContentText("사용자에 의해 취소되었습니다.")
                                        .show();
                                Log.d(TAG, "facebook:onCancel");
                                // ...
                        }
        
                        @Override
                        public void onError(FacebookException error) {
                                new SweetAlertDialog(LoginActivity_v2.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("오류")
                                        .setContentText("로그인중 오류가 발생하였습니다. error : " + error)
                                        .show();
                                Log.d(TAG, "facebook:onError", error);
                                // ...
                        }
                };
                
                loginManager.registerCallback(mCallbackManager, callback);
                
//                loginButton.registerCallback(mCallbackManager, callback);
        }



        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                
                // Pass the activity result back to the Facebook SDK
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        
        String facebookToken;
        private void handleFacebookAccessToken(AccessToken token) {
                facebookToken = token.getToken();
                loginType = LoginType.Facebook;
                
                Log.d(TAG, "handleFacebookAccessToken:" + token);
                showProgress(true);
                LoginService.LoginWithFacebook(facebookToken, onLoginCompleteListener);
        }
        
        //endregion

        //region Google login by tae-il
        GoogleSignInOptions gso;
        GoogleSignInClient mGoogleSignInClient;
        void initGoogleLogin(){
                gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//                updateUI(account);

                FancyButton GoogleLoginButton = findViewById(R.id.google_login);
                GoogleLoginButton.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                                startActivityForResult(signInIntent, Goole_SIGN_IN);
                        }
                });
                AccessToken.setCurrentAccessToken(null);
        }
}