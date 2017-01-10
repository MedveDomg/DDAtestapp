package com.example.medvedomg.ddatest.ui.fragment;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.model.Course;
import com.example.medvedomg.ddatest.ui.activity.MainActivityImpl;
import com.example.medvedomg.ddatest.ui.adapter.DialogAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyDialogFragment extends DialogFragment implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private DialogAdapter adapter;
    private TextView tvAverageMark;
    private DialogAdapter dialogAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int mark = 0;
    float divide = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        ArrayList<String> names = new ArrayList<>();
        names.addAll(bundle.getStringArrayList("name"));
        ArrayList<Integer> marks = new ArrayList<>();
        marks.addAll(bundle.getIntegerArrayList("mark"));
        for (Integer markCount : marks) {
            divide = ((float) divide + markCount);
        }
        divide = divide / marks.size();
        Log.d("TAG", divide+"");
        dialogAdapter = new DialogAdapter(names, marks ,getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_dialog);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(dialogAdapter);
        mRecyclerView.setHasFixedSize(true);
        tvAverageMark = (TextView) v.findViewById(R.id.tv_dialog_mark);
        tvAverageMark.setText("Average mark: " + divide);
        Button button = (Button) v.findViewById(R.id.btn_dialog_ok);
        button.setOnClickListener(this);
        return v;
    }



    public static MyDialogFragment newInstance(ArrayList<String> names, ArrayList<Integer> marks) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("name", names);
        bundle.putIntegerArrayList("mark", marks);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}