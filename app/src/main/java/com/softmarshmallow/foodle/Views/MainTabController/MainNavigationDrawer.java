package com.softmarshmallow.foodle.Views.MainTabController;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Festival.FestivalDetailViewActivity;
import com.softmarshmallow.foodle.Views.StoreCreator_Deprecated.MenuCreatorActivity_Deprecated;
import com.softmarshmallow.foodle.Views.StoreCreator_Deprecated.StoreCreatorActivity_Deprecated;
import com.softmarshmallow.foodle.Views.StoreEditor.StoreEditorActivity;
import com.softmarshmallow.foodle.Views.Test.TestActivity;



public class MainNavigationDrawer
{
        
        private static final String TAG = "MainNavigationDrawer";
        
        enum NavigationItemType
        {
                Home(0),
                Settings(1),
                StoreCreatorPage(2),
                Option1(3),
                Option2(4),
                Option3(5),;
                
                
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
                PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(
                        NavigationItemType.Home.getValue())
                        .withName("Home");
                SecondaryDrawerItem settings = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Settings.getValue())
                        .withName("Settings");
                SecondaryDrawerItem storeCreator = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.StoreCreatorPage.getValue())
                        .withName("Create Store");
                SecondaryDrawerItem option1 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Option1.getValue())
                        .withName("option1");
                SecondaryDrawerItem option2 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Option2.getValue())
                        .withName("option2");

                SecondaryDrawerItem option3 = new SecondaryDrawerItem().withIdentifier(
                        NavigationItemType.Option3.getValue())
                        .withName("option3");
        
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
        
                DrawerBuilder builder = new DrawerBuilder()
                        .withActivity(activity)
                        .withToolbar(toolbar)
                        .withActionBarDrawerToggleAnimated(true)
                        .withActionBarDrawerToggle(true)
                        .withAccountHeader(headerResult)
                        .addDrawerItems(
                                home,
                                new DividerDrawerItem(),
                                settings,
                                storeCreator,
                                option1,
                                option2,
                                option3
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
                                                case Home:
                                                        intent = new Intent(activity, TestActivity.class);
                                                         activity.startActivity(intent);
                                                        break;
                                                case Settings:

                                                
                                                        break;
                                                case StoreCreatorPage:
                                                        intent = new Intent(activity,
                                                                StoreCreatorActivity_Deprecated.class);
                                                        activity.startActivity(intent);
                                                        break;
                                        
                                                case Option1:
                                                        intent = new Intent(activity,
                                                                MenuCreatorActivity_Deprecated.class);
                                                        activity.startActivity(intent);
                                                        break;
                                        
                                                case Option2:
                                                        intent = new Intent(activity,
                                                                FestivalDetailViewActivity.class);
                                                        activity.startActivity(intent);
                                                        break;
                                                case Option3:
                                                        intent = new Intent(activity,
                                                                StoreEditorActivity.class);
                                                        activity.startActivity(intent);


                                                        break;
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
