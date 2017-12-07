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

import com.softmarshmallow.foodle.R;


import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


import java.util.List;

/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_Dish extends RecyclerView.Adapter<RecycleAdapter_Dish.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private List<BeanClassForDish> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {




        ImageView image;
        TextView dish_name;
        TextView dish_type;
        TextView price;




        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            dish_name = (TextView) view.findViewById(R.id.tv_dish_name);
            dish_type = (TextView) view.findViewById(R.id.tv_dish_type);
            price = (TextView) view.findViewById(R.id.tv_price);
        }

    }



    public RecycleAdapter_Dish(Context context, List<BeanClassForDish> moviesList) {
        this.moviesList = moviesList;
       this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dish_list, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        
        
        
        BeanClassForDish movie = moviesList.get(position);
        holder.dish_name.setText(movie.getDish_name());
        holder.dish_type.setText(movie.getDish_type());
        holder.price.setText(movie.getPrice());
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


