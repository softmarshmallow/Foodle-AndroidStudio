package com.softmarshmallow.foodle.Views.StoreCreator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.MenusAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreCreator_Form_MenusFragment extends Fragment
{
        
        private static final String TAG = StoreCreator_Form_MenusFragment.class.getName();
        @BindView(R.id.menusRecyclerView)
        RecyclerView menusRecyclerView;
        MenusAdapter menusAdapter;
        
        List<MenuModel> createdMenuDatas = new ArrayList<>();
        
        public StoreCreator_Form_MenusFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_store_creator__form__menus, container,
                        false);
                ButterKnife.bind(this, view);
                
                
                menusAdapter = new MenusAdapter(getContext(), createdMenuDatas);
                menusRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                menusRecyclerView.setAdapter(menusAdapter);
                
                return view;
        }
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK){
                        if(requestCode == CreateNewMenuRequestCode){
                                Log.d(TAG, "Crated new Menu");
                                Gson gson = new Gson();
                                MenuModel menuData = gson.fromJson(data.getStringExtra(MenuCreatorActivity.ResultKey), MenuModel.class);
                                OnCreatedNewMenu(menuData);
                        }
                }
        }
        
        void OnCreatedNewMenu(MenuModel menuData){
                // add tp menus recycler view adapter
                createdMenuDatas.add(menuData);
                menusAdapter.notifyDataSetChanged();
        }
        
        private static final int CreateNewMenuRequestCode = 864;
        @OnClick(R.id.createNewMenuButton)
        void OnCreateNewMenuButtonClick(){
                Intent intent = new Intent(getContext(), MenuCreatorActivity.class);
                startActivityForResult(intent, CreateNewMenuRequestCode);
        }
        
}
