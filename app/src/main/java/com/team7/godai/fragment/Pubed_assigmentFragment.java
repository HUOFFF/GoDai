package com.team7.godai.fragment;

 import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
 import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

 import com.team7.godai.activities.LoginActivity;
 import com.team7.godai.R;
import com.team7.godai.adapter.Pub_As_RecycleAdapter;
import com.team7.godai.Service.AssignmentService;
import com.team7.godai.domain.Assignment;

 import java.util.ArrayList;

/**
 * Created by mm on 2016/4/23.
 */
public class Pubed_assigmentFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ArrayList<Assignment> assignment= new ArrayList<>();


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
        mRecyclerView.setAdapter(new Pub_As_RecycleAdapter(getActivity(), assignment));

    }

    public void findView(){
        mRecyclerView = (RecyclerView)mRecyclerView.findViewById(R.id.recycler_view);
    }
    public void initData(){
        AssignmentService assignmentService = new AssignmentService(getActivity());
        assignment=assignmentService.GetPub_Assignment(LoginActivity.getUsername());

    }
}
