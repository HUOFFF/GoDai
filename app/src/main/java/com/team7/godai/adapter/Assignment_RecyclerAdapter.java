package com.team7.godai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team7.godai.R;
import com.team7.godai.domain.Assignment;
import com.team7.godai.fragment.AssignmentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mm on 2016/4/20.
 */
public class Assignment_RecyclerAdapter extends RecyclerView.Adapter<Assignment_RecyclerAdapter.ViewHolder>  {


    private Context mContext;
    private ArrayList<Assignment> Assignment;

    public Assignment_RecyclerAdapter(Context mContext,ArrayList Assignment) {
        this.mContext = mContext;
        this.Assignment = Assignment;
    }

    @Override
    public Assignment_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_assigment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Assignment_RecyclerAdapter.ViewHolder holder, int position) {
        holder.destination.setText(Assignment.get(position).getDestination());
        holder.dormitory.setText(Assignment.get(position).getDormitory());
        holder.money.setText(Assignment.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return Assignment.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView dormitory;
        public TextView destination;
        public TextView money;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            destination = (TextView)mView.findViewById(R.id.tv_destination);
            dormitory = (TextView)mView.findViewById(R.id.tv_dormitory);
            money = (TextView)mView.findViewById(R.id.tv_money);
        }
    }

}
