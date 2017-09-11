package com.softmarshmallow.foodle.Views.Mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.MockDataSource.MockDataSource;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Mypage.Shared.StoresListViewActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

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


        @OnClick(R.id.interstedStoresImageView)
        void OnShowMyLikedStoreClick() {
                Log.d(TAG, "OnShowMyLikedStoreClick");

                // fixme for debug
                List<StoreModel> likedStores = MockDataSource.LikedStoreDatas;

                Intent intent = new Intent(getContext(), (StoresListViewActivity.class));
                intent.putExtra((StoresListViewActivity.StoreDatasKey),
                        gson.toJson(likedStores));

                // intent
                getActivity().startActivity(intent);

        }


        @OnClick(R.id.ownedstoreImageView)
        void OnShowMyOwnedStoreClick() {
                Log.d(TAG, "OnShowMyOwnedStoreClick");

                // fixme for debug
                List<StoreModel> ownedStoreDatas = MockDataSource.OwnedStoreDatas;

                Intent intent = new Intent(getContext(), (StoresListViewActivity.class));
                intent.putExtra((StoresListViewActivity.StoreDatasKey),
                        gson.toJson(ownedStoreDatas));

                // intent
                getActivity().startActivity(intent);
        }

}
