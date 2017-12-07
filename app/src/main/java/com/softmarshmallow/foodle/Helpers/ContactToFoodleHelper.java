package com.softmarshmallow.foodle.Helpers;

import android.content.Context;
import android.content.Intent;

/**
 * Created by uzu on 9/25/17.
 */

public class ContactToFoodleHelper
{
        public static void SendEmailToFoodle(Context context){
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"woojoo@softmarshmallow.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "푸들에게 한마디");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "내용을 입력해주세요");
                context.startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
        }
}
