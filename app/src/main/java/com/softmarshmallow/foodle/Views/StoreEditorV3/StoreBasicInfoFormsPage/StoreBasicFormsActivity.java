package com.softmarshmallow.foodle.Views.StoreEditorV3.StoreBasicInfoFormsPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView.DefaultEditTextContainerView;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.ValidationRules.NotEmptyQuickRule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreBasicFormsActivity extends AppCompatActivity
{
        
        private static final String TAG = StoreBasicFormsActivity.class.getSimpleName();
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        @BindView(R.id.storeNameEditTextContainer)
        DefaultEditTextContainerView storeNameEditTextContainer;
        @BindView(R.id.storeShortPromotionEditTextContainer)
        DefaultEditTextContainerView storeShortPromotionEditTextContainer;
        @BindView(R.id.storeLongDescriptionEditTextContainer)
        DefaultEditTextContainerView storeLongDescriptionEditTextContainer;
        
        Validator validator = new Validator(this);
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_basic_forms);
                ButterKnife.bind(this);
        
        
                initEditTextFeilds();
        }
        
        
        void initEditTextFeilds() {
                
                TextWatcher textWatcher = new TextWatcher()
                {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        
                        }
                
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        
                        }
                
                        @Override
                        public void afterTextChanged(Editable editable) {
                                validate();
                        }
                };
                storeNameEditTextContainer.contentEditText.addTextChangedListener(textWatcher);
                storeShortPromotionEditTextContainer.contentEditText.addTextChangedListener(textWatcher);
                storeLongDescriptionEditTextContainer.contentEditText.addTextChangedListener(textWatcher);
        
        
                initValidator();
        
        }
        
        void initValidator(){
        
                validator.put(storeNameEditTextContainer.contentEditText, new NotEmptyQuickRule());
                validator.put(storeShortPromotionEditTextContainer.contentEditText, new NotEmptyQuickRule());
                validator.put(storeLongDescriptionEditTextContainer.contentEditText, new NotEmptyQuickRule());
                
                
                validator.setValidationListener(new Validator.ValidationListener()
                {
                        @Override
                        public void onValidationSucceeded() {
                                // Show Button
                                Toast.makeText(getApplicationContext(), "Validated!!", Toast.LENGTH_SHORT).show();
                        }
                
                        @Override
                        public void onValidationFailed(List<ValidationError> errors) {
                                for (ValidationError error : errors){
                                        EditText editText = (EditText)error.getView();
                                        editText.setError(error.getCollatedErrorMessage(getApplicationContext()));
                                }
                        }
                });
        }
        
        
        void validate(){
                Log.d(TAG, "validating...");
                validator.validate();
        }
        
        @Override
        public void onBackPressed() {
                onExitButtonClicked();
        }
        
        @OnClick(R.id.exitButton)
        public void onExitButtonClicked() {
                finish();
        }
}
