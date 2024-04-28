package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class AddDocuments extends AppCompatActivity {

    Spinner a;
    String uid;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    EditText title,fname,lname,mname,username,company,pnumber,lnumber,email,address1,address2,city,state,pincode,country,phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddocuments);
        getSupportActionBar().setTitle("Documents");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();

        String []arraySpinner = new String[] {"Documnets","Notes","Login","Cards"};

        a = (Spinner) findViewById(R.id.spiner2);
        title = findViewById(R.id.title);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        mname = findViewById(R.id.mname);
        username = findViewById(R.id.username);
        company = findViewById(R.id.company);
        pnumber = findViewById(R.id.pnumber);
        lnumber = findViewById(R.id.lnumber);
        email = findViewById(R.id.email);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);
        country = findViewById(R.id.country);
        phone = findViewById(R.id.phone);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        a.setAdapter(adapter);

        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1)
                {
                    startActivity(new Intent(AddDocuments.this,AddNotes.class));
                }
                if(position == 2)
                {
                    startActivity(new Intent(AddDocuments.this,AddPassword.class));
                }
                if(position == 3)
                {
                    startActivity(new Intent(AddDocuments.this,AddCards.class));
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
            startActivity(new Intent(AddDocuments.this,Documents.class));
        }
        if(item.getItemId() == R.id.save)
        {

            MyDatabaseHelper myDB = new MyDatabaseHelper(AddDocuments.this);
            myDB.addDocumentData(uid,
                    a.getSelectedItem().toString().trim(),
                    title.getText().toString().trim(),
                    fname.getText().toString().trim(),
                    mname.getText().toString().trim(),
                    lname.getText().toString().trim(),
                    username.getText().toString().trim(),
                    company.getText().toString().trim(),
                    pnumber.getText().toString().trim(),
                    lnumber.getText().toString().trim(),
                    email.getText().toString().trim(),
                    address1.getText().toString().trim(),
                    address2.getText().toString().trim(),
                    city.getText().toString().trim(),
                    state.getText().toString().trim(),
                    pincode.getText().toString().trim(),
                    country.getText().toString().trim(),
                    phone.getText().toString().trim());
            startActivity(new Intent(AddDocuments.this,Documents.class));

        }
        return super.onOptionsItemSelected(item);
    }
}