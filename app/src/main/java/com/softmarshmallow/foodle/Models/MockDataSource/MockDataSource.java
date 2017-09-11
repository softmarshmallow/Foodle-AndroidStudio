package com.softmarshmallow.foodle.Models.MockDataSource;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.Models.Store.StoreReviewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by UZU on 21/08/2017.
 */

public class MockDataSource
{
        
        public final static StoreModel TestStore_1 = new StoreModel()
                .setId("Pizza_Planet_MOCK_Key")
                .setStoreName("Pizza Planet [MOCK]")
                .setStoreShortDescription("shut up and eat!")
                .setStoreFullDescription(
                        "shut up your little mouth and put some shit in it!!")
                .setStoreLocation("35.9078, 127.7669")
                .setStoreAddress(
                        "South korea, Jeju, Jeju city, jeju international airport")
                .setStorePhoneNumber("064-0000-0000")
                .setStoreExtraContacts("facebook : none")
                .setStorePhotoUrls(
                        new ArrayList<String>()
                        {{
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                        }}
                )
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
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date())
                                        );
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                }
                        }
                );
        
        
        public final static StoreModel TestStore_Caseys = new StoreModel()
                .setId("Caseys_MOCK_Key")
                .setStoreName("Casey's")
                .setStoreShortDescription("old world craft pies. since 2009")
                .setStoreFullDescription(
                        "shut up your little mouth and put some shit in it!!")
                .setStoreLocation("35.9078, 127.7669")
                .setStoreAddress(
                        "South korea, Jeju, Jeju city, jeju international airport")
                .setStorePhoneNumber("064-0000-0000")
                .setStoreExtraContacts("facebook : none")
                .setStorePhotoUrls(
                        new ArrayList<String>()
                        {{
                                add("https://s-media-cache-ak0.pinimg.com/originals/fd/d6/b2/fdd6b21a7b3d6abe33e48372010f0855.jpg");
                        }}
                )
                .setMenus(
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
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date())
                                        );
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                }
                        }
                );
        
        
        public final static StoreModel TestStore_3 = new StoreModel()
                .setId("Coco_MOCK_Key")
                .setStoreName("Coco [MOCK]")
                .setStoreShortDescription("shut up and eat!")
                .setStoreFullDescription(
                        "shut up your little mouth and put some shit in it!!")
                .setStoreLocation("35.9078, 127.7669")
                .setStoreAddress(
                        "South korea, Jeju, Jeju city, jeju international airport")
                .setStorePhoneNumber("064-0000-0000")
                .setStoreExtraContacts("facebook : none")
                .setStorePhotoUrls(
                        new ArrayList<String>()
                        {{
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                        }}
                )
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
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date())
                                        );
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                }
                        }
                );
        
        
        public final static StoreModel TestStore_4 = new StoreModel()
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
                .setStorePhotoUrls(
                        new ArrayList<String>()
                        {{
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                                add("https://s-media-cache-ak0.pinimg.com/originals/6c/53/57/6c535763bb660db15b7f664582d8fb35.jpg");
                        }}
                )
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
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date())
                                        );
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                        add(
                                                (StoreReviewModel) new StoreReviewModel()
                                                        .setRating(100)
                                                        .setReviewContent("this is soooo cool!!")
                                                        .setReviewer(0)
                                                        .setCreatedTime(new Date()));
                                }
                        }
                );
        
        
        public static List<StoreModel> AllStoreDatas = new ArrayList<>(
                Arrays.asList(
                        TestStore_1,
                        TestStore_Caseys,
                        TestStore_3,
                        TestStore_4
                )
        );
        public static List<StoreModel> OwnedStoreDatas = new ArrayList<>(
                Arrays.asList(
                        TestStore_1,
                        TestStore_Caseys
                
                )
        );
        public static List<StoreModel> LikedStoreDatas = new ArrayList<>(
                Arrays.asList(
                        TestStore_1,
                        TestStore_Caseys,
                        TestStore_3
                )
        );
}
