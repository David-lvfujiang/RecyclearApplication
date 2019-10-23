package com.example.recyclearapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RecyclerView recyclerView;
    private RcvBaseAdapter rcvBaseAdapter;
    private static ArrayList<Integer> imgList;
    private int[] imgDatas = {R.mipmap.one, R.mipmap.three, R.mipmap.two, R.mipmap.five};
    final private int DATAS_LENGHT = 50;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        init();//初始化控件
        //初始化数据源
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < DATAS_LENGHT; i++) {
                    imgList.add(imgDatas[i % imgDatas.length]);
                }
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));

                RcvBaseAdapter.OnItemClickListener listener = new RcvBaseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        index = position;
                        // Toast.makeText(RecyclerViewActivity.this,"这是第"+(position+1)+"张图片",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
//                        intent.putExtra("position", position);
//                        intent.putIntegerArrayListExtra("imgList", (ArrayList<Integer>) imgList);
//                        startActivityForResult(intent,0);


                    }

                };
                rcvBaseAdapter = new RcvBaseAdapter(imgList, listener);//创建适配器
                recyclerView.setAdapter(rcvBaseAdapter);


            }
        }).start();


    }

    /**
     * 接受activity的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 200) {
//            imgList.remove(index);//更新数据源
//            rcvBaseAdapter.notifyDataSetChanged();//更新适配器
            rcvBaseAdapter.removeData(index);//删除item
        }

    }

    /**
     * 初始化方法
     */
    public void init() {
        recyclerView = (RecyclerView) this.findViewById(R.id.rview);
        imgList = new ArrayList<Integer>();

    }


}
