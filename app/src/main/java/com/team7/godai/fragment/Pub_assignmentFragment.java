package com.team7.godai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team7.godai.R;
import com.team7.godai.database.AssignmentService;
import com.team7.godai.domain.Assignment;

/**
 * Created by mm on 2016/4/20.
 */
public class Pub_assignmentFragment extends Fragment {
    private View mParentView;
    private EditText destination;
    private EditText dormitory;
    private EditText money;
    private Button pub_bt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_pub_assignment, container, false);
        return mParentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        Input();

    }

    public void findView(){
        destination = (EditText)mParentView.findViewById(R.id.destination);
        dormitory = (EditText)mParentView.findViewById(R.id.dormitory);
        money = (EditText)mParentView.findViewById(R.id.money);
        pub_bt = (Button)mParentView.findViewById(R.id.pub);
    }
    public void Input(){

        pub_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mdestination = destination.getText().toString().trim();
                String mdormitory = dormitory.getText().toString().trim();
                String mmoney = money.getText().toString().trim();

                AssignmentService AS = new AssignmentService(getActivity());
                Assignment assignment = new Assignment();
                assignment.setDestination(mdestination);
                assignment.setDormitory(mdormitory);
                assignment.setMoney(mmoney);
                AS.Pub(assignment);

                Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_LONG).show();

                destination.setText("");
                dormitory.setText("");
                money.setText("");
            }
            });

    }
}
