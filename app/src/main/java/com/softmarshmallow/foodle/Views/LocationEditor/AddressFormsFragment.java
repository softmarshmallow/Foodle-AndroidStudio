package com.softmarshmallow.foodle.Views.LocationEditor;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.softmarshmallow.foodle.CustomViews.DefaultEditTextContainerView.DefaultEditTextContainerView;
import com.softmarshmallow.foodle.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
        
        private static final String TAG = AddressFormsFragment.class.getSimpleName();
        Unbinder unbinder;
        
        @BindView(R.id.nextButton)
        FancyButton nextButton;
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        @BindView(R.id.descriptionTextView)
        TextView descriptionTextView;
        @BindView(R.id.countryEditTextContainer)
        DefaultEditTextContainerView countryEditTextContainer;
        @BindView(R.id.streetEditTextContainer)
        DefaultEditTextContainerView streetEditTextContainer;
        @BindView(R.id.locationDescriptionEditTextContainer)
        DefaultEditTextContainerView locationDescriptionEditTextContainer;
        
        
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
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                // Search Geo Coder
                try {
                        List<Address> results = geocoder.getFromLocationName(streetEditTextContainer.contentEditText.getText().toString(), 1);
                        
                        if (results.size() <= 0){
                                return;
                        }
                        
                        Address address =results.get(0);
                        if (address != null){
                                
                                Log.d(TAG, address.getCountryCode());
                                Log.d(TAG, address.getPostalCode() == null ? "NoPostalCode" : address.getPostalCode());
                                Log.d(TAG, address.getLatitude() + "");
                                Log.d(TAG, address.getLongitude() + "");
                                
                                LocationChooserFragment.Instance.googleMap.moveCamera(
                                        CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()), 17));
                        }
                }
                catch (IOException e) {
                        e.printStackTrace();
                }
                
                LocationEditorActivity.Instance.ShowLocationChooser();
        }
        
        
        @OnClick(R.id.exitButton)
        public void onExitButtonClicked() {
                getActivity().finish();
        }
}
