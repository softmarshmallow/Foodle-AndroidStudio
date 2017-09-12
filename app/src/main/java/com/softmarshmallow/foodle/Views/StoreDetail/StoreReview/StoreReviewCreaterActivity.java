package com.softmarshmallow.foodle.Views.StoreDetail.StoreReview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

import com.softmarshmallow.foodle.Models.Store.StoreReviewModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.FirebaseUserService;
import com.softmarshmallow.foodle.Services.StoreReviewService;

import java.util.Date;
// import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StoreReviewCreaterActivity extends AppCompatActivity
{

        private static final String TAG = StoreReviewCreaterActivity.class.getName();
        @BindView(R.id.ReviewRatingbar)
        RatingBar reviewRatingBar;

        @BindView(R.id.ReviewContentEditText)
        EditText reviewContentEditText;



        private static String baseStoreId;
        public static void StartCreateStoreReviewActivity(String baseStoreId, Context context){
                StoreReviewCreaterActivity.baseStoreId = baseStoreId;
                Intent intent = new Intent(context, StoreReviewCreaterActivity.class);
                context.startActivity(intent);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_WithActionBar);
                setContentView(R.layout.activity_store_review_creater);

                ButterKnife.bind(this);
        }


        @OnClick(R.id.UploadReviewButton)
        void OnUploadReviewButtonClick() {
                Log.d(TAG, "OnUploadReviewButtonClick");

                StoreReviewModel storeReviewModel = (StoreReviewModel) new StoreReviewModel()
                        .setCreatedTime(new Date())
                        .setReviewContent(reviewContentEditText.getText().toString())
                        .setRating(((int) reviewRatingBar.getRating()))
                        .setReviewerId(FirebaseUserService.GetUserUID());


                final SweetAlertDialog uploadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Uploading");
                uploadingDialog.show();

                new StoreReviewService.StoreReviewCreator
                        (
                                storeReviewModel,
                                StoreReviewCreaterActivity.baseStoreId,
                                new Consumer<StoreReviewModel>()
                                {
                                        @Override
                                        public void accept(StoreReviewModel storeReviewModel) throws Exception {
                                                uploadingDialog.dismissWithAnimation();
                                                finish();
                                        }
                                }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String s) throws Exception {
                                        uploadingDialog.dismissWithAnimation();
                                }
                        })
                        .CreateStoreReview();

        }


        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }

}
