package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.passworddd;

import java.util.ArrayList;

public class Password extends AppCompatActivity {
    Button button22,button11;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<passworddd> arrayList;
    String title;
    CustomPasswordAdapter customPasswordAdapter;
    ListView lv;
    int pos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Vault");

        button11 =  findViewById(R.id.Button11);
        myDatabaseHelper = new MyDatabaseHelper(this);
        arrayList = new ArrayList<>();
        lv = findViewById(R.id.lv);
        registerForContextMenu(lv);

        loadDataInListView();


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Password.this,DashBoard.class));
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.txt_title);
                title = t.getText().toString();
                Toast.makeText(Password.this, title, Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> list = new ArrayList<>();
                passworddd pd = new passworddd();
                TextView t = view.findViewById(R.id.txt_title);
                title = t.getText().toString();
                //Toast.makeText(Password.this, title, Toast.LENGTH_SHORT).show();
                list = myDatabaseHelper.viewPassword(title);
                Intent i = new Intent(Password.this,view_page_for_passwordd.class);
                i.putExtra("uid", (CharSequence) list.get(0));
                i.putExtra("type",(CharSequence) list.get(1));
                i.putExtra("title",(CharSequence) list.get(2));
                i.putExtra("username",(CharSequence) list.get(3));
                i.putExtra("password",(CharSequence) list.get(4));
                i.putExtra("url",(CharSequence) list.get(5));
                startActivity(i);

            }
        });
    }

    private void loadDataInListView() {
        arrayList = myDatabaseHelper.getAllData();
        customPasswordAdapter = new CustomPasswordAdapter(this,arrayList);
        lv.setAdapter(customPasswordAdapter);
        customPasswordAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuforpassword, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.buttonadd2)
        {
            startActivity(new Intent(Password.this,AddPassword.class));
        }
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.deletemenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                myDatabaseHelper.deletePasswordData(title);
                loadDataInListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}