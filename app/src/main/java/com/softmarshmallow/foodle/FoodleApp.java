package com.softmarshmallow.foodle;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.nexmo.sdk.NexmoClient;
import com.nexmo.sdk.core.client.ClientBuilderException;
import com.nexmo.sdk.verify.client.VerifyClient;
import com.softmarshmallow.foodle.Views.SMSVerification.NexmoConfig;

//import static com.softmarshmallow.foodle.Views.SMSVerification.MyFirebaseInstanceIDService.INTENT_EXTRA_PUSH_TOKEN;


public class FoodleApp extends Application
{
        private static Context context;
        public static final String URL = "http://52.78.115.181:7001/";



        @Override
        public void onCreate() {
                super.onCreate();
                // app context
                FoodleApp.context = getApplicationContext();

                acquireVerifyClient();
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
        public static final String TAG = FoodleApp.class.getSimpleName();
        private VerifyClient verifyClient;
        private NexmoClient nexmoClient;
        public VerifyClient getVerifyClient() {
                return this.verifyClient;
        }
        public void acquireVerifyClient() {
            if (TextUtils.isEmpty(NexmoConfig.NexmoAppId) || TextUtils.isEmpty(NexmoConfig.NexmoSharedSecretKey)) {
                Log.e(TAG, "You must supply valid appId and sharedSecretKey, provided by Nexmo");
                return;
            }
            NexmoClient nexmoClient = null;
            try {
                nexmoClient = new NexmoClient.NexmoClientBuilder()
                        .context(context)
                        .applicationId(NexmoConfig.NexmoAppId)
                        .sharedSecretKey(NexmoConfig.NexmoSharedSecretKey)
                        .build();
            } catch (ClientBuilderException e) {
                e.printStackTrace();
                return;
            }
            this.verifyClient = new VerifyClient(nexmoClient);
        }

}
