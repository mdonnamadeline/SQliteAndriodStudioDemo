package com.example.cc106demosqlite1;

import androidx.appcompat.app.AppCompatActivity;

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

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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

               DBHandler db = new DBHandler(MainActivity.this);
               db.saveStudentInfo(name, location, course);
                  Toast.makeText(getApplicationContext(), "Student info saved", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}