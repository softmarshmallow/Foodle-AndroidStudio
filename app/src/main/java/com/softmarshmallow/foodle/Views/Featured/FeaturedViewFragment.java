package com.softmarshmallow.foodle.Views.Featured;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.firebase.database.DatabaseError;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.Featured.ListStore.StoreRecyclerViewAdapter;
import com.softmarshmallow.foodle.Views.Featured.Nearby.NearbyStoresRecyclerViewAdapter;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabControllerActivity;
import com.softmarshmallow.foodle.Views.MainTabController.MainTabsType;
import com.softmarshmallow.foodle.Views.Shared.GridSpacingItemDecoration;
import com.yalantis.taurus.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturedViewFragment extends Fragment
{
        private static final String TAG = "FeaturedViewFragment";

        @BindView(R.id.promotionImageSlider)
        SliderLayout promotionImageSlider;

        @BindView(R.id.foodtrucksNearbyTextView)
        TextView foodtrucksNearbyTextView;

        @BindView(R.id.topMenusTextView)
        TextView topMenusTextView;



        List<StoreModel> storeDatas = new ArrayList<StoreModel>();


        public static FeaturedViewFragment instance;


        public FeaturedViewFragment() {
                // Required empty public constructor
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                instance = this;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment

                View view = inflater.inflate(R.layout.fragment_featured_view, container, false);

                ButterKnife.bind(this, view);

                InitFeaturedStoresRecyclerView();
                LoadFeaturedStoresData();

                InitNearbyStoresRecyclerView();
                LoadNearbyStoresData();

                InitPromotionSlider();

                // InitPullToRefresh
                InitPullToRefresh();

                return view;

        }




        //region FeaturedStoreRecyclerView

        @BindView(R.id.featuredStoresRecyclerView)
        RecyclerView featuredStoresRecyclerView;
        StoreRecyclerViewAdapter featuredStoresRecyclerViewAdapter;
        RecyclerView.LayoutManager featuredStoresRecyclerViewLayoutManager;

        void InitFeaturedStoresRecyclerView(){

                featuredStoresRecyclerViewLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                featuredStoresRecyclerView.setLayoutManager(
                        featuredStoresRecyclerViewLayoutManager);
                featuredStoresRecyclerViewAdapter = new StoreRecyclerViewAdapter(storeDatas,
                        getActivity());
                featuredStoresRecyclerView.setAdapter(featuredStoresRecyclerViewAdapter);
        }

        void LoadFeaturedStoresData(){
                StoreService.GetAllStores(new Consumer<List<StoreModel>>()
                {
                        @Override
                        public void accept(List<StoreModel> storeModels) throws Exception {
                                featuredStoresRecyclerViewAdapter.storeDatas = storeModels;
                                featuredStoresRecyclerViewAdapter.notifyDataSetChanged();
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
                                                                LoadFeaturedStoresData();
                                                        }
                                                })
                                        .show();
                        }
                });
        }
        //endregion

        //region NearbyStoresRecyclerView
        @BindView(R.id.NearbyStoresRecyclerView)
        RecyclerView nearbyStoresRecyclerView;
        NearbyStoresRecyclerViewAdapter nearbyStoresRecyclerViewAdapter;
        void  InitNearbyStoresRecyclerView(){

                nearbyStoresRecyclerViewAdapter = new NearbyStoresRecyclerViewAdapter(storeDatas,
                        getContext());
                nearbyStoresRecyclerView.setAdapter(nearbyStoresRecyclerViewAdapter);

                RecyclerView.LayoutManager nearbyStoresLayoutManager = new GridLayoutManager(getContext(), 2);

                nearbyStoresRecyclerView.setLayoutManager(nearbyStoresLayoutManager);
                nearbyStoresRecyclerView.addItemDecoration(
                        new GridSpacingItemDecoration(2, GridSpacingItemDecoration.ConvertPixelsToDp(10), true));
                nearbyStoresRecyclerView.setItemAnimator(new DefaultItemAnimator());

        }

        void LoadNearbyStoresData(){

                StoreService.GetAllStores(new Consumer<List<StoreModel>>()
                {
                        @Override
                        public void accept(List<StoreModel> storeModels) throws Exception {

                                nearbyStoresRecyclerViewAdapter.storeDatas = storeModels;
                                nearbyStoresRecyclerViewAdapter.notifyDataSetChanged();
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
                                                                LoadFeaturedStoresData();
                                                        }
                                                })
                                        .show();
                        }
                });


        }
        //endregion

        void InitPromotionSlider(){
                DefaultSliderView sliderView1 = (DefaultSliderView) new DefaultSliderView(getContext()).image(R.drawable.promotion1_mockup);
                promotionImageSlider.addSlider(sliderView1);
                DefaultSliderView sliderView0 = (DefaultSliderView) new DefaultSliderView(getContext()).image(R.drawable.promotion_mockup);
                promotionImageSlider.addSlider(sliderView0);
                DefaultSliderView sliderView2 = (DefaultSliderView) new DefaultSliderView(getContext()).image(R.drawable.category1_mockup);
                promotionImageSlider.addSlider(sliderView2);
                DefaultSliderView sliderView3 = (DefaultSliderView) new DefaultSliderView(getContext()).image(R.drawable.category2_mockup);
                promotionImageSlider.addSlider(sliderView3);
                DefaultSliderView sliderView4 = (DefaultSliderView) new DefaultSliderView(getContext()).image(R.drawable.category3_mockup);
                promotionImageSlider.addSlider(sliderView4);
        }


        //region InitPullToRefresh
        @BindView(R.id.pull_to_refresh)
        PullToRefreshView pullToRefreshView;
        void InitPullToRefresh(){
                pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener()
                {
                        @Override
                        public void onRefresh() {
                                Log.d(TAG, "onRefresh");

                                pullToRefreshView.setRefreshing(false);
                        }
                });
        }



        @Override
        public void onDetach() {
                super.onDetach();
                Log.d(TAG, "onDetach");
        }

        @Override
        public void onDestroy() {
                super.onDestroy();

                Log.d(TAG, "onDestroy");
/*
                RefWatcher refWatcher = FoodleApp.getRefWatcher(getActivity());
                refWatcher.watch(this);*/
        }

        @Override
        public void onStop() {
                super.onStop();
                Log.d(TAG, "onStop");

                promotionImageSlider.stopAutoCycle();
        }


        //region Category Mockups
        @OnClick(R.id.categoryItem1)
        void OnCategoryItem1Click() {
                OnCategoryClick("Bakery");
        }

        @OnClick(R.id.categoryItem2)
        void OnCategoryItem2Click() {
                OnCategoryClick("Pizza");
        }

        @OnClick(R.id.categoryItem3)
        void OnCategoryItem3Click() {
                OnCategoryClick("Rice");
        }

        void OnCategoryClick(String categoryName) {
                Log.d(TAG, "OnCategoryClick : categoryName : " + categoryName);
                MainTabControllerActivity.instance.FocusOnTab(MainTabsType.Search);
        }
        //endregion


}
