package com.softmarshmallow.foodle.Views.ExtraOptions_Deprecated;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softmarshmallow.foodle.CustomViews.OptionItemView.OptionItemView;
import com.softmarshmallow.foodle.Helpers.ContactToFoodleHelper;
import com.softmarshmallow.foodle.Helpers.LoginHelpers.LoginPreferences;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.LoginService;
import com.softmarshmallow.foodle.Views.Login.LoginActivity;
import com.softmarshmallow.foodle.Views.StoreEditor_Deprecated.StoreCreatorActivity;
import com.softmarshmallow.foodle.Views.StoreUploadRequestView.StoreUploadRequestActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Option_Tab_Fragment extends Fragment
{

        @BindView(R.id.profilePhotoImageView)
        CircleImageView profilePhotoImageView;



        @BindView(R.id.option_setting)
        View option_setting;
        @BindView(R.id.option_help)
        View option_help;
        @BindView(R.id.option_change)
        View option_change;
        @BindView(R.id.option_storeupload)
        View option_storeupload;
        @BindView(R.id.option_feedback)
        View option_feedback;





        public Option_Tab_Fragment() {
                // Required empty public constructor
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_options_tab, container,
                        false);
                
                ButterKnife.bind(this, view);
                InitButtons();

                //
                return view;
        }

        private void InitButtons() {
                option_setting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });
                ImageView img = (ImageView)option_setting.findViewById(R.id.optionImageView);
                img.setImageResource(R.drawable.ic_setting);
                TextView text =(TextView)option_setting.findViewById(R.id.optionNameTextView);
                text.setText("설정");
                option_help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });

                ImageView img_help = (ImageView)option_help.findViewById(R.id.optionImageView);
                img.setImageResource(R.drawable.ic_question);
                TextView text_hep =(TextView)option_help.findViewById(R.id.optionNameTextView);
                text.setText("도움말");

                option_change.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();

                        }
                });

                ImageView  img_change = (ImageView)option_change.findViewById(R.id.optionImageView);
                img.setImageResource(R.drawable.ic_random);
                TextView text_change =(TextView)option_change.findViewById(R.id.optionNameTextView);
                text.setText("스토어 계정 전환");
                option_storeupload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                getActivity().startActivity(
                                        new Intent(getContext(), StoreUploadRequestActivity.class));

                        }
                });
                option_feedback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("준비중")
                                        .setContentText("준비중입니다.")
                                        .show();
                        }
                });

        }


}
