package com.softmarshmallow.foodle.Views.MainTabController;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.softmarshmallow.foodle.Helpers.ContactToFoodleHelper;
import com.softmarshmallow.foodle.Views.Mypage.ProfileEditorActivity;
import com.softmarshmallow.foodle.Views.QR_ReaderView.QR_ReaderActivity;
import com.softmarshmallow.foodle.Views.SMSVerification.SMSVerification_PhoneNumberEnterViewActivity;
import com.softmarshmallow.foodle.Views.StoreEditorV3.StoreEditorLandingPage.StoreEditorLandingPageActivity;
import com.softmarshmallow.foodle.Views.StoreEditor_Deprecated.StoreCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreMapsView.StoreMapsViewActivity;


public class MainNavigationDrawer
{
        
        private static final String TAG = "MainNavigationDrawer";
        
        enum NavigationItemType
        {
//                Home(0),
                Settings(1),
                StoreCreatorPage(2),
                EditProfile(3),
                ViewOnMaps(4),
                ContactToFoodle(5),
                Extra1(6),
                //region Dev options
                DevOption1(1001),
                DevOption2(1002),
                DevOption3(1003),
                DevOption4(1004);
                //endregion
                
                
                int value;
                
                NavigationItemType(int value) {
                        this.value = value;
                }
                
                public int getValue() {
                        return value;
                }
                
                
                public static NavigationItemType getEnum(int value) {
                        for (NavigationItemType e : NavigationItemType.values()) {
                                if (e.getValue() == value) {
                                        return e;
                                }
                        }
                        return null;
                }
        }
        
        static DrawerBuilder CreateNavigationDrawer(final Activity activity, Toolbar toolbar) {
               
                SecondaryDrawerItem settings = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Settings.getValue())
                        .withName("설정");
                
                SecondaryDrawerItem storeCreator = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.StoreCreatorPage.getValue())
                        .withName("푸드트럭 등록하기");
                
                SecondaryDrawerItem editProfile = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.EditProfile.getValue())
                        .withName("프로필 수정");
                
                SecondaryDrawerItem option2 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.ViewOnMaps.getValue())
                        .withName("지도에서 보기");

                SecondaryDrawerItem option3 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.ContactToFoodle.getValue())
                        .withName("연락하기");
        
                SecondaryDrawerItem option4 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Extra1.getValue())
                        .withName("QR주문");
        
                
                
                /*
                // Create the AccountHeader
                AccountHeader headerResult = new AccountHeaderBuilder()
                        .withActivity(activity)
                        .withHeaderBackground(R.drawable.app_icon_red_v3)
                        .withHeaderBackgroundScaleType(ImageView.ScaleType.CENTER_CROP)
                        .addProfiles(
                                new ProfileDrawerItem().withName("UZU")
                                        .withEmail("woojoo@softmarshmallow.com")
                                        .withIcon(R.drawable.like_icon)
                        )
                        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener()
                        {
                                @Override
                                public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                                        return true;
                                }
                        })
                        .build();
                //create the drawer and remember the `Drawer` result object
                */

        
                DrawerBuilder builder = new DrawerBuilder()
                        .withActivity(activity)
                        .withToolbar(toolbar)
                        .withActionBarDrawerToggleAnimated(true)
                        .withActionBarDrawerToggle(true)
//                        .withAccountHeader(headerResult)
                        .addDrawerItems(
//                                settings,
                                storeCreator,
                                editProfile,
                                option2,
                                option3,
                                option4,
                                new DividerDrawerItem(),
                                //region DevOptions
                                new SecondaryDrawerItem().withIdentifier(NavigationItemType.DevOption1.getValue()).withName("Dev1 - StoreEditorV3"),
                                new SecondaryDrawerItem().withIdentifier(NavigationItemType.DevOption2.getValue()).withName("Dev2 - SMS_Verification"),
                                new SecondaryDrawerItem().withIdentifier(NavigationItemType.DevOption3.getValue()).withName("Dev3 - NA")
                                //endregion



                        )
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
                        {
                        
                                @Override
                                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                
                                        // do something with the clicked item :D
                                        Log.d(TAG,
                                                "onItemClick : position = " + position + ", identifier = " + drawerItem.getIdentifier());
                                        NavigationItemType navigationItemType = NavigationItemType.getEnum(
                                                (int) drawerItem.getIdentifier());
                                
                                        Intent intent;
                                        switch (navigationItemType) {
                                                
                                                case Settings:
                                                        break;
                                                case StoreCreatorPage:
                                                        intent = new Intent(activity,
                                                                StoreCreatorActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                        
                                                case EditProfile:
                                                        intent = new Intent(activity,
                                                                ProfileEditorActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                        
                                                case ViewOnMaps:
                                                        intent = new Intent(activity,
                                                                StoreMapsViewActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                                case ContactToFoodle:
                                                        ContactToFoodleHelper.SendEmailToFoodle(activity);
                                                        break;
                                                        
                                                //region Devoptions
                          
                                                case Extra1:
        
                                                        intent = new Intent(activity,
                                                                QR_ReaderActivity.class);
                                                        activity.startActivity(intent);
                                                        /*
                                                        IntentIntegrator integrator = new IntentIntegrator(activity);
                                                        integrator.setBarcodeImageEnabled(false);
                                                        integrator.setOrientationLocked(true);
                                                        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                                                        integrator.setPrompt("");
                                                        integrator.setBeepEnabled(false);
                                                        integrator.initiateScan();*/
                                                        break;
        
                                                case DevOption1:
                                                        intent = new Intent(activity,
                                                                StoreEditorLandingPageActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                                case DevOption2:
                                                        intent = new Intent(activity,
                                                                SMSVerification_PhoneNumberEnterViewActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                                case DevOption3:
                                                        break;
                                                case DevOption4:
                                                        break;
                                                //endregion
        
                                        }
                                        
                                        return true;
                                }
                        });
                
                return builder;
        
        }
        
        public static View GetNavigationDrawerView(final Activity activity, Toolbar toolbar) {
                DrawerBuilder builder = CreateNavigationDrawer( activity,toolbar);
                return builder.buildView().getSlider();
        }
        
        public static void DrawNavigationBar(final Activity activity, Toolbar toolbar) {
                DrawerBuilder builder = CreateNavigationDrawer( activity,toolbar);
                 builder.build();
        }
        
        
}
