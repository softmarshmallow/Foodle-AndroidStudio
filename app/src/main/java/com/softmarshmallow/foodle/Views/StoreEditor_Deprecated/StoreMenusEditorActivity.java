package com.softmarshmallow.foodle.Views.StoreEditor_Deprecated;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.MenuEditor.MenuCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreDetail.MenusAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreMenusEditorActivity extends AppCompatActivity
{
        
        public static String baseStoreId;
        public static void setBaseStoreId(String baseStoreId){
                StoreMenusEditorActivity.baseStoreId = baseStoreId;
        }
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_menus_editor);
        
                ButterKnife.bind(this);
                
                
                menusAdapter = new MenusAdapter(this, MenuDatas);
                menusRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                menusRecyclerView.setAdapter(menusAdapter);
                
        }
        
        
        private static final String TAG = StoreMenusEditorActivity.class.getName();
        @BindView(R.id.menusRecyclerView)
        RecyclerView menusRecyclerView;
        MenusAdapter menusAdapter;
        
        List<MenuModel> MenuDatas = new ArrayList<>();
        
        
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK){
                        if(requestCode == CreateNewMenuRequestCode){
                                Log.d(TAG, "Crated new Menu");
                                Gson gson = new Gson();
                                MenuModel menuData = gson.fromJson(data.getStringExtra(
                                        MenuCreatorActivity.ResultKey), MenuModel.class);
                                OnCreatedNewMenu(menuData);
                        }
                }
        }
        
        
        void OnCreatedNewMenu(MenuModel menuData){
                // add tp menus recycler view adapter
                MenuDatas.add(menuData);
                menusAdapter.notifyDataSetChanged();
        }
        
        private static final int CreateNewMenuRequestCode = 864;
        @OnClick(R.id.createNewMenuButton)
        void OnCreateNewMenuButtonClick(){
                Intent intent = new Intent(this, MenuCreatorActivity.class);
                MenuCreatorActivity.SetBaseStoreId(baseStoreId);
                startActivityForResult(intent, CreateNewMenuRequestCode);
        }
        
        
        @OnClick(R.id.saveTextButton)
        void OnSaveButtonClick(){
                finishEditingStoreMenus();
        }
        
        
        void finishEditingStoreMenus(){
                finish();
        }
        
}




