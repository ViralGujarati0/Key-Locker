package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myapplication.model.noteee;
import com.example.myapplication.model.passworddd;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    Button button22,button11;

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<noteee> arrayList;
    String title;
    CustomNoteAdapter customNoteAdapter;

    ListView lv_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Vault");
        button11 =  findViewById(R.id.Button11);

        myDatabaseHelper = new MyDatabaseHelper(this);
        arrayList = new ArrayList<>();
        lv_note = findViewById(R.id.lv_note);
        registerForContextMenu(lv_note);

        loadNoteDataInListView();

        lv_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.txt_title_note);
                title = t.getText().toString();
                Toast.makeText(Notes.this, title, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        lv_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> list = new ArrayList<>();
                noteee pd = new noteee();
                TextView t = view.findViewById(R.id.txt_title_note);
                title = t.getText().toString();
               // Toast.makeText(Notes.this, title, Toast.LENGTH_SHORT).show();
                list = myDatabaseHelper.viewNote(title);
                Intent i = new Intent(Notes.this,view_page_for_note.class);
                i.putExtra("uid", (CharSequence) list.get(0));
                i.putExtra("type",(CharSequence) list.get(1));
                i.putExtra("title",(CharSequence) list.get(2));
                i.putExtra("content",(CharSequence) list.get(3));
                startActivity(i);

            }
        });





        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notes.this,MainActivity.class));
            }
        });
    }

    private void loadNoteDataInListView() {
        arrayList = myDatabaseHelper.getAllnote();
        customNoteAdapter = new CustomNoteAdapter(this,arrayList);
        lv_note.setAdapter(customNoteAdapter);
        customNoteAdapter.notifyDataSetChanged();
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
            startActivity(new Intent(Notes.this,AddNotes.class));
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
                myDatabaseHelper.deleteNoteData(title);
                loadNoteDataInListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}