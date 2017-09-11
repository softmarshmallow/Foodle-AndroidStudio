package com.softmarshmallow.foodle.Views.OrderMenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderMenuActivity extends AppCompatActivity
{
        
        
        public static MenuModel OrderringMenuData;
        
        
        @BindView(R.id.menuThumbnailImageView)
        ImageView menuThumbnailImageView;

        @BindView(R.id.OrderingMenuNameTextView)
        TextView orderingMenuNameTextView;

        @BindView(R.id.OrderingMenuPriceTextView)
        TextView orderingMenuPriceTextView;
        
        @BindView(R.id.orderTotalPriceTextView)
        TextView orderTotalPriceTextView;
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_WithActionBar);

                setContentView(R.layout.activity_order_menu);
                ButterKnife.bind(this);
                
                
                LoadOrderringMenuData();
                InitOrderCountStepperTouch();
        }
        
        
        void LoadOrderringMenuData() {
                if (OrderringMenuData == null) {
                        return;
                }
                
                // SET image
                Glide.with(this)
                        .load(OrderringMenuData.MenuMainPhotoUrl)
                        .into(menuThumbnailImageView);

                // SET Name
                orderingMenuNameTextView.setText(OrderringMenuData.MenuName);
                
                // SET Total Price
                setTotalPrice(OrderringMenuData.MenuPrice);
                // Set Price
                orderingMenuPriceTextView.setText("₩ "+String.valueOf(OrderringMenuData.MenuPrice));
        }
        
        
        @BindView(R.id.orderCountStepperTouch)
        StepperTouch orderCountStepperTouch;
        
        void InitOrderCountStepperTouch() {
                orderCountStepperTouch.stepper.setValue(1);
                orderCountStepperTouch.stepper.setMin(1);
                orderCountStepperTouch.stepper.setMax(10);
                orderCountStepperTouch.stepper.addStepCallback(new OnStepCallback()
                {
                        @Override
                        public void onStep(int i, boolean b) {
                                setOrderCount(i);
                        }
                });
        }
        
        
        int orderCount;
        
        public void setOrderCount(int orderCount) {
                this.orderCount = orderCount;
                setTotalPrice(OrderringMenuData.MenuPrice * orderCount);
        }
        
        int totalPrice;
        
        public void setTotalPrice(int totalPrice) {
                this.totalPrice = totalPrice;
                orderTotalPriceTextView.setText("₩ " + totalPrice);
        }
        
        
        
        @OnClick(R.id.orderButton)
        void OnOrderButtonClick(){

                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("주문할까요?")
                        .setContentText("취소시 수수료가 발생합니다.")
                        .setConfirmText("좋아 먹자!!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        finish();

                                }
                        })
                        .show();

        }




        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
}
