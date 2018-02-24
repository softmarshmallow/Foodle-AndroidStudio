package com.softmarshmallow.foodle.Views.MainTabController.MainTabcontrollerV2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.softmarshmallow.foodle.Views.ExtraOptions_Deprecated.Option_Tab_Fragment;
import com.softmarshmallow.foodle.Views.FeaturedViewV3.FeaturedViewFragmentV3;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabsType;
import com.softmarshmallow.foodle.Views.Mypage.MypageFragment;
import com.softmarshmallow.foodle.Views.NotificationsPage.NotificationsViewFragment;
import com.softmarshmallow.foodle.Views.Search.IntegratedSearchViewFragment;

public class MainPagerAdapter_v2 extends FragmentStatePagerAdapter
{

        private static final String TAG = "MainPagerAdapter";

        public MainPagerAdapter_v2(FragmentManager fm) {
                super(fm);
        }


        @Override
        public int getCount() {
                return MainTabsType.values().length;
        }
        
        Fragment featuredViewFragment = new FeaturedViewFragmentV3();
        
        // Fragment testFragment = new TestFragment();
        Fragment storeSearchViewFragment = new IntegratedSearchViewFragment();
        Fragment mypageFragment = new MypageFragment();
        Fragment notificationsPageFragment = new NotificationsViewFragment();
        Fragment option_tab_fragment = new Option_Tab_Fragment();


        @Override
        public Fragment getItem(int position) {
                MainTabsType tabType = MainTabsType.MainTabsPositionMapping.get(position);
                Log.d(TAG, "tab == null : " + (tabType  == null));
                Log.d(TAG, "position : " + position + ", tab : " + tabType);


                switch (tabType)
                {
                        case Featured:
                                     return featuredViewFragment;
                        case Search:
                                return storeSearchViewFragment;
                        case MyPage:
                                return mypageFragment;
                        case Notifications:
                                return notificationsPageFragment;
                        case More:
                                return option_tab_fragment;
                        default:
                                return null;
                }
        }


}
