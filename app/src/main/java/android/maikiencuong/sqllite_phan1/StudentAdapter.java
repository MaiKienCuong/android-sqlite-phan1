package android.maikiencuong.sqllite_phan1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private List<Student> students;
    private Context context;
    private DatabaseHandler dbHandler;

    public StudentAdapter(Context context, DatabaseHandler dbHandler) {
        this.context = context;
        this.dbHandler = dbHandler;
        students = new ArrayList<>(dbHandler.getAllStudents());
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = students.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(student.getName());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        students = dbHandler.getAllStudents();
        super.notifyDataSetChanged();
    }
}
