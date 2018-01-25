package com.softmarshmallow.foodle.Views.MenuEditorV3.MenuEditorListingPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.MenuEditorV3.MenuEditorLandingPage.MenuEditorLandingPageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuEditorListingPageActivity extends AppCompatActivity
{
        
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_editor_listing_page);
                ButterKnife.bind(this);
        }
        
        @OnClick(R.id.addItemContainer)
        public void onAddItemContainerClicked() {
                Intent intent = new Intent(this, MenuEditorLandingPageActivity.class);
                startActivity(intent);
        }
        
        
        
        
        @OnClick(R.id.exitButton)
        public void onexitButtonClicked() {
                onBackPressed();
        }
        @Override
        public void onBackPressed() {
                super.onBackPressed();
        }
}
