package com.softmarshmallow.foodle.ValidationRules;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.QuickRule;

/**
 * Created by softmarshmallow on 1/25/18.
 */

public class NotEmptyQuickRule extends QuickRule<EditText>
{
        
        @Override
        public boolean isValid(EditText view) {
                return !view.getText().toString().isEmpty();
        }
        
        @Override
        public String getMessage(Context context) {
                // ...
                return "this field cannot be empty!";
                
        }
}
