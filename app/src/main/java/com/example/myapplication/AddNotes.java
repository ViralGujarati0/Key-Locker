package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddNotes extends AppCompatActivity {

    String uid;
    EditText title;
    EditText content;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Spinner a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        getSupportActionBar().setTitle("Notes");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();
        String []arraySpinner = new String[] {"Notes","Login","Cards","Documents"};

        a = (Spinner) findViewById(R.id.spiner2);
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        a.setAdapter(adapter);

        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1)
                {
                    startActivity(new Intent(AddNotes.this,AddPassword.class));
                }
                if(position == 2)
                {
                    startActivity(new Intent(AddNotes.this,AddCards.class));
                }
                if(position == 3)
                {
                    startActivity(new Intent(AddNotes.this,AddDocuments.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuforaddpassword, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cancel)
        {
            startActivity(new Intent(AddNotes.this,Notes.class));
        }
        if(item.getItemId() == R.id.save)
        {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddNotes.this);
            myDB.addnoteData(uid,
                    a.getSelectedItem().toString().trim(),
                    title.getText().toString().trim(),
                    content.getText().toString().trim());
            startActivity(new Intent(AddNotes.this,Notes.class));
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}