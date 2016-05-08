package com.team7.godai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team7.godai.R;

import java.util.ArrayList;

/**
 * Created by keng on 2016/5/7.
 */
public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> address;

    public Address_Adapter(Context mContext, ArrayList<String> address){
        this.mContext = mContext;
        this.address = address;
    }

    @Override
    public Address_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Address_Adapter.ViewHolder holder, int position) {
        holder.address_item.setText("地址"+(position+1)+":");
        holder.address_info.setText(address.get(position));
    }

    @Override
    public int getItemCount() {
        return address.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        TextView address_item;
        TextView address_info;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            address_item = (TextView)mView.findViewById(R.id.address_item);
            address_info = (TextView)mView.findViewById(R.id.user_info_des);

        }
    }
}
