package com.softmarshmallow.foodle.Views.Test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Featured.ListStore.StoreItemViewHolder;

/**
 * Created by uzu on 9/7/17.
 */

public class TestRecyclerViewAdapter extends RecyclerView.Adapter
{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.card_test, parent, false);

                return new TestViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView textView = holder.itemView.findViewById(R.id.indexTextView);
                textView.setText("index : " + position);
        }

        @Override
        public int getItemCount() {
                return 80;
        }
}

class TestViewHolder extends RecyclerView.ViewHolder
{

        public TestViewHolder(View itemView) {
                super(itemView);
        }
}
