package com.team7.godai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team7.godai.LoginActivity;
import com.team7.godai.R;
import com.team7.godai.Service.Re_AssignmentService;
import com.team7.godai.adapter.Assignment_RecyclerAdapter;
import com.team7.godai.domain.Assignment;

import java.util.ArrayList;


public class Re_AssignmentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Assignment> assignment= new ArrayList<>();
    public static Re_AssignmentFragment re_assignmentFragment;

    public Re_AssignmentFragment(){
        re_assignmentFragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycleview, container, false);
        return mRecyclerView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        initData();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(new Assignment_RecyclerAdapter(getActivity(), assignment));
    }

    public void findView(){
        mRecyclerView = (RecyclerView)mRecyclerView.findViewById(R.id.recycler_view);
    }

    public void initData(){
        Re_AssignmentService assignmentService = new Re_AssignmentService(getActivity());
        assignment=assignmentService.getRe_Assignment(LoginActivity.getUsername());

    }
}
