package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class view_page_for_document extends AppCompatActivity {

    EditText title,fname,lname,mname,username,company,pnumber,lnumber,email,address1,address2,city,state,pincode,country,phone;
    Button savechange;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_for_document);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Document");

        myDatabaseHelper = new MyDatabaseHelper(this);
        savechange = findViewById(R.id.savechange);

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

        Intent i = getIntent();
        String tit = i.getStringExtra("title");
        String fna = i.getStringExtra("fname");
        String mna = i.getStringExtra("mname");
        String lna = i.getStringExtra("lname");
        String use = i.getStringExtra("username");
        String com = i.getStringExtra("company");
        String pnum = i.getStringExtra("pnumber");
        String lnum = i.getStringExtra("lnumber");
        String ema = i.getStringExtra("email");
        String add1 = i.getStringExtra("address1");
        String add2 = i.getStringExtra("address2");
        String cit = i.getStringExtra("city");
        String sta = i.getStringExtra("state");
        String pin = i.getStringExtra("pincode");
        String cou = i.getStringExtra("country");
        String pho = i.getStringExtra("phone");

        title.setText(tit);
        fname.setText(fna);
        mname.setText(mna);
        lname.setText(lna);
        username.setText(use);
        company.setText(com);
        pnumber.setText(pnum);
        lnumber.setText(lnum);
        email.setText(ema);
        address1.setText(add1);
        address2.setText(add2);
        city.setText(cit);
        state.setText(sta);
        pincode.setText(pin);
        country.setText(cou);
        phone.setText(pho);

        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtitle=title.getText().toString();
                String newfirstname=fname.getText().toString();
                String newmiddlename=mname.getText().toString();
                String newlastname=lname.getText().toString();
                String newusername=username.getText().toString();
                String newcompany=company.getText().toString();
                String newpassportnumber=pnumber.getText().toString();
                String newlicencenumber=lnumber.getText().toString();
                String newemail=email.getText().toString();
                String newaddress1=address1.getText().toString();
                String newaddress2=address2.getText().toString();
                String newcity=city.getText().toString();
                String newstate=state.getText().toString();
                String newpincode=pincode.getText().toString();
                String newcountry=country.getText().toString();
                String newphone=phone.getText().toString();

                int a =myDatabaseHelper.editDocument(tit,newtitle,newfirstname,newmiddlename,newlastname,newusername,newcompany,newpassportnumber,newlicencenumber,newemail,newaddress1,newaddress2,newcity,newstate,newpincode,newcountry,newphone);
                startActivity(new Intent(view_page_for_document.this,Documents.class));
                Toast.makeText(view_page_for_document.this, "Updated", Toast.LENGTH_SHORT).show();
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
            fname.setEnabled(true);
            mname.setEnabled(true);
            lname.setEnabled(true);
            username.setEnabled(true);
            company.setEnabled(true);
            pnumber.setEnabled(true);
            lnumber.setEnabled(true);
            email.setEnabled(true);
            address1.setEnabled(true);
            address2.setEnabled(true);
            city.setEnabled(true);
            state.setEnabled(true);
            pincode.setEnabled(true);
            country.setEnabled(true);
            phone.setEnabled(true);
            savechange.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}