package com.example.recyclearapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * recyclerView适配器
 * Created by Administrator on 2019/8/8.
 */
public class RcvBaseAdapter extends RecyclerView.Adapter<RcvBaseAdapter.BaseViewHolder> {


    private List<Integer> dataList = new ArrayList<Integer>();
    private OnItemClickListener mOnItemClickListener;

    public RcvBaseAdapter(List<Integer> dataList, OnItemClickListener mOnItemClickListener) {
        this.dataList = dataList;
        this.mOnItemClickListener = mOnItemClickListener;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dialog_fragment, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }


    //对RecyclerView子项数据进行赋值
    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        Integer t = dataList.get(position);
        int min = 300;
        int max = 500;
        int height = new Random().nextInt(max - min) + min;

        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height = height;//把随机的高度赋予itemView布局
        holder.imageView.setLayoutParams(params);//把params设置给itemView布局
        holder.imageView.setImageResource(t);
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //返回对应view的信息
                    int pos = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(holder.imageView, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //在adapter中自定义一个接口 实现想要实现的方法
    public interface OnItemClickListener {
        //子条目单机事件
        void onItemClick(View view, int position);


    }

    //  删除数据
    public void removeData(int position) {
        dataList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public BaseViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }


    }

}

