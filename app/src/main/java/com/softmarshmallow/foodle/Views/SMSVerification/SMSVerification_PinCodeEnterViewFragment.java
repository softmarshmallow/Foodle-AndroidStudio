package com.softmarshmallow.foodle.Views.SMSVerification;


import android.app.Activity;
import android.content.Context;
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
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.VerifyClientListener;
import com.softmarshmallow.foodle.FoodleApp;
import com.softmarshmallow.foodle.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SMSVerification_PinCodeEnterViewFragment extends Fragment
{
        
        
        public static final String TAG = SMSVerification_PinCodeEnterViewFragment.class.getSimpleName();
        private static final int MIN_CODE_SIZE = 4;
        
        @BindView(R.id.PinCodeEditText)
        EditText code_et;
        
        @BindView(R.id.confirmButton)
        FancyButton confirm_btn;
        
        
        public SMSVerification_PinCodeEnterViewFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_sms_verification_pin_code_enter_view, container, false);
                ButterKnife.bind(this, view);
                setup();
                return view;
        }
        
        private void setup() {
        
                final Activity activity = getActivity();
                final FoodleApp application = (FoodleApp) activity.getApplication();
                final InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        
                code_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                // If DONE or Enter were pressed, validate the input.
                                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                                        inputMethodManager.hideSoftInputFromWindow(code_et.getWindowToken(), 0);
                                        Editable codeEdit = code_et.getText();
                                        if (TextUtils.isEmpty(codeEdit.toString()) || codeEdit.toString().length() < MIN_CODE_SIZE)
                                                code_et.setError("Error");
                                        return true;
                                }
                                return false;
                        }
                });
        
                confirm_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                inputMethodManager.hideSoftInputFromWindow(code_et.getWindowToken(), 0);
                                Editable codeEdit = code_et.getText();
                        
                                if (codeEdit != null) {
                                        String pinCode = codeEdit.toString();
                                        if (TextUtils.isEmpty(pinCode) || pinCode.length() < MIN_CODE_SIZE)
                                                code_et.setError("Encorrect!");
                                        else
                                                application.getVerifyClient().checkPinCode(pinCode);
                                }
                        }
                });
        
        
        }
        
        
        private void showToast(final String message) {
                getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                                Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
