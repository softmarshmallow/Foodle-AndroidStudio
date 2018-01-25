package com.softmarshmallow.foodle.Views.LocationEditor;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationChooserFragment extends Fragment implements OnMapReadyCallback
{
        public static LocationChooserFragment Instance;
        
        SupportMapFragment map;
        Unbinder unbinder;
        @BindView(R.id.confirmButton)
        FancyButton confirmButton;
        @BindView(R.id.exitButton)
        ImageButton exitButton;
        
        public LocationChooserFragment() {
                // Required empty public constructor
        }
        
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Instance = this;
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_location_chooser, container, false);
                unbinder = ButterKnife.bind(this, view);
                
                // Map
                map = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
                SetUpMap();
                
                
                return view;
        }
        
        
        //region map
        GoogleMap googleMap;
        
        void SetUpMap() {
                if (googleMap == null) {
                        map.getMapAsync(this);
                }
        }
        
        @Override
        public void onMapReady(GoogleMap googleMap) {
                this.googleMap = googleMap;
        }
        //endregion
        
        
        @Override
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }
        
        
        @OnClick(R.id.confirmButton)
        public void onConfirmButtonClicked() {
                Toast.makeText(getContext(), "" + googleMap.getCameraPosition().target,
                        Toast.LENGTH_SHORT)
                        .show();
        }
        
        @OnClick(R.id.exitButton)
        public void onExitButtonClicked() {
                LocationEditorActivity.Instance.ShowAddreessForms();
        }
}
