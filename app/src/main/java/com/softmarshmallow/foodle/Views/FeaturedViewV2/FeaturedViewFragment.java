package com.softmarshmallow.foodle.Views.FeaturedViewV2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.softmarshmallow.foodle.CustomViews.PromotionSlider.PromotionSliderView;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.MenuService;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.StoreMapsView.StoreMapsViewActivity;
import com.softmarshmallow.foodle.Views.Test.Fragment_Banner;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

public class FeaturedViewFragment extends Fragment
{
        
        
        private static final String TAG = "FeaturedViewFragment";
        Unbinder unbinder;
        
        public FeaturedViewFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_featured_view_v2, container, false);
                unbinder = ButterKnife.bind(this, view);
        
                
                InitPromotionSlider();
                
                initFeaturedStores();
        
                initFeaturedMenus();
        
                initBanner();
                
                return view;
        }
        
        //region PromotionSlider
        @BindView(R.id.promotionImageSlider)
        SliderLayout promotionImageSlider;
        
        void InitPromotionSlider() {
        
                PromotionSliderView sliderView0 = (PromotionSliderView) new PromotionSliderView(
                        getContext()).image(R.drawable.promotion1_mockup);
                sliderView0.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                sliderView0.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener()
                {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                                //region Open facebook page
                                String url = "https://www.facebook.com/EatWithFoodle/";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                                //endregion
                        }
                });
                promotionImageSlider.addSlider(sliderView0);
                /*
                DefaultSliderView sliderView1 = (DefaultSliderView) new DefaultSliderView(
                        getContext()).storeImageView(R.drawable.promotion_mockup);
                sliderView1.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                promotionImageSlider.addSlider(sliderView1);
                */
        
                PromotionSliderView sliderView2 = (PromotionSliderView) new PromotionSliderView(
                        getContext()).image(R.drawable.category1_mockup);
                sliderView2.setPromotionTitle("work!!");
                sliderView2.description("anit joking!!");
                
                sliderView2.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                promotionImageSlider.addSlider(sliderView2);
        
                PromotionSliderView sliderView3 = (PromotionSliderView) new PromotionSliderView(
                        getContext()).image(R.drawable.category2_mockup);
                sliderView3.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                promotionImageSlider.addSlider(sliderView3);
        
                PromotionSliderView sliderView4 = (PromotionSliderView) new PromotionSliderView(
                        getContext()).image(R.drawable.category3_mockup);
                sliderView4.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                promotionImageSlider.addSlider(sliderView4);
        }
        //endregion
        
        
        //region watchFromMapsButtonContainer
        @OnClick(R.id.watchFromMapsButtonContainer)
        void onWatchFromMapsButtonContainerClick(){
                Intent intent = new Intent(getContext(), StoreMapsViewActivity.class);
                startActivity(intent);
        }
        
        
        //endregion
        
        
        
        //region FeaturedStores
        @BindView(R.id.featuredStoresRecyclerView)
        RecyclerView featuredStoresRecyclerView;
        FeaturedStoresAdapter featuredStoresAdapter;
        List<StoreContainerModel> storeDatas = new ArrayList<>();
        void initFeaturedStores(){
                Log.d(TAG, "initFeaturedStores");
        
                featuredStoresRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                featuredStoresRecyclerView.setItemAnimator(new DefaultItemAnimator());
                featuredStoresAdapter = new FeaturedStoresAdapter(getContext(), storeDatas);
                featuredStoresRecyclerView.setAdapter(featuredStoresAdapter);
        
                updateFeaturedStores();
        }
        
        void updateFeaturedStores(){
                Log.d(TAG, "updateFeaturedStores");
        
                StoreService.GetAllStores(new Consumer<List<StoreContainerModel>>()
                {
                        @Override
                        public void accept(List<StoreContainerModel> storeModels) throws Exception {
        
                                featuredStoresAdapter.storeDatas = storeModels;
                                featuredStoresAdapter.notifyDataSetChanged();
                        }
                }, new Consumer<String>()
                {
                        @Override
                        public void accept(String databaseError) throws Exception {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error while getting Store datas")
                                        .setContentText(databaseError)
                                        .setConfirmText("Retry")
                                        .setCancelClickListener(
                                                new SweetAlertDialog.OnSweetClickListener()
                                                {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                sweetAlertDialog.dismissWithAnimation();
                                                                updateFeaturedStores();
                                                        }
                                                })
                                        .show();
                        }
                });
        }
        //endregion
        
        //region FeaturedMenus
        @BindView(R.id.featuredMenusRecyclerView)
        RecyclerView featuredMenusRecyclerView;
        FeaturedMenusAdapter featuredMenusAdapter;
        List<MenuModel> menuDatas = new ArrayList<>();
        void initFeaturedMenus(){
                featuredMenusAdapter = new FeaturedMenusAdapter(getContext(), menuDatas);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                featuredMenusRecyclerView.setLayoutManager(mLayoutManager);
                featuredMenusRecyclerView.setItemAnimator(new DefaultItemAnimator());
                featuredMenusRecyclerView.setAdapter(featuredMenusAdapter);
        
                updateFeaturedMenus();
        }
        void updateFeaturedMenus(){
                MenuService.GetAllMenu(
                        new Consumer<List<MenuModel>>()
                        {
                                @Override
                                public void accept(List<MenuModel> menuModels) throws Exception {
                                        featuredMenusAdapter.UpdateDatas(menuModels);
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Error while getting Menus datas")
                                                .setContentText(s)
                                                .setConfirmText("Retry")
                                                .setCancelClickListener(
                                                        new SweetAlertDialog.OnSweetClickListener()
                                                        {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                        sweetAlertDialog.dismissWithAnimation();
                                                                        updateFeaturedStores();
                                                                }
                                                        })
                                                .show();
                                }
                        }
                );
        }
        //endregion
        
        
        //region Banner
        @BindView(R.id.viewpager)
        ViewPager viewPager;
        void initBanner(){
        
                viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager())
                {
                
                        @Override
                        public Fragment getItem(int position) {
                        
                                switch (position) {
                                        case 0:
                                                Fragment_Banner tab1 = new Fragment_Banner();
                                                return tab1;
                                
                                        case 1:
                                                Fragment_Banner tab6 = new Fragment_Banner();
                                                return tab6;
                                
                                
                                        case 2:
                                                Fragment_Banner tab2 = new Fragment_Banner();
                                                return tab2;
                                
                                
                                        default:
                                                return null;
                                }
                        }
                
                        @Override
                        public int getCount() {
                                return 3;
                        }
                });
        
        }
        
        //endregion
        
        
        @Override
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }
}
