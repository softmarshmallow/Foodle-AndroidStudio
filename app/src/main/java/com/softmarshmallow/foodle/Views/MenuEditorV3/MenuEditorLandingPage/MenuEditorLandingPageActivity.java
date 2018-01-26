package com.softmarshmallow.foodle.Views.MenuEditorV3.MenuEditorLandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.MenuEditorV3.MenuBasicFormsPage.MenuBasicFormsPageActivity;
import com.softmarshmallow.foodle.Views.StoreEditorV3.CustomViews.LandingPageSectionItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

public class MenuEditorLandingPageActivity extends AppCompatActivity
{
        
        
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        @BindView(R.id.previewButton)
        FancyButton previewButton;
        
        @BindView(R.id.section_basic_forms)
        LandingPageSectionItemView sectionBasicForms;
        @BindView(R.id.section_photos)
        LandingPageSectionItemView sectionPhotos;
        @BindView(R.id.section_menu_extra_options)
        LandingPageSectionItemView sectionMenuExtraOptions;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_editor_landing_page);
                ButterKnife.bind(this);
        
                initSection();
        }
        
        void initSection() {
                sectionBasicForms.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(MenuEditorLandingPageActivity.this, MenuBasicFormsPageActivity.class);
                                startActivity(intent);
                        }
                });
        
                sectionPhotos.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View v) {
                  
                        }
                });
        
                sectionMenuExtraOptions.setOnContinueButtonClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View v) {
                  
                        }
                });
        
        
        }
}
