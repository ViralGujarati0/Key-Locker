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

import com.example.myapplication.model.cardddd;
import com.example.myapplication.model.passworddd;

import java.util.ArrayList;

public class Cards extends AppCompatActivity {

    Button button22,button11;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<cardddd> arrayList;
    String title;
    ListView lv;
    CustomCardAdapter customCardAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Vault");
        button11 =  findViewById(R.id.Button11);
        lv = findViewById(R.id.list);
        registerForContextMenu(lv);
        myDatabaseHelper = new MyDatabaseHelper(this);



        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cards.this,MainActivity.class));
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.txt_title_card);
                title = t.getText().toString();
                Toast.makeText(Cards.this, title, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> list = new ArrayList<>();
                cardddd pd = new cardddd();
                TextView t = view.findViewById(R.id.txt_title_card);
                title = t.getText().toString();
                //Toast.makeText(Cards.this, title, Toast.LENGTH_SHORT).show();
                list = myDatabaseHelper.viewCard(title);
                Intent i = new Intent(Cards.this,view_page_for_card.class);
                i.putExtra("uid", (CharSequence) list.get(0));
                i.putExtra("type",(CharSequence) list.get(1));
                i.putExtra("title",(CharSequence) list.get(2));
                i.putExtra("cardholdname",(CharSequence) list.get(3));
                i.putExtra("number",(CharSequence) list.get(4));
                i.putExtra("branch",(CharSequence) list.get(5));
                i.putExtra("month",(CharSequence) list.get(6));
                i.putExtra("year",(CharSequence) list.get(7));
                i.putExtra("cvv",(CharSequence) list.get(8));
                startActivity(i);

            }
        });

        loadCardDataInListView();
    }
    private void loadCardDataInListView() {
        arrayList = myDatabaseHelper.getAllCard();
        customCardAdapter = new CustomCardAdapter(this,arrayList);
        lv.setAdapter(customCardAdapter);
        customCardAdapter.notifyDataSetChanged();
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
            startActivity(new Intent(Cards.this,AddCards.class));
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
                myDatabaseHelper.deleteCardData(title);
                loadCardDataInListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}