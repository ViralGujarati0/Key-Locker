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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddCards extends AppCompatActivity {

    EditText title,cardholdname,number,cvv;
    String uid;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Spinner a,b,c,d;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);
        getSupportActionBar().setTitle("Cards");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();
        title = findViewById(R.id.title);
        cardholdname = findViewById(R.id.cardholdname);
        number = findViewById(R.id.number);
        cvv = findViewById(R.id.code);

        String []arraySpinner = new String[] {"Cards","Notes","Login","Document"};

         a = (Spinner) findViewById(R.id.spiner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        a.setAdapter(adapter);

        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1)
                {
                    startActivity(new Intent(AddCards.this,AddNotes.class));
                }
                if(position == 2)
                {
                    startActivity(new Intent(AddCards.this,AddPassword.class));
                }
                if(position == 3)
                {
                    startActivity(new Intent(AddCards.this,AddDocuments.class));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        String []arraySpinner1 = new String[] {"Visa","Mastercard","American Express","Discover","Other"};

         b = (Spinner) findViewById(R.id.spiner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner1);
        b.setAdapter(adapter1);

        String []arraySpinner2 = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};

         c = (Spinner) findViewById(R.id.spiner3);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner2);
        c.setAdapter(adapter2);

        String []arraySpinner3 = new String[] {"2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036"};

         d = (Spinner) findViewById(R.id.spiner4);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner3);
        d.setAdapter(adapter3);

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
            startActivity(new Intent(AddCards.this,Cards.class));
        }
        if(item.getItemId() == R.id.save)
        {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddCards.this);
            myDB.addCardData(uid,
                    a.getSelectedItem().toString().trim(),
                    title.getText().toString().trim(),
                    cardholdname.getText().toString().trim(),
                    number.getText().toString().trim(),
                    b.getSelectedItem().toString().trim(),
                    c.getSelectedItem().toString().trim(),
                    d.getSelectedItem().toString().trim(),
                    cvv.getText().toString().trim());
            startActivity(new Intent(AddCards.this,Cards.class));
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}