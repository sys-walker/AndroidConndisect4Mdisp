package com.eps.androidconnect4.UIgame;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.eps.androidconnect4.MainActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
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
        int size_dt = MainActivity.b.getInt("MIDA_GRAELLA");
        this.mThumbIds[size_dt*fila+columna]=resource;
    }
    public void Arrayize(){
        mThumbIds = new Integer[ids.size()];
        Iterator<Integer> it = ids.iterator();
        int i=0;

        while (it.hasNext()){
            mThumbIds[i] = it.next();
            i++;
        }


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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0,0,0,0);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setBackgroundColor(Color.BLUE);
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
