package com.softmarshmallow.foodle.Views.StoreEditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.softmarshmallow.foodle.R;

import butterknife.ButterKnife;

public class StoreEditorActivity extends AppCompatActivity
{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_store_editor);
                ButterKnife.bind(this);
        }
}
