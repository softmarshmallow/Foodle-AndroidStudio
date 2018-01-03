package com.softmarshmallow.foodle.Views.FoodtruckOwnerTabView;

import com.softmarshmallow.foodle.Views.MainTabController.MainTabsType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by softmarshmallow on 12/18/17.
 */

public enum FoodtruckOwnerTabsType
{
        
        // todo REPLACE
        A(1),
        B(2),
        C(3);
        
        
        
        
        
        int value;
        FoodtruckOwnerTabsType(int value) {
                this.value = value;
        }
        
        public int getValue() {
                return value;
        }
        
        
        public static Map<Integer, FoodtruckOwnerTabsType> MainTabsPositionMapping = new HashMap<Integer, FoodtruckOwnerTabsType>(){{
                put(0, FoodtruckOwnerTabsType.A);
                put(1, FoodtruckOwnerTabsType.B);
                put(2, FoodtruckOwnerTabsType.C);
        }};
        
        
        
        
}
