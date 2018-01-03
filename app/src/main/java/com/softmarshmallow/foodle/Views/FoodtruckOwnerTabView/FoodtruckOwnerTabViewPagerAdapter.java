package com.softmarshmallow.foodle.Views.FoodtruckOwnerTabView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.softmarshmallow.foodle.Views.MainTabController.MainTabsType;


public class FoodtruckOwnerTabViewPagerAdapter extends FragmentStatePagerAdapter
{
        
        private static final String TAG = "FoodtruckPagerAdapter";
        
        public FoodtruckOwnerTabViewPagerAdapter(FragmentManager fm) {
                super(fm);
        }
        
        
        @Override
        public int getCount() {
                return MainTabsType.values().length;
        }
        
        // FIXME: 12/18/17 REPLACE FRAGMENTS
        /*
        Fragment featuredViewFragmentV2 = new FeaturedViewFragment();
        
        // Fragment testFragment = new TestFragment();
        Fragment storeSearchViewFragment = new IntegratedSearchViewFragment();
        Fragment mypageFragment = new MypageFragment();
        Fragment notificationsPageFragment = new NotificationsViewFragment();
        Fragment extraOptionsViewFragment = new ExtraOptionsViewFragment();
        */
        
        @Override
        public Fragment getItem(int position) {
                FoodtruckOwnerTabsType tabType = FoodtruckOwnerTabsType.MainTabsPositionMapping.get(position);
                Log.d(TAG, "tab == null : " + (tabType  == null));
                Log.d(TAG, "position : " + position + ", tab : " + tabType);
                
                return new Fragment();
               /* switch (tabType)
                {
                
                }*/
        }
}
