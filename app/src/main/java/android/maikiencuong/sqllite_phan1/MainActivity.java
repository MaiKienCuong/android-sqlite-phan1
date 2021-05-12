package android.maikiencuong.sqllite_phan1;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler dbHandler;
    private Button btnAdd, btnRemove, btnCancle;
    private TextView tvAdd;
    private ListView listView;
    private StudentAdapter adapter;
    private List<Student> students;
    private int index = -1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dbHandler = new DatabaseHandler(MainActivity.this);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancle = findViewById(R.id.btnCancle);
        tvAdd = findViewById(R.id.tvAdd);
        listView = findViewById(R.id.listView);

        students = new ArrayList<>();
//        students.add(new Student(1, "Nguyen Van Anh"));
//        students.add(new Student(2, "Nguyen Van Anh"));
//        students.add(new Student(3, "Nguyen Van Anh"));
//        students.add(new Student(4, "Nguyen Van Anh"));
//        students.add(new Student(5, "Nguyen Van Anh"));
//        students.add(new Student(6, "Nguyen Van Anh"));
//        students.add(new Student(7, "Nguyen Van Anh"));
//        students.add(new Student(8, "Nguyen Van Anh"));
//        students.add(new Student(9, "Nguyen Van Anh"));
//        students.add(new Student(10, "Nguyen Van Anh"));
//
//        for (Student s : students) {
//            dbHandler.addStudent(s);
//        }
        adapter = new StudentAdapter(MainActivity.this, dbHandler);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(Color.WHITE);
                }
                view.setBackgroundColor(Color.argb(100, 255, 165, 0));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(tvAdd.getText().toString().trim());
                dbHandler.addStudent(student);
                adapter.notifyDataSetChanged();
                tvAdd.setText("");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    dbHandler.deleteStudent((int) adapter.getItemId(index));
                    adapter.notifyDataSetChanged();
                    index = -1;
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAdd.setText("");
                adapter.notifyDataSetChanged();
            }
        });


    }
}