package com.team7.godai.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team7.godai.activities.LoginActivity;
import com.team7.godai.activities.PubActivity;
import com.team7.godai.R;
import com.team7.godai.Service.AddressService;
import com.team7.godai.Service.AssignmentService;
import com.team7.godai.domain.Assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by mm on 2016/4/20.
 */
public class Pub_assignmentFragment extends Fragment {
    private View mParentView;
    private EditText destination;
    private Button dormitory;
    private EditText money;
    private Button pub_bt;
    String[] address;

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

    public void findView() {
        destination = (EditText) mParentView.findViewById(R.id.destination);
        dormitory = (Button) mParentView.findViewById(R.id.dormitory);
        money = (EditText) mParentView.findViewById(R.id.money);
        pub_bt = (Button) mParentView.findViewById(R.id.pub);
    }

    public void Input() {

        AddressService addressService = new AddressService(getActivity());
        ArrayList<String> add_list = addressService.getAddress(LoginActivity.getUsername());
        address = add_list.toArray(new String[]{});

        dormitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChoiceOnClickListener choiceListener = new ChoiceOnClickListener();
                new AlertDialog.Builder(getActivity())
                        .setTitle("选择地址")
                        .setSingleChoiceItems(address, 0, choiceListener)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int choicewhich = choiceListener.getWhich();
                                String str = address[choicewhich];
                                dormitory.setText("" + str + "");
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });


        pub_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PubActivity.pubActivity);
                builder.setTitle("确认发布");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mdestination = destination.getText().toString().trim();
                        String mdormitory = dormitory.getText().toString().trim();
                        String mmoney = money.getText().toString().trim();

                        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
                        String date = sDateFormat.format(new java.util.Date());

                        AssignmentService AS = new AssignmentService(getActivity().getApplicationContext());
                        Assignment assignment = new Assignment();
                        assignment.setDestination(mdestination);
                        assignment.setDormitory(mdormitory);
                        assignment.setMoney(mmoney);
                        assignment.setUser(LoginActivity.getUsername());
                        assignment.setStatus("" + date + "发布");
                        assignment.setre_OR_not("false");

                        AS.Pub(assignment);

                        Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_LONG).show();

                        destination.setText("");
                        dormitory.setText("");
                        money.setText("");

                        PubActivity.pubActivity.initView();
                        PubActivity.pubActivity.mViewPager.setCurrentItem(1);
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

    private class ChoiceOnClickListener implements DialogInterface.OnClickListener {

        private int which = 0;

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            this.which = which;
        }

        public int getWhich() {
            return which;
        }
    }
}
