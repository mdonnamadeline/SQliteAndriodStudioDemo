package com.example.cc106demosqlite1;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {
    private ListView liststudents;
    private Button btnBack;
    private List<Student> students;
    private DBHandler dbHandler;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);

        liststudents = findViewById(R.id.liststudents);
        btnBack = findViewById(R.id.btnBack);

        dbHandler = new DBHandler(this);
        students = dbHandler.getAllStudents();

        List<String> studentStrings = new ArrayList<>();
        for (Student student : students) {
            studentStrings.add(student.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentStrings);
        liststudents.setAdapter(adapter);

        liststudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = students.get(position);

                Intent intent = new Intent(StudentList.this, MainActivity.class);
                intent.putExtra("Student", selectedStudent);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}