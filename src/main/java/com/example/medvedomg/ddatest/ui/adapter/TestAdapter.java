package com.example.medvedomg.ddatest.ui.adapter;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.model.Course;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.activity.MainActivityImpl;
import com.example.medvedomg.ddatest.ui.fragment.MyDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medvedomg on 06.01.17.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>{

    public static final String TAG = TestAdapter.class.getSimpleName();

    MainActivityImpl mainActivity;

    List<Student> studentList = new ArrayList<>();
    private ArrayList<Course> courses;
    private ArrayList<Integer> marks = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    public TestAdapter(MainActivityImpl mainActivity) {
        this.mainActivity = mainActivity;
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHuman;
        private ImageView imgInfo;
        private TextView tvName;
        private TextView tvBirthday;
        public TestViewHolder(View itemView) {
            super(itemView);
            imgHuman = (ImageView) itemView.findViewById(R.id.image_rv_human);
            imgInfo = (ImageView) itemView.findViewById(R.id.img_rv_extra_information);
            tvName = (TextView) itemView.findViewById(R.id.tv_rv_name);
            tvBirthday = (TextView) itemView.findViewById(R.id.tv_rv_birthday);
        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rv, parent,false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TestViewHolder holder, int position) {
        holder.tvName.setText(studentList.get(position).getFirstName() + " " + studentList.get(position).getLastName());
        holder.tvBirthday.setText(Integer.toOctalString(studentList.get(position).getBirthday()));
        Log.d(TAG, "position " + position);
        courses = studentList.get(position).getCourses();

        holder.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "imgInfo clicked");
                names.clear();
                marks.clear();
                showAlertDialog(studentList.get(holder.getAdapterPosition()).getCourses());
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudents(List<Student> list) {
        Log.d(TAG, "students size " + list.size());
        studentList.clear();
        studentList.addAll(list);
        notifyDataSetChanged();
    }

    private void showAlertDialog(ArrayList<Course> courses) {


        for (Course c:courses) {
            names.add(c.getName());
            Log.d(TAG, "showAlertDialog" + c.getName());
            marks.add(c.getMark());
            Log.d(TAG, "showAlertDialog" + c.getMark()+"");
        }

        Log.d(TAG, names.size() + " marks.size() " + marks.size());
        MyDialogFragment fragment = MyDialogFragment.newInstance(names,marks);
        fragment.show(mainActivity.getSupportFragmentManager(), "some tag");
    }
}
