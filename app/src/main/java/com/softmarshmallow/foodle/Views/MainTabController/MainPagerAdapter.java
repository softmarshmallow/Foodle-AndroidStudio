package com.softmarshmallow.foodle.Views.MainTabController;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.softmarshmallow.foodle.Views.ExtraOptions_Deprecated.ExtraOptionsViewFragment;
import com.softmarshmallow.foodle.Views.FeaturedViewV2.FeaturedViewFragment;
import com.softmarshmallow.foodle.Views.Mypage.MypageFragment;
import com.softmarshmallow.foodle.Views.NotificationsPage.NotificationsViewFragment;
import com.softmarshmallow.foodle.Views.Search.IntegratedSearchViewFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter
{

        private static final String TAG = "MainPagerAdapter";

        public MainPagerAdapter(FragmentManager fm) {
                super(fm);
        }


        @Override
        public int getCount() {
                return MainTabsType.values().length;
        }
        
        Fragment featuredViewFragmentV2 = new FeaturedViewFragment();
        
        // Fragment testFragment = new TestFragment();
        Fragment storeSearchViewFragment = new IntegratedSearchViewFragment();
        Fragment mypageFragment = new MypageFragment();
        Fragment notificationsPageFragment = new NotificationsViewFragment();
        Fragment extraOptionsViewFragment = new ExtraOptionsViewFragment();


        @Override
        public Fragment getItem(int position) {
                MainTabsType tabType = MainTabsType.MainTabsPositionMapping.get(position);
                Log.d(TAG, "tab == null : " + (tabType  == null));
                Log.d(TAG, "position : " + position + ", tab : " + tabType);


                switch (tabType)
                {
                        case Featured:
                                     return featuredViewFragmentV2;
                        case Search:
                                return storeSearchViewFragment;
                        case MyPage:
                                return mypageFragment;
                        case Notifications:
                                return notificationsPageFragment;
                        case More:
                                return extraOptionsViewFragment;
                        default:
                                return null;
                }
        }


}
