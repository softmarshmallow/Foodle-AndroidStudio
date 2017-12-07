package com.softmarshmallow.foodle.Helpers;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uzu on 9/21/17.
 */

public class StoreExtraContactsBuilder
{
        // NEVER EVER EDIT THIS
        private static final String entrySeperator = "\n";
        private static final String seperator = " : ";
        // NEVER EVER EDIT THIS
        
        public static final String facebookKey = "facebook";
        public static final String instagramKey = "instagram";
        
        public static String BuildExtraContacts(Map<String, String> linkMaps){
                
                String extraContacts = "";
                for(Map.Entry<String, String> entry : linkMaps.entrySet()) {
                        String sociaServiceName = entry.getKey();
                        String socialLink = entry.getValue();
                        
                        String entryString = sociaServiceName + seperator + socialLink;
                        extraContacts +=   entryString + entrySeperator;
                }
                return extraContacts;
        }
        
        
        public static Map<String, String> Parse(String extraContactsRawString){
                HashMap data = new HashMap<String, String>();
                
                String[] entries = extraContactsRawString.split(entrySeperator);
                for (String entry : entries){
                        String[] entrySet = entry.split(seperator);
                        String socialName = entrySet[0];
                        String socialLink = entrySet[1];
                        
                        data.put(socialName, socialLink);
                }
                
                return data;
        }
}

