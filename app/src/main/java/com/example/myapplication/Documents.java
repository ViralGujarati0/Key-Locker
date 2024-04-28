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

import com.example.myapplication.model.documenttt;

import java.util.ArrayList;

public class Documents extends AppCompatActivity {

    Button button22,button11;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<documenttt> arrayList;
    String title;
    CustomDocumentAdapter customDocumentAdapter;
    ListView lv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Vault");
        button11 =  findViewById(R.id.Button11);
        lv = findViewById(R.id.lv_document);
        registerForContextMenu(lv);
        myDatabaseHelper = new MyDatabaseHelper(this);

       loadDocumentInListView();



        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Documents.this,MainActivity.class));
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.txt_title_doc);
                title = t.getText().toString();
                Toast.makeText(Documents.this, title, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> list = new ArrayList<>();
                documenttt dc = new documenttt();
                TextView t = view.findViewById(R.id.txt_title_doc);
                title = t.getText().toString();
               // Toast.makeText(Documents.this, title, Toast.LENGTH_SHORT).show();
                list = myDatabaseHelper.viewDocument(title);
                Intent i = new Intent(Documents.this,view_page_for_document.class);
                i.putExtra("uid", (CharSequence) list.get(0));
                i.putExtra("type",(CharSequence) list.get(1));
                i.putExtra("title",(CharSequence) list.get(2));
                i.putExtra("fname", (CharSequence) list.get(3));
                i.putExtra("mname",(CharSequence) list.get(4));
                i.putExtra("lname",(CharSequence) list.get(5));
                i.putExtra("username", (CharSequence) list.get(6));
                i.putExtra("company",(CharSequence) list.get(7));
                i.putExtra("pnumber",(CharSequence) list.get(8));
                i.putExtra("lnumber", (CharSequence) list.get(0));
                i.putExtra("email",(CharSequence) list.get(10));
                i.putExtra("address1",(CharSequence) list.get(11));
                i.putExtra("address2", (CharSequence) list.get(12));
                i.putExtra("city",(CharSequence) list.get(13));
                i.putExtra("state",(CharSequence) list.get(14));
                i.putExtra("pincode", (CharSequence) list.get(15));
                i.putExtra("country",(CharSequence) list.get(16));
                i.putExtra("phone",(CharSequence) list.get(17));
                startActivity(i);
            }
        });
    }
    private void loadDocumentInListView() {
        arrayList = myDatabaseHelper.getAllDocument();
        customDocumentAdapter = new CustomDocumentAdapter(this,arrayList);
        lv.setAdapter(customDocumentAdapter);
        customDocumentAdapter.notifyDataSetChanged();
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
            startActivity(new Intent(Documents.this,AddDocuments.class));
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
                myDatabaseHelper.deleteDocumentData(title);
                loadDocumentInListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}