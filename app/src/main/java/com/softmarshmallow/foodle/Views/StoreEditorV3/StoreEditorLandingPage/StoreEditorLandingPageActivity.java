package com.softmarshmallow.foodle.Views.StoreEditorV3.StoreEditorLandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreEditorV3.CustomViews.LandingPageSectionItemView;
import com.softmarshmallow.foodle.Views.StoreEditorV3.StoreBasicInfoFormsPage.StoreBasicFormsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreEditorLandingPageActivity extends AppCompatActivity
{
        
        @BindView(R.id.section_basic_forms)
        LandingPageSectionItemView sectionBasicForms;
        @BindView(R.id.section_location)
        LandingPageSectionItemView sectionLocation;
        @BindView(R.id.section_photos)
        LandingPageSectionItemView sectionPhotos;
        @BindView(R.id.section_menus)
        LandingPageSectionItemView sectionMenus;
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_editor_landing_page);
                ButterKnife.bind(this);
                
                
                initSectionActions();
        }
        
        
        void initSectionActions() {
                
                // Section  sectionBasicForms
                sectionBasicForms.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(StoreEditorLandingPageActivity.this,
                                        StoreBasicFormsActivity.class);
                                startActivity(intent);
                        }
                });
                
                
                // Section  sectionLocation
                sectionLocation.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(StoreEditorLandingPageActivity.this, "NA", Toast.LENGTH_SHORT).show();
                        }
                });
                
                
                
                // Section  sectionPhotos
                sectionPhotos.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(StoreEditorLandingPageActivity.this, "NA", Toast.LENGTH_SHORT).show();
                        }
                });
                
                
                
                // Section  sectionMenus
                sectionMenus.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(StoreEditorLandingPageActivity.this, "NA", Toast.LENGTH_SHORT).show();
                        }
                });
        }
        
        
        @OnClick(R.id.exitButton)
        public void onExitButtonClicked() {
                finish();
        }
}
