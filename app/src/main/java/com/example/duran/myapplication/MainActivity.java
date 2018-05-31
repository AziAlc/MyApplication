package com.example.duran.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   DatabaseHelper mDatabaseHelper;


    private Button add, view;
    private EditText editText;

    private EditText editText2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editText = findViewById(R.id.field);
       editText2 = findViewById(R.id.editText2);
       add = findViewById(R.id.add);
       view= findViewById(R.id.view);

       mDatabaseHelper = new DatabaseHelper(this);


       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String newEntry = editText.getText().toString();
               String newEntry2 = editText2.getText().toString();
               if (editText.length() != 0) {
                    addData(newEntry, newEntry2);
                    editText.setText("");
               } else {
                   toastMessage("CANT be empty");
               }
           }
       });

       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, ListData.class);
               startActivity(intent);
           }
       });



    }


    public void addData(String newEntry, String newEntry2) {
        boolean insert = mDatabaseHelper.insertData(newEntry, newEntry2);

        if(insert) {
            toastMessage("Success insert");
        } else {
            toastMessage("wrong");
        }
    }




    private void toastMessage(String mess) {
           Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
        }





}
