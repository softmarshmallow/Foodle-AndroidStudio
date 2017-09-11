package com.softmarshmallow.foodle.Views.Mypage.Shared;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Featured.ListStore.StoreRecyclerViewAdapter;
import com.softmarshmallow.foodle.Views.Search.SearchResultStoresDisplayRecyclerviewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StoresListViewActivity extends AppCompatActivity
{

        private static final String TAG ="StoresListViewActivity";

        // fixme embed StoreListFragment under this activity
        // fixme
        // fixme


        List<StoreModel> storeDatas;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_stores_list_view);
                ButterKnife.bind(this);

                LoadStoreDatas();

        }


        public void LoadStoreDatas()
        {
                Thread t = new Thread(new Runnable()
                {
                        @Override
                        public void run() {
                                LoadStoreDatasInBackground();
                        }
                });
                t.run();
        }

        // fixme more specific?
        public static final String StoreDatasKey = "StoreDatasKey";


        public void LoadStoreDatasInBackground()
        {
                Log.d(TAG, "LoadStoreDatasInBackground");
                // Load StoreData
                Gson gson = new Gson();
                this.storeDatas = gson.fromJson(getIntent().getStringExtra(StoreDatasKey), new TypeToken<List<StoreModel>>(){}.getType());


                runOnUiThread(new Runnable()
                {
                        @Override
                        public void run() {
                                SetStoreRecyclerView();
                        }
                });
        }





        @BindView(R.id.storeListRecyclerView)
        RecyclerView storeListRecyclerView;

        private StoreRecyclerViewAdapter storesAdapter;

        void SetStoreRecyclerView()
        {

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

                storesAdapter = new StoreRecyclerViewAdapter(storeDatas, this);
                storeListRecyclerView.setLayoutManager(layoutManager);
                storeListRecyclerView.setItemAnimator(new DefaultItemAnimator());
                storeListRecyclerView.setAdapter(storesAdapter);

        }





        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }

}
