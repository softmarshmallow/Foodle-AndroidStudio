package com.softmarshmallow.foodle.Views.MainTabController.MainTabcontrollerV2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.MainTabController.MainPagerAdapter;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabsType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import devlight.io.library.ntb.NavigationTabBar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;


public class MainTabControllerActivity_v2 extends AppCompatActivity
{
        public static MainTabControllerActivity_v2 instance;
        
        @BindView(R.id.pager)
        public ViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                instance = this;


                setTheme(R.style.AppTheme_NoActionBar);
                setContentView(R.layout.activity_main_tab_controller_v2);
                ButterKnife.bind(this);
                
                InitTabBar();
        }


        private void InitTabBar() {
                PagerAdapter adapter = new MainPagerAdapter_v2(getSupportFragmentManager());
                viewPager.setAdapter(adapter);
                
                final String[] colors = getResources().getStringArray(R.array.default_preview);
                
                final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(
                        R.id.ntb_horizontal);
                
                final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
                models.add(
                        new NavigationTabBar.Model.Builder(
                                getResources().getDrawable(R.drawable.restaurant_icon),
                                Color.parseColor(colors[0]))
                                .title("Featured")
                                .badgeTitle("New")
                                .build()
                );
                models.add(
                        new NavigationTabBar.Model.Builder(
                                getResources().getDrawable(R.drawable.magnifying_glass_icon),
                                Color.parseColor(colors[0]))

                                .title("Search")
                                // .badgeTitle("with")
                                .build()
                );
                models.add(

                                new NavigationTabBar.Model.Builder(
                                getResources().getDrawable(R.drawable.id_card_icon),
                                Color.parseColor(colors[0]))
                                .title("My Foodle")
                                // .badgeTitle("state")
                                .build()
                );
                models.add(
                        new NavigationTabBar.Model.Builder(
                                getResources().getDrawable(R.drawable.icon_notification),
                                Color.parseColor(colors[0]))
                                .title("Notifications")
                                .badgeTitle("3")
                                .build()
                );
                models.add(
                        new NavigationTabBar.Model.Builder(
                                getResources().getDrawable(R.drawable.ellipsis_icon),
                                Color.parseColor(colors[0]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                                .title("More")
                                // .badgeTitle("icon")
                                .build()
                );
                
                
                navigationTabBar.setModels(models);
                navigationTabBar.setViewPager(viewPager);
                navigationTabBar.setIsBadged(true);
                navigationTabBar.setBehaviorEnabled(true);
                
                
                navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
                {
                        @Override
                        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                                
                        }
                        
                        @Override
                        public void onPageSelected(final int position) {
                                navigationTabBar.getModels()
                                        .get(position)
                                        .hideBadge();
                        }
                        
                        @Override
                        public void onPageScrollStateChanged(final int state) {
                                
                        }
                });
                
                navigationTabBar.postDelayed(new Runnable()
                {
                        @Override
                        public void run() {
                                for (int i = 0; i < navigationTabBar.getModels()
                                        .size(); i++) {
                                        final NavigationTabBar.Model model = navigationTabBar.getModels()
                                                .get(i);
                                        navigationTabBar.postDelayed(new Runnable()
                                        {
                                                @Override
                                                public void run() {
                                                        model.showBadge();
                                                }
                                        }, i * 100);
                                }
                        }
                }, 500);
        }
        
        public void FocusOnTab(MainTabsType tabType) {
                viewPager.setCurrentItem((tabType.getValue()), true);
        }
        
        
        
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                new MenuInflater(this).inflate(R.menu.menu_main, menu);
                return true;
        }
        
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                        return true;
                }
                
                return super.onOptionsItemSelected(item);
        }
        
        
        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }


        
        void SetTabIconAsSelected(TabLayout.Tab tab) {
                int tabIconColor = (ContextCompat.getColor(getApplicationContext(),
                        R.color.tabUnselectedIconColor));
                tab.getIcon()
                        .setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
        
        
        void SetTabIconAsUnSelected(TabLayout.Tab tab) {
                int tabIconColor = (ContextCompat.getColor(getApplicationContext(),
                        R.color.tabUnselectedIconColor));
                tab.getIcon()
                        .setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
        
        
        //endregion


        //region confirm exit app


        @Override
        public void onBackPressed() {
                AskIfQuitApp();
        }

        void AskIfQuitApp(){
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.quit_app_title))
                        .setContentText(getString(R.string.quit_app_content))
                        .setCancelText(getString(R.string.quit_app_cancel))
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                }
                        })

                        .setConfirmText(getString(R.string.quit_app_confirm))
                        .setConfirmClickListener (new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        finish();
                                }
                        }).show();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//                if(result != null) {
//                        if(result.getContents() == null) {
//
//                        } else {
//                                StoreDetailViewActivity.ShowStoreDetailWithData(this, MockDataSource.getTestStore_1());
//                        }
//                } else {
//                        super.onActivityResult(requestCode, resultCode, data);
//                }
        }


}

