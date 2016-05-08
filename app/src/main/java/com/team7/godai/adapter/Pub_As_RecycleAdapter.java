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

import com.team7.godai.PubActivity;
import com.team7.godai.R;
import com.team7.godai.Service.AssignmentService;
import com.team7.godai.Service.His_AssignmentService;
import com.team7.godai.Service.Re_AssignmentService;
import com.team7.godai.domain.Assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by mm on 2016/4/23.
 */
public class Pub_As_RecycleAdapter extends RecyclerView.Adapter<Pub_As_RecycleAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Assignment> Assignment;

    public Pub_As_RecycleAdapter(Context mContext,ArrayList Assignment) {
        this.mContext = mContext;
        this.Assignment = Assignment;
    }

    @Override
    public Pub_As_RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_isre_assigment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Pub_As_RecycleAdapter.ViewHolder holder, final int position) {
        final Re_AssignmentService Re_as =new Re_AssignmentService(mContext);
        final AssignmentService AS = new AssignmentService(mContext);
        holder.destination.setText(Assignment.get(position).getDestination());
        holder.dormitory.setText(Assignment.get(position).getDormitory());
        holder.money.setText(Assignment.get(position).getMoney());
        holder.user.setText(Assignment.get(position).getUser());
        holder.re_user.setText(Re_as.getRe_User(Assignment.get(position).getAssignment_id()));
        holder.status.setText(Assignment.get(position).getStatus());

        final Button isre = holder.isre;

        if(AS.getAssignment_status(Assignment.get(position).getAssignment_id()).equals("false")) {
            isre.setEnabled(false);
        }

        holder.isre.setOnClickListener(new View.OnClickListener() {
            His_AssignmentService his_assignmentService = new His_AssignmentService(mContext);

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PubActivity.pubActivity);
                builder.setTitle("确定收货");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
                        String date = sDateFormat.format(new java.util.Date());

                        Assignment assignment = new Assignment();

                        assignment.setDestination(Assignment.get(position).getDestination());
                        assignment.setDormitory(Assignment.get(position).getDormitory());
                        assignment.setMoney(Assignment.get(position).getMoney());
                        assignment.setUser(Assignment.get(position).getUser());
                        assignment.setRe_User(Re_as.getRe_User(Assignment.get(position).getAssignment_id()));
                        assignment.setStatus("" + date + "完成");
                        assignment.setAssignment_id(Assignment.get(position).getAssignment_id());

                        his_assignmentService.Add_FinishAssignment(assignment);

                        AS.FinishAssignment(Assignment.get(position).getAssignment_id());
                        Re_as.FinishRe_Assignment(Assignment.get(position).getAssignment_id());

                        PubActivity.pubActivity.initView();
                        PubActivity.pubActivity.mViewPager.setCurrentItem(2);

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
        public TextView re_user;
        public TextView status;
        public Button isre;

        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            destination = (TextView) mView.findViewById(R.id.re1_destination);
            dormitory = (TextView) mView.findViewById(R.id.re1_dormitory);
            money = (TextView) mView.findViewById(R.id.re1_money);
            user = (TextView)mView.findViewById(R.id.re1_pub_user);
            re_user = (TextView)mView.findViewById(R.id.re1_re_user);
            status = (TextView)mView.findViewById(R.id.re1_status);
            isre = (Button)mView.findViewById(R.id.assignment_isreceive);
        }
    }
}
