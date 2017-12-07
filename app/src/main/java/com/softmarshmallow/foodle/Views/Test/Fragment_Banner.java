package com.softmarshmallow.foodle.Views.Test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.R;


public class Fragment_Banner extends Fragment
{

    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);



        return view;

    }
    }
