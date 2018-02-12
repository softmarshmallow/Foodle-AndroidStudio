package com.softmarshmallow.foodle.CustomViews.OptionItemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by softmarshmallow on 2/6/18.
 */

public class OptionItemView extends ConstraintLayout
{
        @BindView(R.id.optionImageView)
        ImageView optionImageView;
        @BindView(R.id.optionNameTextView)
        TextView optionNameTextView;
        
        public OptionItemView(Context context) {
                super(context);
                init(null, 0);
        }
        
        public OptionItemView(Context context, AttributeSet attrs) {
                super(context, attrs);
                init(attrs, 0);
        }
        
        public OptionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(attrs, defStyleAttr);
        }
        
        
        String mOptionText = "Option";
        int mOptionImage;
        
        private void init(AttributeSet attrs, int defStyle) {
                View view = inflate(getContext(), R.layout.view_option_item, this);
                ButterKnife.bind(this, view);
                
                
                //region Load attributes
                final TypedArray typedArray = getContext().obtainStyledAttributes(
                        attrs, R.styleable.OptionItemView, defStyle, 0);
        
                if (typedArray.hasValue(R.styleable.OptionItemView_optionNameText)) {
                        mOptionText = typedArray.getString(
                                R.styleable.OptionItemView_optionNameText);
                }
        
                if (typedArray.hasValue(R.styleable.OptionItemView_optionImage)) {
                        mOptionImage = typedArray.getInteger(R.styleable.OptionItemView_optionImage,
                                R.drawable.ic_x);
                }
                
                typedArray.recycle();
                // endregion
        
                optionImageView.setImageResource(mOptionImage);
                optionNameTextView.setText(mOptionText);
        }
        
}
