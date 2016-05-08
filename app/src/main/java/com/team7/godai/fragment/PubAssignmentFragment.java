package com.team7.godai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team7.godai.R;
import com.team7.godai.Service.AssignmentService;
import com.team7.godai.activities.LoginActivity;
import com.team7.godai.adapter.Assignment_RecyclerAdapter;
import com.team7.godai.adapter.Re_Assignment_RecyclerAdapter;
import com.team7.godai.domain.Assignment;

import java.util.ArrayList;


public class PubAssignmentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Assignment> assignment= new ArrayList<>();
    public static PubAssignmentFragment assignmentFragment;

    public PubAssignmentFragment(){
        assignmentFragment = this;
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
        AssignmentService assignmentService = new AssignmentService(getActivity());
        assignment=assignmentService.GetAssignment();

    }

}
