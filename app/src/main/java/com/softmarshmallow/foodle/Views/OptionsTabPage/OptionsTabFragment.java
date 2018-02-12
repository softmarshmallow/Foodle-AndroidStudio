package com.softmarshmallow.foodle.Views.OptionsTabPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsTabFragment extends Fragment
{
        
        
        public OptionsTabFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                return inflater.inflate(R.layout.fragment_options_tab, container, false);
        }
        
}
