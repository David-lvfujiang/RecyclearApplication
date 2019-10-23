package com.example.listView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.recyclearapplication.MainActivity;
import com.example.recyclearapplication.R;

import java.util.ArrayList;

public class ExpandabelListViewActivity extends AppCompatActivity {

    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
    private ExpandableListView exlist_lol;
    private EListViewAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandabel_list_view);
        mContext = ExpandabelListViewActivity.this;
        exlist_lol =  findViewById(R.id.e_list_view);

        //数据准备
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group("AD"));
        gData.add(new Group("AP"));
        gData.add(new Group("TANK"));

        lData = new ArrayList<Item>();

        //AD组
        lData.add(new Item(R.mipmap.six,"剑圣"));
        lData.add(new Item(R.mipmap.six,"德莱文"));
        lData.add(new Item(R.mipmap.six,"男枪"));
        lData.add(new Item(R.mipmap.six,"韦鲁斯"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<Item>();
        lData.add(new Item(R.mipmap.six, "提莫"));
        lData.add(new Item(R.mipmap.six, "安妮"));
        lData.add(new Item(R.mipmap.six, "天使"));
        lData.add(new Item(R.mipmap.six, "泽拉斯"));
        lData.add(new Item(R.mipmap.six, "狐狸"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<Item>();
        lData.add(new Item(R.mipmap.six, "诺手"));
        lData.add(new Item(R.mipmap.six, "德邦"));
        lData.add(new Item(R.mipmap.six, "奥拉夫"));
        lData.add(new Item(R.mipmap.six, "龙女"));
        lData.add(new Item(R.mipmap.six, "狗熊"));
        iData.add(lData);

        myAdapter = new EListViewAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);

        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getiName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}