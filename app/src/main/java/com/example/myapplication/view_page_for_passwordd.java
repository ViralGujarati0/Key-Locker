package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.passworddd;

public class view_page_for_passwordd extends AppCompatActivity {

    EditText user,pass,tit,ur;
    Button savechange;
    MyDatabaseHelper myDatabaseHelper;
    String title,username,password,url;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_for_passwordd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Password");

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        tit = findViewById(R.id.title);
        ur = findViewById(R.id.url);
        savechange = findViewById(R.id.savechange);


        Intent i = getIntent();
        title = i.getStringExtra("title");
        username = i.getStringExtra("username");
        password = i.getStringExtra("password");
        url = i.getStringExtra("url");

        tit.setText(title);
        user.setText(username);
        pass.setText(password);
        ur.setText(url);



        myDatabaseHelper = new MyDatabaseHelper(this);
        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newtitle=tit.getText().toString();
                String newusername=user.getText().toString();
                String newpassword=pass.getText().toString();
                String newurl=ur.getText().toString();

                int a =myDatabaseHelper.editPassword(title,newtitle,newusername,newpassword,newurl);
                startActivity(new Intent(view_page_for_passwordd.this,Password.class));
                Toast.makeText(view_page_for_passwordd.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        if(item.getItemId() == R.id.edit)
        {
            user.setEnabled(true);
            pass.setEnabled(true);
            tit.setEnabled(true);
            ur.setEnabled(true);
            savechange.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}