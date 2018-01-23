package com.softmarshmallow.foodle.Views.LocationEditor;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFormsFragment extends Fragment
{
        
        Unbinder unbinder;
        
        @BindView(R.id.nextButton)
        FancyButton nextButton;
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        @BindView(R.id.descriptionTextView)
        TextView descriptionTextView;
        @BindView(R.id.constraintLayout2)
        ConstraintLayout constraintLayout2;
        @BindView(R.id.textView10)
        TextView textView10;
        
        public AddressFormsFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_address_forms, container, false);
                unbinder = ButterKnife.bind(this, view);
                return view;
        }
        
        @Override
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }
        
        @OnClick(R.id.nextButton)
        public void onNextButtonClicked() {
                LocationEditorActivity.Instance.ShowLocationChooser();
        }
        
        
        @OnClick(R.id.exitButton)
        public void onExitButtonClicked() {
                getActivity().finish();
        }
}
