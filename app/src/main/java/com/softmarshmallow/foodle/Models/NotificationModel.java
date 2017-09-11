package com.softmarshmallow.foodle.Models;

import java.util.Date;

/**
 * Created by UZU on 03/09/2017.
 */

public class NotificationModel
{
        public String NotificationThumbnailUrl;
        public String NotificationContent;
        public Date NotificationTime;
        
        public NotificationModel setNotificationThumbnailUrl(String notificationThumbnailUrl) {
                NotificationThumbnailUrl = notificationThumbnailUrl;
                return this;
        }
        
        public NotificationModel setNotificationContent(String notificationContent) {
                NotificationContent = notificationContent;
                return this;
        }
        
        public NotificationModel setNotificationTime(Date notificationTime) {
                NotificationTime = notificationTime;
                return this;
        }
}
