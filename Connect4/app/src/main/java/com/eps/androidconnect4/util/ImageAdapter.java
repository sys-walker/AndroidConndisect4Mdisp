package com.eps.androidconnect4.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class ImageAdapter extends BaseAdapter {
    public Context mContext;
    public Integer[] mThumbIds;
    public ArrayList<Integer> ids = new ArrayList<>();

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public void add(Integer id_res){
        ids.add(id_res);
    }
    public void add_position(int fila,int columna,int resource){
        //size by data transfer
        int size_dt = 7;
        this.mThumbIds[size_dt*fila+columna]=resource;

    }
    public void Arrayize(){
        mThumbIds = ids.toArray(new Integer[0]);
        ids = new ArrayList<>();
        Collections.addAll(ids,mThumbIds);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public ArrayList<Integer> getArray() {
        return ids;
    }


    public void putArray(Integer[] toArray) {
        mThumbIds = toArray;
        ids = new ArrayList<>();
        Collections.addAll(ids,mThumbIds);
    }
}