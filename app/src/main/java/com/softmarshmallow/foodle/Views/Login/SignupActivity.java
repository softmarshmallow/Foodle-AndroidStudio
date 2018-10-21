package com.softmarshmallow.foodle.Views.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends AppCompatActivity
{
        
        
        private static final String TAG = "SignUpActivity";
        // UI references.
        @BindView(R.id.signupEmialTextField)
        EditText mEmailView;
        
        @BindView(R.id.signupPasswordTextField)
        EditText mPasswordView;
        
        @BindView(R.id.signupConfirmPasswordTextField)
        EditText mConfirmPasswordView;
        
        @BindView(R.id.signupFormView)
        View mSignupFormView;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_FullScreen);
                setContentView(R.layout.activity_signup);
                
                ButterKnife.bind(this);
        }
        
        
        @OnClick(R.id.signupButton)
        void OnSignupButtonClick() {
                attemptSignup();
        }
        
        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        boolean isAttemptingSigup;
        
        private void attemptSignup() {
                
                Log.d(TAG, "attemptLogin");
                
                if (isAttemptingSigup) {
                        return;
                }
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);
                mConfirmPasswordView.setError(null);
                // Store values at the time of the login attempt.
                String email = mEmailView.getText()
                        .toString();
                String password = mPasswordView.getText()
                        .toString();
                String passwordConfirm = mConfirmPasswordView.getText()
                        .toString();
                
                boolean cancel = false;
                View focusView = null;
                
                // Check for a valid password, if the user entered one.
                if (!isPasswordValid(password)) {
                        mPasswordView.setError(getString(R.string.error_invalid_password));
                        focusView = mPasswordView;
                        cancel = true;
                }
                
                if (!isPasswordConfirmCorrect(password, passwordConfirm)) {
                        mConfirmPasswordView.setError(
                                getString(R.string.error_confirm_password_non_match));
                        focusView = mConfirmPasswordView;
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
                        isAttemptingSigup = true;
                        LoginService.CreateNewUser(email, password,
                                new OnCompleteListener<AuthResult>()
                                {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                
                                                showProgress(false);
                                                isAttemptingSigup = false;
                                                
                                                if (task.isSuccessful()) {
                                                        new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Register Complete")
                                                                .setConfirmText("LoginWithEmail")
                                                                .setConfirmClickListener(
                                                                        new SweetAlertDialog.OnSweetClickListener()
                                                                        {
                                                                                @Override
                                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                                        sweetAlertDialog.dismissWithAnimation();
                                                                                        finish();
                                                                                }
                                                                        })
                                                                .show();
                                                } else {
                                                        Exception exception = task.getException();

                                                        new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                                .setTitleText("Error")
                                                                .setContentText(exception.getLocalizedMessage())
                                                                .setCancelText("Retry")
                                                                .show();

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
                return !TextUtils.isEmpty(password) || password.length() > 5;
        }
        
        private boolean isPasswordConfirmCorrect(String password, String passwordConfirm) {
                return password.equals(passwordConfirm);
        }
        
        /**
         * Shows the progress UI and hides the login form.
         */
        SweetAlertDialog signUpProgressDialog;

        private void showProgress(final boolean show) {
                // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
                // for very easy animations. If available, use these APIs to fade-in
                // the progress spinner.
                        int shortAnimTime = getResources().getInteger(
                                android.R.integer.config_shortAnimTime);
                        
                        mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        mSignupFormView.animate()
                                .setDuration(shortAnimTime)
                                .alpha(
                                        show ? 0 : 1)
                                .setListener(new AnimatorListenerAdapter()
                                {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                                mSignupFormView.setVisibility(
                                                        show ? View.GONE : View.VISIBLE);
                                        }
                                });



                if (signUpProgressDialog == null){
                        signUpProgressDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                                .setTitleText("Registering...");
                }

                if (show){
                        signUpProgressDialog.show();
                }else {
                        signUpProgressDialog.hide();
                }
        }
        
}

