package com.softmarshmallow.foodle.Views.StoreEditor_Deprecated;

import android.content.Intent;
import android.os.Bundle;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.Services.StoreService;

//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

public class StoreCreatorActivity extends StoreEditorBaseActivity
{
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                
                ChangeCustomUi();
        }
        
        void ChangeCustomUi(){
                saveButton.setText("등록하기");
        }
        
        
        @Override
        protected void PerformCRUDAction() {
                super.PerformCRUDAction();
                CreateNewStore();
        }
        
        
        //region CreateStore
        
        boolean isStoreCreated = false;
        
        void CreateNewStore(){
                final SweetAlertDialog createStoreProgressDialog = new SweetAlertDialog(StoreCreatorActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("푸드트럭 업로드중..");
                createStoreProgressDialog.show();
                new StoreService.StoreUploader(
                        StoreUploadBundleData,
                        new Consumer<StoreContainerModel>()
                        {
                                @Override
                                public void accept(final StoreContainerModel storeModel) throws Exception {
                                        createStoreProgressDialog.dismissWithAnimation();
                                        SweetAlertDialog dialog = new SweetAlertDialog(StoreCreatorActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("업로드 완료")
                                                .setConfirmClickListener(
                                                        new SweetAlertDialog.OnSweetClickListener()
                                                        {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                        isStoreCreated = true;
                                                                        
                                                                        
                                                                        Intent intent = new Intent(StoreCreatorActivity.this, StoreMenusEditorActivity.class);
                                                                        StoreMenusEditorActivity.setBaseStoreId(storeModel.Id);
                                                                        StoreCreatorActivity.this.startActivity(intent);
                                                                        
                                                                        
                                                                        StoreCreatorActivity.this.finish();
                                                                        
                                                                }
                                                        });
                                        dialog.setCancelable(false);
                                        dialog.show();
                                }
                        },
                        new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        createStoreProgressDialog.dismissWithAnimation();
                                        new SweetAlertDialog(StoreCreatorActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("업로드 실패")
                                                .setContentText(s)
                                                .setConfirmClickListener(
                                                        new SweetAlertDialog.OnSweetClickListener()
                                                        {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                        StoreCreatorActivity.this.finish();
                                                                }
                                                        }).show();
                                }
                        }).CreateStore();
        }
        //endregion
        
        
        @Override
        void OnEndterEditMenuPageButtonClick() {
                if (isStoreCreated){
                        //
                }
                else{
                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("준비 안됨")
                                .setContentText("우선 푸드트럭을 저장해주세요")
                                .show();
                }
        }
}
