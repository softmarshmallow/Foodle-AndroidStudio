package com.softmarshmallow.foodle.Views.FeaturedViewV3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.FeaturedViewV2.FeaturedStoresAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
// TODO Unify
public class FeaturedStoreRowFragment extends Fragment
{
        static final String TAG = FeaturedStoreRowFragment.class.getSimpleName();
        
        @BindView(R.id.TopicDescriptionText)
        TextView TopicDescriptionText;
        @BindView(R.id.TopicTitleText)
        TextView TopicTitleText;
        @BindView(R.id.RecyclerView)
        android.support.v7.widget.RecyclerView RecyclerView;
        Unbinder unbinder;
        
        public FeaturedStoreRowFragment() {
                // Required empty public constructor
        }
        
        void SetFeaturedRow(String topicTitle, String topicDescription, ArrayList<StoreContainerModel> storeDatas){
        
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_featured_row, container, false);
                unbinder = ButterKnife.bind(this, view);
                initFeaturedStores();
                return view;
        }
        
        
        
        
        
        
        
        
        
        
        
        //region FeaturedStores
        FeaturedStoresAdapter featuredStoresAdapter;
        List<StoreContainerModel> storeDatas = new ArrayList<>();
        void initFeaturedStores(){
                Log.d(TAG, "initFeaturedStores");
                
                RecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                RecyclerView.setItemAnimator(new DefaultItemAnimator());
                featuredStoresAdapter = new FeaturedStoresAdapter(getContext(), storeDatas);
                RecyclerView.setAdapter(featuredStoresAdapter);
                
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
        
        
        
        
        
        
        
        
        
        
        
        
        @Override
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }
}
