package com.softmarshmallow.foodle.Views.SMSVerification;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nexmo.sdk.verify.client.VerifyClient;
import com.nexmo.sdk.verify.event.SearchListener;
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.UserStatus;
import com.nexmo.sdk.verify.event.VerifyClientListener;
import com.softmarshmallow.foodle.FoodleApp;
import com.softmarshmallow.foodle.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SMSVerification_PhoneNumberEnterViewFragment extends Fragment
{
        public static final String TAG = SMSVerification_PhoneNumberEnterViewFragment.class.getSimpleName();
        
        @BindView(R.id.phoneNumberEditText)
        EditText phoneNumber_et;
        
        @BindView(R.id.confirm_button)
        FancyButton sign_btn;
        
        public SMSVerification_PhoneNumberEnterViewFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        
                View view = inflater.inflate(R.layout.fragment_sms_verification_phone_number_enter_view, container, false);
                ButterKnife.bind(this, view);
                setup();
                return view;
        }
        
        void setup(){
                final Activity activity = getActivity();
                final InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        
                phoneNumber_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                                // If DONE or Enter were pressed, validate the input.
                                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                                        inputMethodManager.hideSoftInputFromWindow(phoneNumber_et.getWindowToken(), 0);
                                        Editable phoneNumber = phoneNumber_et.getText();
                                        if (TextUtils.isEmpty(phoneNumber.toString()) || phoneNumber.toString().length() < 5)
                                                phoneNumber_et.setError("Error Phone Number");
                                        return true;
                                }
                                return false;
                        }
                });
        
                sign_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                inputMethodManager.hideSoftInputFromWindow(phoneNumber_et.getWindowToken(), 0);
                                final String prefix = "KR";
                        
                                Editable phoneNumberEdit = phoneNumber_et.getText();
                        
                                if(phoneNumberEdit != null) {
                                        final String phoneNumber = phoneNumberEdit.toString();
                                        if(TextUtils.isEmpty(prefix) || TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 5)
                                                phoneNumber_et.setError("Enter your Phone Number");
                                        else {
                                                initiateGetUserStatus(prefix, phoneNumber);
                                                SMSVerificationActivity.Instance.showPinEnterView();
                                        }
                                }
                        }
                });
        }
        
        
        private void initiateGetUserStatus(final String prefix, final String phoneNumber) {
                final FoodleApp application = (FoodleApp) getActivity().getApplication();
                
                application.getVerifyClient().getUserStatus(prefix, phoneNumber, new SearchListener() {
                        @Override
                        public void onUserStatus(final UserStatus userStatus) {
                                Log.d(TAG, "onUserStatus " + userStatus.toString());
                                showToast("onUserStatus: " + userStatus.toString());
                                
                                if(userStatus != UserStatus.USER_VERIFIED) {
                                        application.getVerifyClient().getVerifiedUser(prefix, phoneNumber);
                                }
                        }
                        
                        @Override
                        public void onError(final com.nexmo.sdk.verify.event.VerifyError errorCode, final String errorMessage) {
                                Log.d(TAG, "onSearchError " + errorCode);
                                showToast("onSearchError.message: " + errorMessage);
                        }
                        
                        @Override
                        public void onException(IOException exception) {
                                Log.d(TAG, "onException " + exception.getMessage());
                                showToast("No internet connectivity.");
                        }
                });
        }
        
        private void showToast(final String message) {
                getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                });
        }
        
        @Override
        public void onDestroy() {
                super.onDestroy();
                FoodleApp application = (FoodleApp) getActivity().getApplication();
                if (application.getVerifyClient() != null)
                        application.getVerifyClient().removeVerifyListeners();
        }
        
        @Override
        public void onResume(){
                super.onResume();
                
                final Activity activity = getActivity();
                final FoodleApp application = (FoodleApp) activity.getApplication();
                application.getVerifyClient().addVerifyListener(new VerifyClientListener() {
                        @Override
                        public void onVerifyInProgress(final VerifyClient verifyClient, final UserObject userObject) {
                        }
                        
                        @Override
                        public void onUserVerified(final VerifyClient verifyClient, final UserObject userObject) {
                                Log.d(TAG, "onUserVerified ");
                                showToast("User verified!");
                        }
                        
                        @Override
                        public void onError(final VerifyClient verifyClient, final com.nexmo.sdk.verify.event.VerifyError errorCode, final UserObject userObject) {
                                Log.d(TAG, "onError " + errorCode);
                                showToast("onError.code: " + errorCode.toString());
                        }
                        
                        @Override
                        public void onException(final IOException exception) {
                                Log.d(TAG, "onException " + exception.getMessage());
                                showToast("No internet connectivity.");
                        }
                });
        }
        
        @Override
        public void onPause(){
                super.onPause();
                
                final Activity activity = getActivity();
                final FoodleApp application = (FoodleApp) activity.getApplication();
                application.getVerifyClient().removeVerifyListeners();
        }
        
}
