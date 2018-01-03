package com.softmarshmallow.foodle.Views.Mypage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.MockDataSource.MockDataSource;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.Models.User.FoodleUserProfileModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;
import com.softmarshmallow.foodle.Services.FoodleUserProfileService;
import com.softmarshmallow.foodle.Views.Mypage.Shared.StoresListViewActivity;
import com.softmarshmallow.foodle.Views.SMSVerification.SMSVerification_PhoneNumberEnterViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment
{
        
        static final String TAG = "MypageFragment";

        Gson gson = new Gson();

        public MypageFragment() {
                // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_mypage, container, false);
                ButterKnife.bind(this, view);
        
        

                return view;

        }
        
        
        @Override
        public void onAttach(Context context) {
                super.onAttach(context);
                LoadLocalUserData();
        }
        
        void LoadLocalUserData(){
                
                final SweetAlertDialog loadingDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("로딩중..")
                        .setContentText("내 정보를 받아오는 중입니다.");
        
        
                loadingDialog .show();
                
                
                FoodleUserProfileService.getUserProfile(
                        FirebaseUserService.GetUserUID(),
                        new Consumer<FoodleUserProfileModel>()
                        {
                                @Override
                                public void accept(FoodleUserProfileModel foodleUserProfileModel) throws Exception {
        
                                        loadingDialog.dismissWithAnimation();
        
                                        SetUserDisplayName(foodleUserProfileModel.displayName);
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        loadingDialog.dismissWithAnimation();
                                        new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("오류!")
                                                .setContentText("내 정보를 받아오는동안 오류가 발생하였습니다. 메세지 : " + s)
                                                .show();
                                
                                }
                        });
        
        
        }
        
        
        @BindView(R.id.displaynameTextView)
        TextView displaynameTextView;
        void SetUserDisplayName(String userDiisplayName){
                displaynameTextView.setText(userDiisplayName);
        }
        
        
        @OnClick(R.id.enterProfileEditButton)
        void OnEnterProfileEditButtonClick(){
                startActivity(new Intent(getContext(), ProfileEditorActivity.class));
        }
        


        @OnClick(R.id.interstedStoresImageView)
        void OnShowMyLikedStoreClick() {
                Log.d(TAG, "OnShowMyLikedStoreClick");

                // fixme for debug
                List<StoreContainerModel> likedStores = MockDataSource.LikedStoreDatas;
                
                StoresListViewActivity.showStoresList(getContext(), likedStores);
        }


        @OnClick(R.id.ownedstoreImageView)
        void OnShowMyOwnedStoreClick() {
                Log.d(TAG, "OnShowMyOwnedStoreClick");

                // fixme for debug
                List<StoreContainerModel> ownedStoreDatas = MockDataSource.OwnedStoreDatas;
        
                StoresListViewActivity.showStoresList(getContext(), ownedStoreDatas);
        }

}
