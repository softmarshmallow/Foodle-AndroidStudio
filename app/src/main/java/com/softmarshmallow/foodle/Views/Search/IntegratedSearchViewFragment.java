package com.softmarshmallow.foodle.Views.Search;


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
import android.widget.SearchView;
import android.widget.Toast;

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.MockDataSource.MockDataSource;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.MenuDetailPage.MenuDetailActivity;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntegratedSearchViewFragment extends Fragment
{

        final static String TAG = IntegratedSearchViewFragment.class.getName();


        @BindView(R.id.integratedSearchView)
        SearchView searchView;



        public IntegratedSearchViewFragment() {
                // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_integrated_search_view, container,
                        false);
                ButterKnife.bind(this, view);


                // init InitSearchResultsRecyclerView
                InitSearchResultsRecyclerView();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
                {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                                String query = s;
                                Log.d(TAG, "OnSearchQueryTextSubmit | query : " + query);

                                SearchStores(query);
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                                return false;
                        }
                });


                return view;
        }



// Region searchStores
        void SearchStores(String query) {
                // search store names
                List<StoreContainerModel> storeDatas = StoreService.storeContainerDataList;


                SearchStore_MatchStoreName(storeDatas, query);

        }
        //endregion



        // region Search StoreName
        @BindView(R.id.searchResultsRecyclerView)
        RecyclerView searchResults_MatchStoreName_RecyclerView;
        private LinearLayoutManager searchResultsRecyclerViewLayoutManager;
        SearchResultStoresDisplayRecyclerviewAdapter searchResults_MatchStoreName_RecyclerViewAdapter;
        List<StoreContainerModel> searchResults_MatchStoreName = new ArrayList<>();
        void InitSearchResultsRecyclerView(){
                searchResultsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
                searchResults_MatchStoreName_RecyclerView.setLayoutManager(searchResultsRecyclerViewLayoutManager);
                searchResults_MatchStoreName_RecyclerViewAdapter = new SearchResultStoresDisplayRecyclerviewAdapter(
                        searchResults_MatchStoreName, getActivity());
                searchResults_MatchStoreName_RecyclerView.setAdapter(
                        searchResults_MatchStoreName_RecyclerViewAdapter);
        }

        void SearchStore_MatchStoreName(List<StoreContainerModel> storeDatas, String query){

                // region
                // Search searchResult_MatchesStorename
                List<StoreContainerModel> searchResult_MatchesStorename = new ArrayList<>();

                for (StoreContainerModel storeData : storeDatas){
                        if (storeData.StoreName.toLowerCase().contains(query.toLowerCase())){
                                searchResult_MatchesStorename.add(storeData);
                        }
                }

                searchResults_MatchStoreName = searchResult_MatchesStorename;
                Log.d(TAG,
                        "storeDatas.Count : " + storeDatas.size() + " | searchResults_MatchStoreName.Count : " + searchResults_MatchStoreName.size());

                searchResults_MatchStoreName_RecyclerViewAdapter.Update(
                        searchResults_MatchStoreName);
                //endregion
        }
        //endregion


        //region Search Match content





        void SearchStore_MatchStoreDescription(List<StoreContainerModel> storeDatas, String query){
                // region
                // Search SearchStore_MatchStoreDescription
                List<StoreContainerModel> searchResult_MatchStoreDescription = new ArrayList<>();

                for (StoreContainerModel storeData : storeDatas){
                        String content = storeData.StoreShortDescription + storeData.StoreFullDescription;
                        if (content.toLowerCase().contains(query.toLowerCase())){
                                searchResult_MatchStoreDescription.add(storeData);
                        }
                }

                searchResults_MatchStoreName = searchResult_MatchStoreDescription;
                Log.d(TAG,
                        "storeDatas.Count : " + storeDatas.size() + " | searchResults_MatchStoreName.Count : " + searchResults_MatchStoreName.size());

                searchResults_MatchStoreName_RecyclerViewAdapter.Update(
                        searchResults_MatchStoreName);
                //endregion
        }


        // endregion


        // // FIXME: 9/7/17 Implement
        public class StoreSearchContainer{
                protected RecyclerView recyclerView;

                protected SearchResultStoresDisplayRecyclerviewAdapter adapter;

                protected  List<StoreContainerModel> Filter(List<StoreContainerModel> storeDatas, String query){


                        return storeDatas;
                }
        }


        class StoreSearch_MatchStoreName_Conatiner extends StoreSearchContainer{
                @Override
                protected List<StoreContainerModel> Filter(List<StoreContainerModel> storeDatas, String query) {
                        return super.Filter(storeDatas, query);
                }

        }


        @OnClick(R.id.Scan)
        void OnClick(){
//                IntentIntegrator.forFragment(()).initiateScan(); // `this` is the current Fragment
                Log.d("Error", "SEX");
                StoreDetailViewActivity.ShowStoreDetailWithData(getContext(),MockDataSource.getTestStore_1());


        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//                if(result != null) {
//                        if(result.getContents() == null) {
//
//                        } else {
//                                StoreDetailViewActivity.ShowStoreDetailWithData(getContext(),MockDataSource.getTestStore_1());
//                        }
//                } else {
//                        super.onActivityResult(requestCode, resultCode, data);
//                }
        }
}
