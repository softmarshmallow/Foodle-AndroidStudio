package com.softmarshmallow.foodle.Views.NotificationsPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.NotificationModel;
import com.softmarshmallow.foodle.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UZU on 02/09/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter
{
        
        
        List<NotificationModel> notificationDatas;
        Context context;
        
        public NotificationsAdapter(List<NotificationModel> notificationDatas, Context context) {
                this.notificationDatas = notificationDatas;
                this.context = context;
                
        }
        
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.card_notification, parent, false);
                
                return new NotificationViewHolder(itemView, context);
        }
        
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                NotificationViewHolder notificationViewHolder = (NotificationViewHolder) holder;
                notificationViewHolder.BindViewWithData(notificationDatas.get(position));
        }
        @Override
        public int getItemCount() {
                return notificationDatas.size();
        }
}


class NotificationViewHolder extends RecyclerView.ViewHolder
{
        
        @BindView(R.id.notificationThumbnailImageView)
        ImageView notificationThumbnailImageView;
        
        @BindView(R.id.notificationContentTextView)
        TextView notificationContentTextView;
        
        @BindView(R.id.notificationTimeTextView)
        TextView notificationTimeTextView;
        
        
        Context context;
        
        public NotificationViewHolder(View itemView, Context context) {
                super(itemView);
                this.context = context;
                ButterKnife.bind(this, itemView);
        }
        
        public void BindViewWithData(NotificationModel notificationData) {
                Glide.with(context)
                        .load(notificationData.NotificationThumbnailUrl)
                        .into(notificationThumbnailImageView);
        
                notificationContentTextView.setText(notificationData.NotificationContent);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                notificationTimeTextView.setText(simpleDateFormat.format(notificationData.NotificationTime));
        }
}