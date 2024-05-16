package com.example.cc106demosqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtLocation, txtCourse;
    Button btnSave, btnView, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtLocation = findViewById(R.id.txtLocation);
        txtCourse = findViewById(R.id.txtCourse);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);


        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("Student");

        if (student != null) {
            // populate your input fields with the student's information
            txtName.setText(student.getName());
            txtLocation.setText(student.getLocation());
            txtCourse.setText(student.getCourse());

            // change the text of the button
            btnSave.setText("Update");

            // show the delete button
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            // change the text of the button
            btnSave.setText("Save");

            // hide the delete button
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            String location = txtLocation.getText().toString();
            String course = txtCourse.getText().toString();

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


            DBHandler dbHandler = new DBHandler(this);
            if (student != null) {
                // update existing student
                student.setName(name);
                student.setLocation(location);
                student.setCourse(course);
                dbHandler.updateStudent(student);
                toastMessage("Student updated successfully");
            } else {
                // add new student
                Student newStudent = new Student(name, location, course);
                dbHandler.addStudent(newStudent);
                toastMessage("Student added successfully");
            }
        });

        btnView.setOnClickListener(v -> {
            startActivity(StudentList.class);
        });

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Confirmation")
                    .setMessage("Are you sure you want to delete this student?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DBHandler dbHandler = new DBHandler(MainActivity.this);
                            dbHandler.deleteStudent(student);
                            toastMessage("Student deleted successfully");
                            // Redirect to the list of students
                            startActivity(new Intent(MainActivity.this, StudentList.class));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}