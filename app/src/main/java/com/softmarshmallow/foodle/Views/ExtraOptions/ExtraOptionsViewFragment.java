package com.softmarshmallow.foodle.Views.ExtraOptions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Helpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Festival.FestivalCreatorActivity;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.StoreEditor.StoreCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreEditor.StoreEditorBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraOptionsViewFragment extends Fragment
{

        public ExtraOptionsViewFragment() {
                // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_extra_options_view, container, false);

                ButterKnife.bind(this, view);

                return  view;
        }
        
        
        @OnClick(R.id.CreateStoreButton)
        void OnCreateStoreButtonClicked()
        {
                StoreEditorBaseActivity.SetEditingMode(StoreEditorBaseActivity.EditorType.Create);
                getActivity().startActivity(new Intent(getContext(), StoreCreatorActivity.class));
        }


        @OnClick(R.id.LogoutButton)
        void OnClearLoginDataButtonClicked()
        {
                LoginPreferences.clearLoginPreference();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
        }


        @OnClick(R.id.CreateNewFestivalButton)
        void OnCreateNewFestivalButtonClicked()
        {
                getActivity().startActivity(new Intent(getContext(), FestivalCreatorActivity.class));
        }

}
