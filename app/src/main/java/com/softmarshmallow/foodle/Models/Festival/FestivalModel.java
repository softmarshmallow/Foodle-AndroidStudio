package com.softmarshmallow.foodle.Models.Festival;

import com.softmarshmallow.foodle.Models.ReviewModel;
import com.softmarshmallow.foodle.Models.Store.StoreModel;

import java.util.Date;
import java.util.List;

/**
 * Created by UZU on 03/09/2017.
 */

public class FestivalModel
{
        public String FestivalWebsiteUrl;
        
        
        public int Id;
        public String FestivalName;
        
        public String FestivalShortDescription;
        public String FestivalFullDescription;
        
        //region GraphicsData
        public String MainImageUrl;
        
        //endregion
        
        
        public List<ReviewModel> FestivalReviews;
        
        
        // todo set as range
        public Date StartTime;
        public Date EndTime;
        
        
        public String Location;
        
        //region FoodTruckRelatedDatas
        // todo add foodTruck Related data
        // extra info/ eg) does support electricity
        public boolean DoesSupportElectricity;
        
        /// <summary>
        /// 입점 가능 메뉴
        /// </summary>
        /// <value>The available menu description.</value>
        public String AvailableMenuDescription;
        
        /// <summary>
        /// 계약금
        /// </summary>
        /// <value>The contract budget.</value>
        public int ContractBudget;
        // AvailableMenusDescription
        //endregion
        
        /// <summary>
        /// 참여예정 푸드트럭
        /// </summary>
        /// <value>The comming foodtrucks.</value>
        public List<StoreModel> CommingFoodtrucks;
        
}
