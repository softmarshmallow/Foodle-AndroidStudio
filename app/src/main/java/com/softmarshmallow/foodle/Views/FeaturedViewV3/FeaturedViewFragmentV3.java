package com.softmarshmallow.foodle.Views.FeaturedViewV3;


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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.softmarshmallow.foodle.CustomViews.PromotionSlider.PromotionSliderView;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.MenuService;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.FeaturedViewV2.FeaturedMenusAdapter;
import com.softmarshmallow.foodle.Views.FeaturedViewV2.FeaturedStoresAdapter;
import com.softmarshmallow.foodle.Views.Shared.BaseMenusAdapter;
import com.softmarshmallow.foodle.Views.StoreMapsView.StoreMapsViewActivity;
import com.softmarshmallow.foodle.Views.Test.Fragment_Banner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

public class FeaturedViewFragmentV3 extends Fragment
{
        
        
        private static final String TAG = "FeaturedViewFragment";
        Unbinder unbinder;
        
        public FeaturedViewFragmentV3() {
                // Required empty public constructor
        }

        @BindView(R.id.view1)
        View view1;
        @BindView(R.id.view2)
        View view2;
        @BindView(R.id.view3)
        View view3;
        List<StoreContainerModel> storeDatas = new ArrayList<>();
        List<MenuModel> MenuDatas = new ArrayList<>();
        FeaturedStoresAdapter featuredStoresAdapter;
        FeaturedMenusAdapter featuredMenusAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_featured_view_v3, container, false);
                unbinder = ButterKnife.bind(this, view);

                initFeaturedMenus();
                initFeaturedStores();

                return view;
        }

        void initFeaturedStores(){
                RecyclerView recyclerView = (RecyclerView) view1.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                storeDatas.add(new StoreContainerModel());
                storeDatas.add(new StoreContainerModel());
                featuredStoresAdapter = new FeaturedStoresAdapter(getContext(), storeDatas);
                recyclerView.setAdapter(featuredStoresAdapter);
                featuredStoresAdapter.notifyDataSetChanged();
                TextView textView = (TextView) view1.findViewById(R.id.titleText);
                textView.setText("내 주변 푸드트럭 모음");
                TextView sub = (TextView) view1.findViewById(R.id.subtitleText);
                sub.setText("옆거리에서 만나봐요");

                updateFeaturedStores();
        }

        void initFeaturedMenus(){
                updateFeaturedMenus();
                RecyclerView recyclerView = (RecyclerView) view2.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                MenuDatas.add(new MenuModel());

                featuredMenusAdapter = new FeaturedMenusAdapter(getContext(), MenuDatas);
                recyclerView.setAdapter(featuredMenusAdapter);
                featuredMenusAdapter.notifyDataSetChanged();
                TextView textView = (TextView) view2.findViewById(R.id.titleText);
                textView.setText("탑클래스 스테이크 푸드트럭 모음");
                TextView sub = (TextView) view2.findViewById(R.id.subtitleText);
                sub.setText("스테이크도 이제 길거리 음식이다.");
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
                                                                        updateFeaturedMenus();
                                                                }
                                                        })
                                                .show();
                                }
                        }
                );
        }
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }
}
