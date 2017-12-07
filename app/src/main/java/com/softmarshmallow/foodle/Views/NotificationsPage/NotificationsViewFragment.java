package com.softmarshmallow.foodle.Views.NotificationsPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Services.NotificationService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsViewFragment extends Fragment
{
        
        
        @BindView(R.id.noDataToDisplay)
        TextView noDataToDisplayTextView;
        
        public NotificationsViewFragment() {
                // Required empty public constructor
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_notifications_view, container,
                        false);
                ButterKnife.bind(this, view);
        
                noDataToDisplay();
                // Init notifications
//                InitNotificationsRecyclerView();
                
                return view;
        }
        
        // region InitNotificationsRecyclerView
        @BindView(R.id.notificationsRecyclerView)
        RecyclerView notificationsRecyclerView;
        
        NotificationsAdapter notificationsAdapter;
        
        public void InitNotificationsRecyclerView() {
                notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                notificationsAdapter = new NotificationsAdapter(
                        NotificationService.GetAllNotifications(), getContext());
                notificationsRecyclerView.setAdapter(notificationsAdapter);
        }
        
        void noDataToDisplay(){
                noDataToDisplayTextView.setVisibility(View.VISIBLE);
        }
        

        // endregion
        
}
