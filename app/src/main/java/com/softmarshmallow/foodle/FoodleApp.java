package com.softmarshmallow.foodle;

import android.app.Application;
import android.content.Context;

public class FoodleApp extends Application
{
        private static Context context;

        @Override
        public void onCreate() {
                super.onCreate();
                // app context
                FoodleApp.context = getApplicationContext();

                // CalligraphyConfig
                /*
                CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/AppleSDGothicNeo_Thin.otf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                );*/

        }

        public static Context getAppContext() {
                return FoodleApp.context;
        }

}
