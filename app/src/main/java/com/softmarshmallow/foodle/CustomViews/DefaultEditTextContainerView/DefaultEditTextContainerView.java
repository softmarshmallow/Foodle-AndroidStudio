package com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by softmarshmallow on 1/23/18.
 */

public class DefaultEditTextContainerView extends ConstraintLayout
{
        @BindView(R.id.titleText)
        public TextView titleText;
        @BindView(R.id.contentEditText)
        public EditText contentEditText;
        
        private String mTitleText = "title";
        private String mHintText = "hint";
        
        
        public DefaultEditTextContainerView(Context context) {
                super(context);
                init(null, 0);
        }
        
        public DefaultEditTextContainerView(Context context, AttributeSet attrs) {
                super(context, attrs);
                init(attrs, 0);
                
        }
        
        public DefaultEditTextContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(attrs, defStyleAttr);
        }
        
        
        private void init(AttributeSet attrs, int defStyle) {
                View view = inflate(getContext(), R.layout.view_default_edit_text_container_view,
                        this);
                ButterKnife.bind(this, view);
                
                //region Load attributes
                final TypedArray typedArray = getContext().obtainStyledAttributes(
                        attrs, R.styleable.DefaultEditTextContainerView, defStyle, 0);
        
                if (typedArray.hasValue(R.styleable.DefaultEditTextContainerView_titleText)) {
                        mTitleText = typedArray.getString(
                                R.styleable.LandingPageSectionItemView_titleText);
                }
        
                if (typedArray.hasValue(R.styleable.DefaultEditTextContainerView_hintText)) {
                        mHintText = typedArray.getString(
                                R.styleable.DefaultEditTextContainerView_hintText);
                }
                
                
                typedArray.recycle();
                // endregion
                
                titleText.setText(mTitleText);
                contentEditText.setHint(mHintText);
        }
        
}
