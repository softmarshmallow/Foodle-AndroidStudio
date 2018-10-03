package com.softmarshmallow.foodle.Views.StoreDetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.softmarshmallow.foodle.Helpers.StoreExtraContactsBuilder;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.ReviewModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreReviewModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.MenuService;
import com.softmarshmallow.foodle.Services.StoreReviewService;
import com.softmarshmallow.foodle.Services.StoreService;
import com.softmarshmallow.foodle.Views.RequestCatering.RequestCateringActivity;
import com.softmarshmallow.foodle.Views.Shared.GridSpacingItemDecoration;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreReview.StoreReviewCreaterActivity;
import com.yalantis.taurus.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StoreDetailViewActivity extends AppCompatActivity implements OnMapReadyCallback, AppBarLayout.OnOffsetChangedListener
{
        
        
        private static final String TAG = "StoreDetailViewActivity";
        //region views
        @BindView(R.id.appbar)
        AppBarLayout appBarLayout;
        
        @BindView(R.id.pull_to_refresh)
        PullToRefreshView pullToRefreshView;
//
//        @BindView(R.id.toolbar)
//        Toolbar toolbar;
//
        @BindView(R.id.storeFeatureGraphicImageView)
        ImageView storeFeatureGraphicImageView;
        
        @BindView(R.id.storeNameTextView)
        TextView storeNameTextView;
        
        @BindView(R.id.storeShortDescriptionTextView)
        TextView storeShortDescriptionTextView;
        
        @BindView(R.id.storeFullDescriptionExpandableTextView)
        ExpandableTextView storeFullDescriptionExpandableTextView;
        
        @BindView(R.id.storeDetailMenusRecyclerView)
        RecyclerView menusRecyclerView;
        
        @BindView(R.id.storeReviewsRecyclerView)
        RecyclerView reviewsRecyclerView;
        
        
        // Store Location
        @BindView(R.id.storeAddressTextView)
        TextView storeAddressTextView;
        
        @BindView(R.id.storeAddressPreviewTextView)
        TextView storeAddressPreviewTextView;
        
        
        @BindView(R.id.storeMapDescriptionAddressTextView)
        TextView storeMapDescriptionAddressTextView;
        
        @BindView(R.id.storeLocationMapView)
        MapView storeLocationMapView;
        
        //endregion
        
        
        Geocoder geoCoder;
        
        MenusAdapter menusAdapter;
        ReviewsAdapter reviewsAdapter;
        
        static StoreContainerModel storeDataToDisplay;

        
        public static void ShowStoreDetailWithData(Context context, StoreContainerModel storeDataToDisplay) {
                StoreDetailViewActivity.storeDataToDisplay = storeDataToDisplay;
                context.startActivity(new Intent(context, StoreDetailViewActivity.class));
        }
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_detail_view);
                ButterKnife.bind(this);
