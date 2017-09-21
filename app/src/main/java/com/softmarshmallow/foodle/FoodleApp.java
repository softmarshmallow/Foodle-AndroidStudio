package com.softmarshmallow.foodle;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FoodleApp extends MultiDexApplication
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
/*

                // LeakCanary
                if (LeakCanary.isInAnalyzerProcess(this)) {
                        // This process is dedicated to LeakCanary for heap analysis.
                        // You should not init your app in this process.
                        return;
                }
                refWatcher = LeakCanary.install(this);*/
        }

        public static Context getAppContext() {
                return FoodleApp.context;
        }

/*

        private RefWatcher refWatcher;

        public static RefWatcher getRefWatcher(Context context) {
                FoodleApp application = (FoodleApp) context.getApplicationContext();
                return application.refWatcher;
        }
*/

}
