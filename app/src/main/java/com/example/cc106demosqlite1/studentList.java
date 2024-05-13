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

public class studentList extends AppCompatActivity {
    ListView liststudents;
    Button btnBack;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);

        DBHandler db = new DBHandler(this);
        liststudents = findViewById(R.id.liststudents);
        btnBack = findViewById(R.id.btnBack);
        ArrayList<String> studentList = db.readStudent();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        liststudents.setAdapter(adapter);

        liststudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStudent = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(studentList.this, MainActivity.class);
                intent.putExtra("selectedStudent", selectedStudent);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}