package com.softmarshmallow.foodle.Views.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.softmarshmallow.foodle.R;

import java.util.List;



/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_Cusine extends RecyclerView.Adapter<RecycleAdapter_Cusine.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private List<BeanClassForCusine> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {




        ImageView image;
        TextView cusine_name;
        TextView price;
        TextView city;




        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            cusine_name = (TextView) view.findViewById(R.id.tv_cusine_name);
            price = (TextView) view.findViewById(R.id.tv_price);
            city = (TextView) view.findViewById(R.id.tv_city);
        }

    }



    public RecycleAdapter_Cusine(Context context, List<BeanClassForCusine> moviesList) {
        this.moviesList = moviesList;
       this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cusine_list, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {



        BeanClassForCusine movie = moviesList.get(position);
        holder.cusine_name.setText(movie.getCuine_name());
        holder.price.setText(movie.getPrice());
        holder.city.setText(movie.getCity());
        holder.image.setImageResource(movie.getImage());

       


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // You can pass your own memory cache implementation
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(10)) //rounded corner bitmap
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage("drawable://"+ movie.getImage(),holder.image, options );



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


