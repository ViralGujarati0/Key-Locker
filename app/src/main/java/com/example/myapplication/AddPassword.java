package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AddPassword extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1;
    EditText title,username,password1,url;
    Spinner spinner;
    byte[] b10;
    String uid;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpassword);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();
        getSupportActionBar().setTitle("Password");
        title = findViewById(R.id.title);
        username = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        url = findViewById(R.id.url);

        myDB = new MyDatabaseHelper(AddPassword.this);
        String []arraySpinner = new String[] {"Login","Notes","Cards","Documents"};

        spinner = (Spinner) findViewById(R.id.spiner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1)
                {
                    startActivity(new Intent(AddPassword.this,AddNotes.class));
                }
                if(position == 2)
                {
                    startActivity(new Intent(AddPassword.this,AddCards.class));
                }
                if(position == 3)
                {
                    startActivity(new Intent(AddPassword.this,AddDocuments.class));
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
            startActivity(new Intent(AddPassword.this,Password.class));
        }
        if(item.getItemId() == R.id.save)
        {
            /*try {
                b10 = myDB.encryption(password1.getText().toString().trim());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            String encValue = "";
            for (byte bb : b10) {
                Log.i("encryption", String.valueOf(bb));
                encValue += String.valueOf(bb);
            }
            Toast.makeText(this, encValue, Toast.LENGTH_SHORT).show();*/
            myDB.addPasswordData(uid,
                    spinner.getSelectedItem().toString().trim(),
                    title.getText().toString().trim(),
                    username.getText().toString().trim(),
                    password1.getText().toString().trim(),
                    url.getText().toString().trim());
            startActivity(new Intent(AddPassword.this,Password.class));
            Toast.makeText(getApplicationContext(),"Success2",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    /* Intent i = new Intent(AddPassword.this,Password.class);
            startActivity(i);
            String s1;
            try {
                 s1 = myDB.decryption(b10);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(this, s1, Toast.LENGTH_SHORT).show();*/
}