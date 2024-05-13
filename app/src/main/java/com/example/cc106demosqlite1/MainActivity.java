package com.example.cc106demosqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtLocation, txtCourse;
    Button btnSave, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtLocation = findViewById(R.id.txtLocation);
        txtCourse = findViewById(R.id.txtCourse);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);

        Intent intent = getIntent();
        if (intent.hasExtra("selectedStudent")) {
            String selectedStudent = intent.getStringExtra("selectedStudent");
            String[] studentData = selectedStudent.split(", ");

            // Assuming that your studentData array contains the id, name, location, and course in that order
            String id = studentData[0];
            String name = studentData[1];
            String location = studentData[2];
            String course = studentData[3];

            // Set the data to your views here...
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String location = txtLocation.getText().toString();
                String course = txtCourse.getText().toString();

                String selectedStudent = getIntent().getStringExtra("selectedStudent");
                String id = null;
                if (selectedStudent != null) {
                    String[] studentData = selectedStudent.split(", ");
                    id = studentData[0];

                }

                if(name.isEmpty()){
                    toastMessage("Name is required");
                    return;
                }

                if(location.isEmpty()){
                    toastMessage("Location is required");
                    return;
                }

                if(course.isEmpty()){
                    toastMessage("Course is required");
                    return;
                }

                DBHandler db = new DBHandler(MainActivity.this);
                boolean isNewRecord = db.saveStudentInfo(id, name, location, course); // Get the return value here
                String toastMessage = isNewRecord ? "Student info saved" : "Student info updated";
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, studentList.class);
               startActivity(intent);
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}