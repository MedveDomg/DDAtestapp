package com.example.medvedomg.ddatest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.model.Course;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by medvedomg on 07.01.17.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder>{



    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> marks = new ArrayList<>();
    Context context;

    public DialogAdapter(ArrayList<String> names, ArrayList<Integer> marks, Context context) {
        this.names = names;
        this.marks = marks;
        this.context = context;
    }

    public static class DialogViewHolder extends RecyclerView.ViewHolder{

        TextView tvCourse;
        Button btnMark;
        public DialogViewHolder(View itemView) {
            super(itemView);
            tvCourse = (TextView) itemView.findViewById(R.id.tv_rv_dialog_course);
            btnMark = (Button) itemView.findViewById(R.id.btn_rv_dialog_mark);
        }
    }

    @Override
    public DialogAdapter.DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_rv_dialog, parent, false);
        return new DialogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DialogAdapter.DialogViewHolder holder, int position) {
        //names.get(position)+ marks.get(position).intValue()+
        holder.tvCourse.setText(names.get(position).toString());
        holder.btnMark.setText(marks.get(position).intValue()+"");
    }

    @Override
    public int getItemCount() {
        return names.size();
    }


}
