package com.softmarshmallow.foodle.Views.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.Exclude;
import com.softmarshmallow.foodle.Helpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabControllerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{
        static final String TAG = "LoginActivity";

        static final Class nextActivity = MainTabControllerActivity.class;
        // UI references.
        @BindView(R.id.emailEditText)
        EditText mEmailView;
        @BindView(R.id.passwordEditText)
        EditText mPasswordView;

        @BindView(R.id.login_form)
        View mLoginFormView;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_FullScreen);
                setContentView(R.layout.activity_login);
                ButterKnife.bind(this);

        }

        @OnClick(R.id.loginButton)
        void OnLoginButtonClick(){
                attemptLogin();
        }
        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        boolean isAttemptingLogin;
        
        private void attemptLogin() {
                Log.d(TAG, "attemptLogin");
                
                if (isAttemptingLogin) {
                        return;
                }
                
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);
                
                // Store values at the time of the login attempt.
                final String email = mEmailView.getText()
                        .toString();
                final String password = mPasswordView.getText()
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
                        LoginService.Login(email, password, new OnCompleteListener<AuthResult>()
                        {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                        isAttemptingLogin = false;
                                        showProgress(false);
                                        
                                        Log.d(TAG, "Login isSuccessful : " + String.valueOf(
                                                task.isSuccessful()));
                                        if (task.isSuccessful()) {

                                                // Save login datas
                                                LoginPreferences.setUserEmail(email);
                                                LoginPreferences.setUserPassword(password);
                                                LoginPreferences.setIsLoggedIn(true);

                                                // move to next activity
                                                Intent intent = new Intent(LoginActivity.this,
                                                        nextActivity);
                                                startActivity(intent);
                                                
                                                finish();
                                        } else {
                                                Exception exception = task.getException();
                                                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText("로그인 오류")
                                                        .setContentText(exception.getMessage())
                                                        .show();
                                                mPasswordView.setError((exception.getMessage()));
                                                mPasswordView.requestFocus();
                                        }
                                }
                        });
                        
                }
        }
        
        
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
                        loginProgressDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                                .setTitleText("Logging in...");
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
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(intent, ShowSignupRequestCode);
        }
}

