package com.team7.godai.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.team7.godai.activities.ExpActivity;
import com.team7.godai.activities.LoginActivity;
import com.team7.godai.R;
import com.team7.godai.Service.AssignmentService;
import com.team7.godai.Service.Re_AssignmentService;
import com.team7.godai.domain.Assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by mm on 2016/4/20.
 */
public class Re_Assignment_RecyclerAdapter extends RecyclerView.Adapter<Re_Assignment_RecyclerAdapter.ViewHolder>  {


    private Context mContext;
    private ArrayList<Assignment> Assignment;

    public Re_Assignment_RecyclerAdapter(Context mContext, ArrayList Assignment) {
        this.mContext = mContext;
        this.Assignment = Assignment;
    }

    @Override
    public Re_Assignment_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item_receive, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Re_Assignment_RecyclerAdapter.ViewHolder holder, final int position) {
        holder.destination.setText(Assignment.get(position).getDestination());
        holder.dormitory.setText(Assignment.get(position).getDormitory());
        holder.money.setText(Assignment.get(position).getMoney());
        holder.user.setText(Assignment.get(position).getUser());
        holder.status.setText(Assignment.get(position).getStatus());
        final AssignmentService AS = new AssignmentService(mContext);
        if(AS.getAssignment_status(Assignment.get(position).getAssignment_id()).equals("true")) {
            holder.assignment_receive.setEnabled(false);
            holder.assignment_receive.setText("任务已被领取");
        }
        holder.assignment_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExpActivity.expActivity);
                builder.setTitle("确认领取");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
                        String date = sDateFormat.format(new java.util.Date());

                        Re_AssignmentService Re_AS = new Re_AssignmentService(mContext);
                        Assignment assignment = new Assignment();

                        assignment.setDestination(Assignment.get(position).getDestination());
                        assignment.setDormitory(Assignment.get(position).getDormitory());
                        assignment.setMoney(Assignment.get(position).getMoney());
                        assignment.setUser(Assignment.get(position).getUser());
                        assignment.setRe_User(LoginActivity.getUsername());
                        assignment.setStatus("" + date + "领取");
                        assignment.setre_OR_not("true");
                        assignment.setAssignment_id(Assignment.get(position).getAssignment_id());
                        AS.changeAssignment_status(assignment.getStatus(), assignment.getre_OR_not(), assignment.getAssignment_id());
                        Re_AS.Re_assignment(assignment);

                        Toast.makeText(mContext, "领取成功", Toast.LENGTH_LONG).show();

                        ExpActivity.expActivity.initView();
                        ExpActivity.expActivity.mViewPager.setCurrentItem(0);
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
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
        public TextView user;
        public TextView status;
        public Button assignment_receive;

        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            destination = (TextView)mView.findViewById(R.id.re_destination);
            dormitory = (TextView)mView.findViewById(R.id.re_dormitory);
            money = (TextView)mView.findViewById(R.id.re_money);
            user = (TextView)mView.findViewById(R.id.re_user);
            status = (TextView)mView.findViewById(R.id.re_status);
            assignment_receive = (Button)mView.findViewById(R.id.assignment_receive);

        }
    }

}
