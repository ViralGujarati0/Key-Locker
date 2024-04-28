package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class view_page_for_note extends AppCompatActivity {

    EditText title,content;
    Button savechange;
    MyDatabaseHelper myDatabaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_for_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Password");
        myDatabaseHelper = new MyDatabaseHelper(this);
        savechange = findViewById(R.id.savechange);

        title = findViewById(R.id.title_note);
        content = findViewById(R.id.content_note);

        Intent i = getIntent();
        String t = i.getStringExtra("title");
        String c = i.getStringExtra("content");

        title.setText(t);
        content.setText(c);

        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtitle=title.getText().toString();
                String newcontent=content.getText().toString();

                int a =myDatabaseHelper.editNote(t,newtitle,newcontent);
                startActivity(new Intent(view_page_for_note.this,Notes.class));
                Toast.makeText(view_page_for_note.this, "Updated", Toast.LENGTH_SHORT).show();
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
            title.setEnabled(true);
            content.setEnabled(true);
            savechange.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}