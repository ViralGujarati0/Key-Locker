package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar4;
    Dialog d1;
    Button pass,documents,cards,notes,button2,button1,cancel,okay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        firebaseAuth = FirebaseAuth.getInstance();
        //setUpToolbar();
        /*navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(DashBoard.this,DashBoard.class));
                        return true;
                    case R.id.logout:
                        d1.setCancelable(false);
                        d1.show();
                        return  true;
                }
                return false;
            }
        });

        d1 = new Dialog(DashBoard.this);
        d1.setContentView(R.layout.custom_dialogbox);
        d1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel = d1.findViewById(R.id.cancel);
        okay = d1.findViewById(R.id.okay);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(DashBoard.this,MainActivity.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d1.cancel();
                startActivity(new Intent(DashBoard.this,DashBoard.class));
            }
        });*/

        pass = findViewById(R.id.pass);
        documents = findViewById(R.id.Documents);
        cards = findViewById(R.id.Cards);
        notes = findViewById(R.id.Notes);
        button1 =  findViewById(R.id.Button1);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, Password.class));
            }
        });
        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, Documents.class));
            }
        });

        cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, Cards.class));
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, Notes.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufordashboard, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.buttonadd)
        {
            startActivity(new Intent(DashBoard.this,AddNotes.class));
        }
        if(item.getItemId() == R.id.logout)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(DashBoard.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar4 = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setTitle("My Vault");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar4, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}