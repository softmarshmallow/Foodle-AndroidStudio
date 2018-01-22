package com.softmarshmallow.foodle.Views.StoreEditorV3.CustomViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by softmarshmallow on 1/15/18.
 */

public class LandingPageSectionItemView extends ConstraintLayout
{
        private static final String TAG = LandingPageSectionItemView.class.getSimpleName();
        
        @BindView(R.id.descriptionTextView)
        TextView descriptionTextView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.continueButton)
        FancyButton continueButton;
        @BindView(R.id.root)
        ConstraintLayout root;
        
        
        
        private String mTitleText = "Title goes here";
        private String mDescriptionText = "this is a super complex description// maximum of 50 chars";
        private String mButtonText = "Continue";
        
        
        
        public LandingPageSectionItemView(Context context) {
                super(context);
                init(null, 0);
        
        }
        
        public LandingPageSectionItemView(Context context, AttributeSet attrs) {
                super(context, attrs);
                init(attrs, 0);
        }
        
        public LandingPageSectionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(attrs, defStyleAttr);
        }
        
        private void init(AttributeSet attrs, int defStyle) {
                View view = inflate(getContext(), R.layout.view_landing_page_section_item, this);
                ButterKnife.bind(this, view);
        
        
                //region Load attributes
                final TypedArray typedArray = getContext().obtainStyledAttributes(
                        attrs, R.styleable.LandingPageSectionItemView, defStyle, 0);
        
                if (typedArray.hasValue(R.styleable.LandingPageSectionItemView_titleText))
                        mTitleText = typedArray.getString(R.styleable.LandingPageSectionItemView_titleText);
        
                if (typedArray.hasValue(R.styleable.LandingPageSectionItemView_descriptionText))
                        mDescriptionText = typedArray.getString(R.styleable.LandingPageSectionItemView_descriptionText);

                if (typedArray.hasValue(R.styleable.LandingPageSectionItemView_buttonText))
                        mButtonText = typedArray.getString(R.styleable.LandingPageSectionItemView_buttonText);
                
                typedArray.recycle();
                // endregion
                
                titleTextView.setText(mTitleText);
                descriptionTextView.setText(mDescriptionText);
                continueButton.setText(mButtonText);
                
        }
        
        @SuppressLint("ShowToast")
        @OnClick(R.id.continueButton)
        public void onContinueButtonClicked() {
                Toast.makeText(getContext(),"Clicked", Toast.LENGTH_LONG).show();
        }
}
