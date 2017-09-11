package com.softmarshmallow.foodle.Views.Search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.softmarshmallow.foodle.Models.MockDataSource.MockDataSource;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                List<StoreModel> storeDatas = MockDataSource.AllStoreDatas;


                SearchStore_MatchStoreName(storeDatas, query);

        }
        //endregion



        // region Search StoreName
        @BindView(R.id.searchResultsRecyclerView)
        RecyclerView searchResults_MatchStoreName_RecyclerView;
        private LinearLayoutManager searchResultsRecyclerViewLayoutManager;
        SearchResultStoresDisplayRecyclerviewAdapter searchResults_MatchStoreName_RecyclerViewAdapter;
        List<StoreModel> searchResults_MatchStoreName = new ArrayList<>();
        void InitSearchResultsRecyclerView(){
                searchResultsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
                searchResults_MatchStoreName_RecyclerView.setLayoutManager(searchResultsRecyclerViewLayoutManager);
                searchResults_MatchStoreName_RecyclerViewAdapter = new SearchResultStoresDisplayRecyclerviewAdapter(
                        searchResults_MatchStoreName, getActivity());
                searchResults_MatchStoreName_RecyclerView.setAdapter(
                        searchResults_MatchStoreName_RecyclerViewAdapter);
        }

        void SearchStore_MatchStoreName(List<StoreModel> storeDatas, String query){

                // region
                // Search searchResult_MatchesStorename
                List<StoreModel> searchResult_MatchesStorename = new ArrayList<>();

                for (StoreModel storeData : storeDatas){
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





        void SearchStore_MatchStoreDescription(List<StoreModel> storeDatas, String query){
                // region
                // Search SearchStore_MatchStoreDescription
                List<StoreModel> searchResult_MatchStoreDescription = new ArrayList<>();

                for (StoreModel storeData : storeDatas){
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

                protected  List<StoreModel> Filter(List<StoreModel> storeDatas, String query){


                        return storeDatas;
                }
        }


        class StoreSearch_MatchStoreName_Conatiner extends StoreSearchContainer{
                @Override
                protected List<StoreModel> Filter(List<StoreModel> storeDatas, String query) {
                        return super.Filter(storeDatas, query);
                }

        }




}
