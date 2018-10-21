package com.softmarshmallow.foodle.Views.OrderMenu;

import android.content.Context;
import android.content.Intent;
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
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
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
        
                NotifiyUserThatThisFeatureIsNotImplemented();
        }
        
        void NotifiyUserThatThisFeatureIsNotImplemented(){
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("준비중..")
                        .setContentText("이 기능은 아직 지원 되지 않습니다.\n구경만 할까요?")
                        .show();
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

                SweetAlertDialog orderConfirmAlert = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("주문확인")
                        .setContentText("푸드트럭에게 주문확인서를 제시해 주세요")
                        //region confirm
                        .setConfirmText("확인")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
                        {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        finish();
                                        startActivity(new Intent(OrderMenuActivity.this, OrderSheetActivity.class));
                                }
                        });
        
                orderConfirmAlert.setCancelable(false);
                orderConfirmAlert.show();
        
        
        }




        @Override
        protected void attachBaseContext(Context newBase) {
                super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        }
        
}
