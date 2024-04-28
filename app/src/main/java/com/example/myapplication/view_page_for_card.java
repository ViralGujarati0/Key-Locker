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

public class view_page_for_card extends AppCompatActivity {


    EditText title,cardholdname,number,branch,month,year,cvv;
    Button savechange;
    MyDatabaseHelper myDatabaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_for_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Card");

        myDatabaseHelper = new MyDatabaseHelper(this);
        savechange = findViewById(R.id.savechange);

        title = findViewById(R.id.title);
        cardholdname = findViewById(R.id.cardholdname);
        number = findViewById(R.id.number);
        branch = findViewById(R.id.branchh);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        cvv = findViewById(R.id.code);

        Intent i = getIntent();
        String ti = i.getStringExtra("title");
        String chn = i.getStringExtra("cardholdname");
        String nu = i.getStringExtra("number");
        String brr = i.getStringExtra("branch");
        String mon = i.getStringExtra("month");
        String ye = i.getStringExtra("year");
        String cv = i.getStringExtra("cvv");

        title.setText(ti);
        cardholdname.setText(chn);
        number.setText(nu);
        branch.setText(brr);
        month.setText(mon);
        year.setText(ye);
        cvv.setText(cv);

        savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtitle=title.getText().toString();
                String newcardholdname=cardholdname.getText().toString();
                String newnumber=number.getText().toString();
                String newbranch=branch.getText().toString();
                String newmonth=month.getText().toString();
                String newyear=year.getText().toString();
                String newcvv=cvv.getText().toString();

                int a =myDatabaseHelper.editCard(ti,newtitle,newcardholdname,newnumber,newbranch,newmonth,newyear,newcvv);
                startActivity(new Intent(view_page_for_card.this,Cards.class));
                Toast.makeText(view_page_for_card.this, "Updated", Toast.LENGTH_SHORT).show();
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
            cardholdname.setEnabled(true);
            number.setEnabled(true);
            branch.setEnabled(true);
            month.setEnabled(true);
            year.setEnabled(true);
            cvv.setEnabled(true);
            savechange.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
}