/*

                //pushable activity
                Slidr.attach(this);
*/
                
                
//                toolbar.setTitleTextColor(Color.RED);
//                setSupportActionBar(toolbar);
                
                //region Init
                // map
                storeLocationMapView.onCreate(savedInstanceState);
                SetUpMap();
                
                
                // Init CollapsingToolbar
                InitCollapsingToolbar();
                // InitPullToRefresh
                InitPullToRefresh();
                //
                InitLikeButton();
                
                
                // init GeoCoder
                this.geoCoder = new Geocoder(this, Locale.getDefault());
                //endregion
                
                
                LoadStoreData();
                
                
        }
        
        
        public void LoadStoreData() {
                
                Thread t = new Thread(new Runnable()
                {
                        @Override
                        public void run() {
                                LoadStoreDataInBackground();
                        }
                });
                t.run();
        }
        
        public void LoadStoreDataInBackground() {
                
                Gson gson = new Gson();
                // Load StoreData
                if (storeDataToDisplay == null) {
                        Log.e(TAG,
                                "STORE DATA CANNOT BE NULL, this may be caused by using old intend pattern");
                        this.storeDataToDisplay = gson.fromJson(getIntent().getStringExtra(
                                (StoreContainerModel.class.getName())), StoreContainerModel.class);
                }
                
                
                runOnUiThread(new Runnable()
                {
                        @Override
                        public void run() {
                                updateStoreInfo();
        
                                initStoreMenusRecyclerView();
                                
                                initStoreReviewsRecyclerView();
        
                                updateStoreContacts();
        
                                
                        }
                });
                
        }
        
        //region Menus
        void initStoreMenusRecyclerView(){
                RecyclerView.LayoutManager menusLayoutManager = new GridLayoutManager(this, 2);
        
                menusAdapter = new MenusAdapter(this, storeDataToDisplay.Menus);
                menusRecyclerView.setLayoutManager(menusLayoutManager);
                menusRecyclerView.addItemDecoration(
                        new GridSpacingItemDecoration(2,
                                GridSpacingItemDecoration.ConvertPixelsToDp(10), true));
                menusRecyclerView.setItemAnimator(new DefaultItemAnimator());
                menusRecyclerView.setAdapter(menusAdapter);
        
                updateStoreMenus();
        }
        
        void updateStoreInfo(){
        
                // Store MainImage
                Glide.with(StoreDetailViewActivity.this)
                        .load(storeDataToDisplay.getMainStorePhotoUrl())
                        .into(storeFeatureGraphicImageView);
                // StoreName
                storeNameTextView.setText(storeDataToDisplay.StoreName);
                // StoreShortDescription
                storeShortDescriptionTextView.setText(
                        storeDataToDisplay.StoreShortDescription);
                // StoreFullDescription
                storeFullDescriptionExpandableTextView.setText(
                        storeDataToDisplay.StoreFullDescription);
        
        
                // region Store Location ==
                storeAddressPreviewTextView.setText(storeDataToDisplay.StoreAddress);
                storeAddressTextView.setText(storeDataToDisplay.StoreAddress);
        
        
                // set map with storeLocation
        
        
                // Store address with LatLng Data
                               /* String addressByGeoCoder = "";
                                try {
                                        List<Address> listAddresses = geoCoder.getFromLocation(
                                                storeDataToDisplay.GetStoreLocation().first,
                                                storeDataToDisplay.GetStoreLocation().second, 1);
                                        Log.d(TAG, "listAddresses.size : " + listAddresses.size());
                                        Address address = listAddresses.get(0);
                                        
                                        for (int addressLine = 0; addressLine < 3; addressLine++) {
                                                addressByGeoCoder += address.getAddressLine(
                                                        addressLine);
                                        }
                                }
                                catch (Exception ex) {
                                        addressByGeoCoder = "No Data..";
                                }
                                storeMapDescriptionAddressTextView.setText(addressByGeoCoder);
*/
        
        
        
                // set google map to storeLocation
                storeLocationMapView.getMapAsync(new OnMapReadyCallback()
                {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                                googleMap.moveCamera(
                                        CameraUpdateFactory.newLatLngZoom(
                                                new LatLng(
                                                        storeDataToDisplay.getStoreLocation().first,
                                                        storeDataToDisplay.getStoreLocation().second),
                                                18.5f));
                        }
                });
                //endregion
        
        }
        
        void updateStoreMenus() {
                
                // Menus

                
                storeDataToDisplay.Menus = new ArrayList<>();
                for (String menuId : storeDataToDisplay.MenusIds) {
                        MenuService.GetMenu(
                                menuId,
                                new Consumer<MenuModel>()
                                {
                                        @Override
                                        public void accept(MenuModel menuModel) throws Exception {
                                                storeDataToDisplay.Menus.add(menuModel);
                                                menusAdapter.UpdateDatas(storeDataToDisplay.Menus);
                                        }
                                }, new Consumer<String>()
                                {
                                        @Override
                                        public void accept(String databaseError) throws Exception {
                                                Log.e(TAG, databaseError);
                                        }
                                });
                }
                
                
        }
        //endregion
        
        
        //region Reviews
        void initStoreReviewsRecyclerView() {
                reviewsAdapter = new ReviewsAdapter(this);
                
                // Reviews
                RecyclerView.LayoutManager reviewsLayoutManager = new LinearLayoutManager(this);
                
                reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
                reviewsRecyclerView.addItemDecoration(
                        new GridSpacingItemDecoration(2,
                                GridSpacingItemDecoration.ConvertPixelsToDp(10), true));
                reviewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                reviewsRecyclerView.setAdapter(reviewsAdapter);
        
                updateStoreReviews();
        }
        
        void updateStoreReviews(){
        
                // Load & Update Review Datas
                storeDataToDisplay.StoreReviews = new ArrayList<>();
                for (String storeReviewsIdsHashKey : storeDataToDisplay.StoreReviewsIds.keySet()) {
                        String storeReviewId = storeDataToDisplay.StoreReviewsIds.get(
                                storeReviewsIdsHashKey);
                        StoreReviewService.GetStoreReview(
                                storeReviewId,
                                new Consumer<StoreReviewModel>()
                                {
                                        @Override
                                        public void accept(StoreReviewModel storeReviewModel) throws Exception {
                                                storeDataToDisplay.StoreReviews.add(
                                                        storeReviewModel);
                                                List<? extends ReviewModel> reviews = storeDataToDisplay.StoreReviews;
                                                reviewsAdapter.UpdateReviews(
                                                        (List<ReviewModel>) reviews);
                                        }
                                }, new Consumer<String>()
                                {
                                        @Override
                                        public void accept(String databaseError) throws Exception {
                                                Log.e(TAG, databaseError);
                                        }
                                });
                }
        }
        //endregion
        
        //region Contacts
        @BindView(R.id.storePhoneNumberTextView)
        TextView storePhoneNumberTextView;
        
        @BindView(R.id.storeFacebookLinkTextView)
        TextView storeFacebookLinkTextView;
        @BindView(R.id.storeInstagramLinkTextView)
        TextView storeInstagramLinkTextView;
        
        void updateStoreContacts(){
                storePhoneNumberTextView.setText(storeDataToDisplay.StorePhoneNumber);
                
                // SET extra contacts
                Map<String, String> contacts = StoreExtraContactsBuilder.Parse(storeDataToDisplay.StoreExtraContacts);
                try {
                        storeFacebookLinkTextView.setText(
                                contacts.get(StoreExtraContactsBuilder.facebookKey));
                        storeInstagramLinkTextView.setText(
                                contacts.get(StoreExtraContactsBuilder.instagramKey));
                }catch (Exception e){
                        // Do Nothing
                        // No Contact Info
                }
        }
        
        //endregion
        
        
        void InitPullToRefresh() {
                pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener()
                {
                        @Override
                        public void onRefresh() {
                                Log.d(TAG, "onRefresh");
                                
                                
                                StoreService.GetStore(
                                        storeDataToDisplay.Id,
                                        new Consumer<StoreContainerModel>()
                                        {
                                                @Override
                                                public void accept(StoreContainerModel storeModel) throws Exception {
                                                        pullToRefreshView.setRefreshing(false);
                                                        storeDataToDisplay = storeModel;
                                                        updateStoreMenus();
                                                        updateStoreReviews();
                                                        updateStoreContacts();
                                                        
                                                        new SweetAlertDialog(StoreDetailViewActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("업데이트 완료")
                                                                .show();
                                                        
                                                }
                                        }, new Consumer<String>()
                                        {
                                                @Override
                                                public void accept(String databaseError) throws Exception {
                                                        pullToRefreshView.setRefreshing(false);
                                                        new SweetAlertDialog(
                                                                StoreDetailViewActivity.this,
                                                                SweetAlertDialog.ERROR_TYPE)
                                                                .setTitleText("업데이트 오류")
                                                                .setContentText(databaseError)
                                                                .show();
                                                }
                                        });
                                
                                
                        }
                });
        }
        
        
        //region map
        GoogleMap googleMap;
        
        void SetUpMap() {
                if (googleMap == null) {
                        storeLocationMapView.getMapAsync(this);
                }
        }
        
        
        @Override
        public void onMapReady(GoogleMap googleMap) {
                this.googleMap = googleMap;
//                this.googleMap.UiSettings.CompassEnabled = false;
//                this.googleMap.UiSettings.MyLocationButtonEnabled = false;
//                this.googleMap.UiSettings.MapToolbarEnabled = false;
        }
        //endregion
        
        
        void InitCollapsingToolbar() {
                final CollapsingToolbarLayout collapsingToolbar =
                        (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                collapsingToolbar.setTitle(" ");
                AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
                appBarLayout.setExpanded(true);
                
                // hiding & showing the title when toolbar expanded & collapsed
                
                
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
                {
                        boolean isShow = false;
                        int scrollRange = -1;
                        
                        @Override
                        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                                if (scrollRange == -1) {
                                        scrollRange = appBarLayout.getTotalScrollRange();
                                }
                                if (scrollRange + verticalOffset == 0) {
                                        collapsingToolbar.setTitle(storeDataToDisplay.StoreName);
                                        this.isShow = true;
                                } else if (isShow) {
                                        collapsingToolbar.setTitle("");
                                        isShow = false;
                                }
                        }
                });
                
        }
        
        
        void InitLikeButton() {
                LikeButton likeButton = findViewById(R.id.likeButton);
                likeButton.setOnLikeListener(new OnLikeListener()
                {
                        @Override
                        public void liked(LikeButton likeButton) {
                                /*new SweetAlertDialog(StoreDetailViewActivity.this)
                                        .setTitleText("Liked!")
                                        .show();*/
                                
                        }
                        
                        @Override
                        public void unLiked(LikeButton likeButton) {
                                /*new SweetAlertDialog(StoreDetailViewActivity.this)
                                        .setTitleText("UnLiked!")
                                        .show();*/
                                
                        }
                });
        }
        
        
        @OnClick(R.id.cateringRequestOptionContainer)
        void OnRequestCateringOptionClick() {
                Intent intent = new Intent(this, RequestCateringActivity.class);
                startActivity(intent);
        }
        
        @OnClick(R.id.shareOptionContainer)
        void OnShareOptionClick() {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, storeDataToDisplay.StoreExtraContacts);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
        }
        
        @OnClick(R.id.writeReviewOptionContainer)
        void OnWriteReviewOptionClick() {
                StoreReviewCreaterActivity.StartCreateStoreReviewActivity(storeDataToDisplay.Id,
                        this);
        }
        
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //The Refresh must be only active when the offset is zero :
                pullToRefreshView.setEnabled(verticalOffset == 0);
                
        }
        
        @Override
        protected void onResume() {
                super.onResume();
                appBarLayout.addOnOffsetChangedListener(this);
        }
        
        @Override
        protected void onPause() {
                super.onPause();
                appBarLayout.removeOnOffsetChangedListener(this);
        }
        
        //endregion
        
        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
        
}
