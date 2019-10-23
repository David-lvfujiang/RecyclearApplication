package com.example.recyclearapplication;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Administrator on 2019/8/8.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    public BaseViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.imageView);
    }


    
}
