package com.softmarshmallow.foodle.Models.MockDataSource;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreReviewModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MockDataSource
{
        
        static String defaultUserID = "DefaultUser";
        
        static StoreContainerModel TestStore_1;
        public final static StoreContainerModel getTestStore_1(){
                if (TestStore_1 != null){
                        return TestStore_1;
                }else {
                        TestStore_1 = new StoreContainerModel();
        
                        TestStore_1.setMenus(
                                new ArrayList<MenuModel>()
                                {{
                                        add(
                                                new MenuModel()
                                                        .setMenuName("Test Menu1")
                                                        .setMenuShortDescription("the best we surve.")
                                                        .setMenuMainPhotoUrl(
                                                                "http://www.seriouseats.com/recipes/assets_c/2013/01/20130109-236426-sandwiched-avocado-arugula-and-walnut-sandwich-thumb-625xauto-298252.jpg")
                                                        .setMenuPrice(3500)
                                                        .setMenuCalories(220)
                                        );
                                        add(
                                                new MenuModel()
                                                        .setMenuName("Test Menu1")
                                                        .setMenuShortDescription("the best we surve.")
                                                        .setMenuMainPhotoUrl(
                                                                "https://mobile-cuisine.com/wp-content/uploads/2013/01/spaghetti-with-meat-sauce.jpg")
                                                        .setMenuPrice(3500)
                                                        .setMenuCalories(220));
                                }}
                        )
                                .setStoreReviews(
                                        new ArrayList<StoreReviewModel>()
                                        {
                                                {
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date())
                                                        );
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                }
                                        }
                                )
                                .setStorePhotoUrls(
                                        new ArrayList<String>()
                                        {{
                                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                        }}
                                )
                                .setId("Pizza_Planet_MOCK_Key")
                                .setStoreName("Pizza Planet [MOCK]")
                                .setStoreShortDescription("shut up and eat!")
                                .setStoreFullDescription(
                                        "shut up your little mouth and put some shit in it!!")
                                .setStoreLocation("35.9078, 127.7669")
                                .setStoreAddress(
                                        "South korea, Jeju, Jeju city, jeju international airport")
                                .setStorePhoneNumber("064-0000-0000")
                
                                .setStoreExtraContacts("facebook : none");
        
                        return TestStore_1;
                }
                
        }
        
        
        static StoreContainerModel TestStore_Caseys;
        public final static StoreContainerModel getTestStore_Caseys(){
                if (TestStore_Caseys != null){
                        return TestStore_Caseys;
                }else {
                        TestStore_Caseys = new StoreContainerModel();
                        TestStore_Caseys       .setMenus(
                                new ArrayList<MenuModel>()
                                {{
                                        add(
                                                new MenuModel()
                                                        .setMenuName("Pizza")
                                                        .setMenuShortDescription("the best we surve.")
                                                        .setMenuMainPhotoUrl(
                                                                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7L1NJYFFLyn4gfmXtiipaabIPkMAv2UzrFeumgjHv5_HLl549EA")
                                                        .setMenuPrice(3500)
                                                        .setMenuCalories(220)
                                        );
                                        add(
                                                new MenuModel()
                                                        .setMenuName("Test Menu1")
                                                        .setMenuShortDescription("the best we surve.")
                                                        .setMenuMainPhotoUrl(
                                                                "https://mobile-cuisine.com/wp-content/uploads/2013/01/spaghetti-with-meat-sauce.jpg")
                                                        .setMenuPrice(3500)
                                                        .setMenuCalories(220));
                                }}
                        )
                                .setStoreReviews(
                                        new ArrayList<StoreReviewModel>()
                                        {
                                                {
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date())
                                                        );
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                }
                                        }
                                )
                                .setStorePhotoUrls(
                                        new ArrayList<String>()
                                        {{
                                                add("https://s-media-cache-ak0.pinimg.com/originals/fd/d6/b2/fdd6b21a7b3d6abe33e48372010f0855.jpg");
                                        }}
                                )
                                .setId("Caseys_MOCK_Key")
                                .setStoreName("Casey's")
                                .setStoreShortDescription("old world craft pies. since 2009")
                                .setStoreFullDescription(
                                        "shut up your little mouth and put some shit in it!!")
                                .setStoreLocation("35.9078, 127.7669")
                                .setStoreAddress(
                                        "South korea, Jeju, Jeju city, jeju international airport")
                                .setStorePhoneNumber("064-0000-0000")
                                .setStoreExtraContacts("facebook : none");
                        return TestStore_Caseys;
                }
        }
         
        
        static StoreContainerModel TestStore_3;
        public final static StoreContainerModel getTestStore_3(){
                if (TestStore_3 != null){
                        return TestStore_3;
                }else {
                        TestStore_3 = new StoreContainerModel();
                        TestStore_3
                                .setMenus(
                                        new ArrayList<MenuModel>()
                                        {{
                                                add(
                                                        new MenuModel()
                                                                .setMenuName("Test Menu1")
                                                                .setMenuShortDescription("the best we surve.")
                                                                .setMenuMainPhotoUrl(
                                                                        "http://www.seriouseats.com/recipes/assets_c/2013/01/20130109-236426-sandwiched-avocado-arugula-and-walnut-sandwich-thumb-625xauto-298252.jpg")
                                                                .setMenuPrice(3500)
                                                                .setMenuCalories(220)
                                                );
                                                add(
                                                        new MenuModel()
                                                                .setMenuName("Test Menu1")
                                                                .setMenuShortDescription("the best we surve.")
                                                                .setMenuMainPhotoUrl(
                                                                        "http://www.seriouseats.com/recipes/assets_c/2013/01/20130109-236426-sandwiched-avocado-arugula-and-walnut-sandwich-thumb-625xauto-298252.jpg")
                                                                .setMenuPrice(3500)
                                                                .setMenuCalories(220));
                                        }}
                                )
                                .setStoreReviews(
                                        new ArrayList<StoreReviewModel>()
                                        {
                                                {
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date())
                                                        );
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                }
                                        }
                                )
                                .setStorePhotoUrls(
                                new ArrayList<String>()
                                {{
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                }}
                        )
                                
                                .setId("Coco_MOCK_Key")
                                .setStoreName("Coco [MOCK]")
                                .setStoreShortDescription("shut up and eat!")
                                .setStoreFullDescription(
                                        "shut up your little mouth and put some shit in it!!")
                                .setStoreLocation("35.9078, 127.7669")
                                .setStoreAddress(
                                        "South korea, Jeju, Jeju city, jeju international airport")
                                .setStorePhoneNumber("064-0000-0000")
                                .setStoreExtraContacts("facebook : none");
                        
                        return TestStore_3;
                }
                
        }
               
        
        static StoreContainerModel TestStore_4;
        public final static StoreContainerModel getTestStore_4(){
                if (TestStore_4 != null){
                        return TestStore_4;
                }else {
                        TestStore_4 = new StoreContainerModel();
                        TestStore_4
                                .setMenus(
                                        new ArrayList<MenuModel>()
                                        {{
                                                add(
                                                        new MenuModel()
                                                                .setMenuName("Test Menu1")
                                                                .setMenuShortDescription("the best we surve.")
                                                                .setMenuMainPhotoUrl(
                                                                        "http://www.seriouseats.com/recipes/assets_c/2013/01/20130109-236426-sandwiched-avocado-arugula-and-walnut-sandwich-thumb-625xauto-298252.jpg")
                                                                .setMenuPrice(3500)
                                                                .setMenuCalories(220)
                                                );
                                                add(
                                                        new MenuModel()
                                                                .setMenuName("Test Menu1")
                                                                .setMenuShortDescription("the best we surve.")
                                                                .setMenuMainPhotoUrl(
                                                                        "http://www.seriouseats.com/recipes/assets_c/2013/01/20130109-236426-sandwiched-avocado-arugula-and-walnut-sandwich-thumb-625xauto-298252.jpg")
                                                                .setMenuPrice(3500)
                                                                .setMenuCalories(220));
                                        }}
                                )
                                .setStoreReviews(
                                        new ArrayList<StoreReviewModel>()
                                        {
                                                {
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date())
                                                        );
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                        add(
                                                                (StoreReviewModel) new StoreReviewModel()
                                                                        .setRating(100)
                                                                        .setReviewContent("this is soooo cool!!")
                                                                        .setReviewerId(defaultUserID)
                                                                        .setCreatedTime(new Date()));
                                                }
                                        }
                                ).setStorePhotoUrls(
                                new ArrayList<String>()
                                {{
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                        add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                }}
                        )
                                
                                .setId("Move_em_MOCK_Key")
                                .setStoreName("Move e'm [MOCK]")
                                .setStoreShortDescription("shut up and eat!")
                                .setStoreFullDescription(
                                        "shut up your little mouth and put some shit in it!!")
                                .setStoreLocation("35.9078, 127.7669")
                                .setStoreAddress(
                                        "South korea, Jeju, Jeju city, jeju international airport")
                                .setStorePhoneNumber("064-0000-0000")
                                .setStoreExtraContacts("facebook : none")
                        ;
                        return TestStore_4;
                }
        }
                
        
        
        public static List<StoreContainerModel> AllStoreDatas = new ArrayList<>(
                Arrays.asList(
                        getTestStore_1(),
                        getTestStore_Caseys(),
                        getTestStore_3(),
                        getTestStore_4()
                )
        );
        public static List<StoreContainerModel> OwnedStoreDatas = new ArrayList<>(
                Arrays.asList(
                        getTestStore_1(),
                        getTestStore_Caseys(),
                        getTestStore_3(),
                        getTestStore_4()
                )
        );
        public static List<StoreContainerModel> LikedStoreDatas = new ArrayList<>(
                Arrays.asList(
                        getTestStore_1(),
                        getTestStore_Caseys(),
                        getTestStore_3(),
                        getTestStore_4()
                )
        );
}
