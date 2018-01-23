package com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import com.softmarshmallow.foodle.R;

import butterknife.ButterKnife;

/**
 * Created by softmarshmallow on 1/23/18.
 */

public class DefaultEditTextContainerView extends ConstraintLayout
{
        // TODO Implement All
        
        
        
        
        
        public DefaultEditTextContainerView(Context context) {
                super(context);
        }
        
        public DefaultEditTextContainerView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }
        
        public DefaultEditTextContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
        }
        
        
        private void init(AttributeSet attrs, int defStyle) {
                View view = inflate(getContext(), R.layout.view_landing_page_section_item, this);
                ButterKnife.bind(this, view);
                
                /*
                //region Load attributes
                final TypedArray typedArray = getContext().obtainStyledAttributes(
                        attrs, R.styleable.DefaultEditTextContainerView, defStyle, 0);
                
                typedArray.recycle();
                // endregion
                */
                
        }
}
