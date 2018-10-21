package com.softmarshmallow.foodle.Views.MenuEditor_Deprecated;

import android.os.Bundle;
import android.util.Log;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Services.MenuService;

//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

public class MenuCreatorActivity extends MenuEditorBaseActivity
{
        
        private static final String TAG = "MenuCreatorActivity";
        static String baseStoreId;
        public static void SetBaseStoreId(String baseStoreId){
                MenuCreatorActivity.baseStoreId = baseStoreId;
        }
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }
        
        @Override
        protected void PerformCRUDAction() {
                Log.d(TAG, "PerformCRUDAction");
                super.PerformCRUDAction();
                UploadMenu();
        }
        
        void UploadMenu(){
                Log.d(TAG, "UploadMenu");
        
                final SweetAlertDialog progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("메뉴 생성중..");
                
                progressDialog.show();
                
                
                
                new MenuService.MenuCreator(
                        MenuData,
                        baseStoreId,
                        new Consumer<MenuModel>()
                        {
                                @Override
                                public void accept(MenuModel menuModel) throws Exception {
                                        progressDialog.dismiss();
        
        
        
                                        new SweetAlertDialog(MenuCreatorActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("complete")
                                                .setConfirmClickListener(
                                                        new SweetAlertDialog.OnSweetClickListener()
                                                        {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                        sweetAlertDialog.dismiss();
                                                                        finish();
                                                                }
                                                        })
                                                .show();
                                }
                        },
                        new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        progressDialog.dismiss();
                                        
                                        new SweetAlertDialog(MenuCreatorActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("ERROR").show();
                                }
                        }).CreateMenu();
                
        }
        
        
        
        
        
}
