package com.softmarshmallow.foodle.Views.SMSVerification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.softmarshmallow.foodle.CustomViews.NonSwipeableViewPager.NonSwipeableViewPager;
import com.softmarshmallow.foodle.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SMSVerificationActivity extends AppCompatActivity
{
        
        @BindView(R.id.containerViewPager)
        NonSwipeableViewPager containerViewPager;
        
        
        public static SMSVerificationActivity Instance;
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_smsverification);
                ButterKnife.bind(this);
                Instance = this;
        
                initContainerViewPager();
        }
        
        
        SMSVerification_PhoneNumberEnterViewFragment smsVerification_phoneNumberEnterViewFragment = new SMSVerification_PhoneNumberEnterViewFragment();
        SMSVerification_PinCodeEnterViewFragment smsVerification_pinCodeEnterViewFragment = new SMSVerification_PinCodeEnterViewFragment();
        Map<Integer, Fragment> pageMapping = new HashMap<Integer, Fragment>(){
                {
                        put(0, smsVerification_phoneNumberEnterViewFragment);
                        put(1, smsVerification_pinCodeEnterViewFragment);
                }
        };
        
        void initContainerViewPager(){
                containerViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
                {
                        @Override
                        public Fragment getItem(int i) {
                                return pageMapping.get(i);
                        }
        
                        @Override
                        public int getCount() {
                                return pageMapping.size();
                        }
                });
        }
        
        
        public void showPinEnterView(){
                containerViewPager.setCurrentItem(1);
        }
        
        
        public void showDialEnterView(){
                containerViewPager.setCurrentItem(0);
        }
        
        
}
