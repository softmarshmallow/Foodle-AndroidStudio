package com.softmarshmallow.foodle.Services;

import com.softmarshmallow.foodle.Models.NotificationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by UZU on 02/09/2017.
 */

public class NotificationService
{
        static String alertImageUrl = "https://www.iconsfind.com/wp-content/uploads/2016/04/20160405_570332508f4dc-210x210.png";
        static String errorImageUrl = "https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-128.png";
        static String okImageUrl = "http://www.freeiconspng.com/uploads/sign-check-flat-icon-png-29.png";
        
        public static List<NotificationModel> GetAllNotifications() {
                
                
                return new ArrayList<NotificationModel>()
                {{
                        add(new NotificationModel()
                                .setNotificationContent("따끈 주의! 주문하신 피자 나왔습니다~")
                                .setNotificationTime(new Date())
                                .setNotificationThumbnailUrl(
                                        okImageUrl));
                        add(new NotificationModel()
                                .setNotificationContent("___피자 주문 확인. 12000원 결제가 완료되었습니다.")
                                .setNotificationTime(new Date())
                                .setNotificationThumbnailUrl(
                                        alertImageUrl));
                        add(new NotificationModel()
                                .setNotificationContent("우주네 피자 접근 알림. 좋아요 하신 우주네 피자가 이곳으로 왔어요!")
                                .setNotificationTime(new Date())
                                .setNotificationThumbnailUrl(
                                        errorImageUrl));
                        add(new NotificationModel()
                                .setNotificationContent("화원가입을 축하드립니다!")
                                .setNotificationTime(new Date())
                                .setNotificationThumbnailUrl(
                                        alertImageUrl));
                }};
        }
        
}
