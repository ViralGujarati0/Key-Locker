package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.model.cardddd;
import com.example.myapplication.model.documenttt;
import com.example.myapplication.model.noteee;
import com.example.myapplication.model.passworddd;
import com.google.common.primitives.Bytes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


import javax.crypto.Cipher;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Project6.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "password";
    private static final String COLUMN_UID = "uid";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_URL = "url";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_UID + " TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_URL + " TEXT);";
        db.execSQL(query);

        String query1= "CREATE TABLE note(uid TEXT,type TEXT,title TEXT PRIMARY KEY, content Text);";
        db.execSQL(query1);

        String query2= "CREATE TABLE document(uid TEXT,type TEXT,title TEXT PRIMARY KEY, firstname Text,middlename TEXT,lastname TEXT,username TEXT , company Text, passportnumber Text,licensenumber TEXT,email TEXT,address1 TEXT , address2 Text,city TEXT,state TEXT,pincode TEXT , country Text, phone Text);";
        db.execSQL(query2);

        String query3= "CREATE TABLE card(uid TEXT,type TEXT,title TEXT PRIMARY KEY,cardholdername Text,number TEXT,branch TEXT,expirationmonth TEXT , expirationyear Text,cvv TEXT);";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS note");
        db.execSQL("DROP TABLE IF EXISTS document");
        db.execSQL("DROP TABLE IF EXISTS card");
        onCreate(db);
    }

    // inserting for password
    void addPasswordData(String uid , String type , String title , String username , String password , String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_UID, uid);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_URL, url);
        long res = db.insert(TABLE_NAME,null, cv);
        if (res == -1)
        {
            Toast.makeText(context, "Failed To Add In Database", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success Add In Database", Toast.LENGTH_SHORT).show();
        }
    }
    String titlee;
    // show data in password view
    public ArrayList<String> viewPassword(String titlee)
    {
        this.titlee= titlee;
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE title = ?" ,new String[]{titlee});
        String uid=null,type=null,url=null,password=null,username=null,title=null;
        while(cursor.moveToNext()) {
            uid = cursor.getString(0);
            type = cursor.getString(1);
            title = cursor.getString(2);
            username = cursor.getString(3);
            password = cursor.getString(4);
            url = cursor.getString(5);
        }

        arrayList.add(uid);
        arrayList.add(type);
        arrayList.add(title);
        arrayList.add(username);
        arrayList.add(password);
        arrayList.add(url);

        Log.i("password",uid);
        Log.i("password",type);
        Log.i("password",title);
        Log.i("password",username);
        Log.i("password",password);
        Log.i("password",url);

        return arrayList;
    }


    public ArrayList<passworddd> getAllData() {
        ArrayList<passworddd> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM " + TABLE_NAME,null);

        String passwordd;
        while(cursor.moveToNext())
        {
            String uid = cursor.getString(0);
            String type = cursor.getString(1);
            String title = cursor.getString(2);
            String username = cursor.getString(3);
            String password = cursor.getString(4);
            //byte[] pass = password.getBytes();
            Log.i("sdafdf", String.valueOf(password));
           /* try {
                passwordd = decryption(pass);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }*/
            String url = cursor.getString(5);
            passworddd pd = new passworddd(uid, type, title, username, password, url);
            arrayList.add(pd);
        }
        return arrayList;
    }

    String title;
    //deteting data from password table
    public void deletePasswordData(String title)
    {
        SQLiteDatabase db = getWritableDatabase();
        this.title = title;
        db.delete(TABLE_NAME,"title = ?",new String[]{String.valueOf(title)});
    }

    public int editPassword(String oldtitle,String newtitle,String newusername,String newpassword,String newurl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_TITLE,newtitle);
        value.put(COLUMN_USERNAME,newusername);
        value.put(COLUMN_PASSWORD,newpassword);
        value.put(COLUMN_URL,newurl);

        return db.update(TABLE_NAME,value,"title= ?",new String[]{String.valueOf(oldtitle)});
    }


    public ArrayList<noteee> getAllnote(){
        ArrayList<noteee> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM note ",null);

        while(cursor.moveToNext())
        {
            String uid = cursor.getString(0);
            String type = cursor.getString(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);

            noteee ne = new noteee(uid,type,title,content);

            arrayList.add(ne);


            Log.i("password",uid);
            Log.i("password",type);
            Log.i("password",title);
            Log.i("password",content);


        }
        return arrayList;
    }

    void addnoteData(String uid , String type , String title , String content ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uid", uid);
        cv.put("type", type);
        cv.put("title", title);
        cv.put("content", content);
        long res = db.insert("note",null, cv);
        if (res == -1)
        {
            Toast.makeText(context, "Failed To Add In Database", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success Add In Database", Toast.LENGTH_SHORT).show();
        }
    }
    String t;
    public void deleteNoteData(String title)
    {
        SQLiteDatabase db = getWritableDatabase();
        t = title;
        db.delete("note","title = ?",new String[]{String.valueOf(t)});
    }

    String title_note;
    public ArrayList<String> viewNote(String titlee)
    {
        title_note = titlee;
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM note " + " WHERE title = ?" ,new String[]{title_note});
        String title=null,content=null,uid=null,type=null;
        while(cursor.moveToNext()) {
            uid = cursor.getString(0);
            type = cursor.getString(1);
            title = cursor.getString(2);
            content = cursor.getString(3);
        }
        arrayList.add(uid);
        arrayList.add(type);
        arrayList.add(title);
        arrayList.add(content);
        return arrayList;
    }

    public int editNote(String oldtitle,String newtitle,String newcontent)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("title",newtitle);
        value.put("content",newcontent);
        return db.update("note",value,"title= ?",new String[]{String.valueOf(oldtitle)});
    }

    void addDocumentData(String uid,String type,String title,String firstname ,String middlename ,String lastname ,String username  ,String  company ,String  passportnumber ,String licensenumber ,String email ,String address1  ,String  address2 ,String city ,String state ,String pincode  ,String  country ,String  phone ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_UID, uid);
        cv.put("type", type);
        cv.put("title", title);
        cv.put("firstname", firstname);
        cv.put("middlename", middlename);
        cv.put("lastname", lastname);
        cv.put("username", username);
        cv.put("company", company);
        cv.put("passportnumber", passportnumber);
        cv.put("licensenumber", licensenumber);
        cv.put("email", email);
        cv.put("address1", address1);
        cv.put("address2", address2);
        cv.put("city", city);
        cv.put("state", state);
        cv.put("pincode", pincode);
        cv.put("country", country);
        cv.put("phone", phone);
        long res = db.insert("document",null, cv);
        if (res == -1)
        {
            Toast.makeText(context, "Failed To Add In Database", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success Add In Database", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<documenttt> getAllDocument(){
        ArrayList<documenttt> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM document ",null);
        while(cursor.moveToNext())
        {
            String uid = cursor.getString(0);
            String type = cursor.getString(1);
            String title = cursor.getString(2);
            String fname = cursor.getString(3);
            String mname = cursor.getString(4);
            String lname = cursor.getString(5);
            String username = cursor.getString(6);
            String company = cursor.getString(7);
            String pnumber = cursor.getString(8);
            String lnumber = cursor.getString(9);
            String email = cursor.getString(10);
            String address1 = cursor.getString(11);
            String address2 = cursor.getString(12);
            String city = cursor.getString(13);
            String state = cursor.getString(14);
            String pincode = cursor.getString(15);
            String country = cursor.getString(16);
            String phone = cursor.getString(17);
            documenttt dc = new documenttt(uid,type,title,fname,mname,lname,username,company,pnumber,lnumber,email,address1,address2,city,state,pincode,country,phone);
            arrayList.add(dc);
        }
        return arrayList;
    }
    String titledoc;
    public void deleteDocumentData(String title)
    {
        SQLiteDatabase db = getWritableDatabase();
        titledoc = title;
        db.delete("document","title = ?",new String[]{String.valueOf(titledoc)});
    }

    String titleee;
    public ArrayList<String> viewDocument(String title)
    {
        titleee = title;
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM document " + " WHERE title = ?" ,new String[]{titleee});
        String uid=null,type=null,tit=null,fname=null,mname=null,lname=null,username=null,company=null,pnumber=null,lnumber=null,email=null,address1=null,address2=null,city=null,state=null,pincode=null,country=null,phone=null;
        while(cursor.moveToNext()) {
             uid = cursor.getString(0);
             type = cursor.getString(1);
             tit = cursor.getString(2);
             fname = cursor.getString(3);
             mname = cursor.getString(4);
             lname = cursor.getString(5);
             username = cursor.getString(6);
             company = cursor.getString(7);
             pnumber = cursor.getString(8);
             lnumber = cursor.getString(9);
             email = cursor.getString(10);
             address1 = cursor.getString(11);
             address2 = cursor.getString(12);
             city = cursor.getString(13);
             state = cursor.getString(14);
             pincode = cursor.getString(15);
             country = cursor.getString(16);
             phone = cursor.getString(17);
        }

        arrayList.add(uid);
        arrayList.add(type);
        arrayList.add(tit);
        arrayList.add(fname);
        arrayList.add(mname);
        arrayList.add(lname);
        arrayList.add(username);
        arrayList.add(company);
        arrayList.add(pnumber);
        arrayList.add(lnumber);
        arrayList.add(email);
        arrayList.add(address1);
        arrayList.add(address2);
        arrayList.add(city);
        arrayList.add(state);
        arrayList.add(pincode);
        arrayList.add(country);
        arrayList.add(phone);

        return arrayList;
    }

    public int editDocument(String oldtitle,String newtitle,String newfirstname ,String newmiddlename ,String newlastname ,String newusername  ,String  newcompany ,String  newpassportnumber ,String newlicensenumber ,String newemail ,String newaddress1  ,String  newaddress2 ,String newcity ,String newstate ,String newpincode  ,String  newcountry ,String  newphone )
    {   SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", newtitle);
        cv.put("firstname", newfirstname);
        cv.put("middlename", newmiddlename);
        cv.put("lastname", newlastname);
        cv.put("username", newusername);
        cv.put("company", newcompany);
        cv.put("passportnumber", newpassportnumber);
        cv.put("licensenumber", newlicensenumber);
        cv.put("email", newemail);
        cv.put("address1", newaddress1);
        cv.put("address2", newaddress2);
        cv.put("city", newcity);
        cv.put("state",  newstate);
        cv.put("pincode", newpincode);
        cv.put("country", newcountry);
        cv.put("phone", newphone);
        return db.update("document",cv,"title= ?",new String[]{String.valueOf(oldtitle)});
    }


    void addCardData(String uid ,String type ,String title ,String cardholdname,String number,String branch,String month,String year,String cvv ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uid", uid);
        cv.put("type", type);
        cv.put("title", title);
        cv.put("cardholdername", cardholdname);
        cv.put("number", number);
        cv.put("branch", branch);
        cv.put("expirationmonth", month);
        cv.put("expirationyear", year);
        cv.put("cvv", cvv);
        long res = db.insert("card",null, cv);
        if (res == -1)
        {
            Toast.makeText(context, "Failed To Add In Database", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success Add In Database", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<cardddd> getAllCard(){
        ArrayList<cardddd> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM card ",null);

        while(cursor.moveToNext())
        {
            String uid = cursor.getString(0);
            String type = cursor.getString(1);
            String title = cursor.getString(2);
            String cardholdername = cursor.getString(3);
            String number = cursor.getString(4);
            String branch = cursor.getString(5);
            String month = cursor.getString(6);
            String year = cursor.getString(7);
            String cvv = cursor.getString(8);

            cardddd cr = new cardddd(uid,type,title,cardholdername,number,branch,month,year,cvv);
            arrayList.add(cr);
        }
        return arrayList;
    }
    String titlecard;
    public void deleteCardData(String title)
    {
        SQLiteDatabase db = getWritableDatabase();
        titlecard = title;
        db.delete("card","title = ?",new String[]{String.valueOf(titlecard)});
    }

    String ti;
    public ArrayList<String> viewCard(String title)
    {
        ti = title;
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM card " + " WHERE title = ?" ,new String[]{ti});
        String uid=null,type=null,tit=null,cardholdname=null,number=null,branch=null,month=null,year=null,cvv=null;
        while(cursor.moveToNext()) {
            uid = cursor.getString(0);
            type = cursor.getString(1);
            tit = cursor.getString(2);
            cardholdname = cursor.getString(3);
            number = cursor.getString(4);
            branch = cursor.getString(5);
            month = cursor.getString(6);
            year = cursor.getString(7);
            cvv = cursor.getString(8);
        }

        arrayList.add(uid);
        arrayList.add(type);
        arrayList.add(tit);
        arrayList.add(cardholdname);
        arrayList.add(number);
        arrayList.add(branch);
        arrayList.add(month);
        arrayList.add(year);
        arrayList.add(cvv);

        return arrayList;
    }

    public int editCard(String oldtitle,String newtitle,String newcardholdname,String newnumber,String newbranch,String newmonth,String newyear,String newcvv)
    {   SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", newtitle);
        cv.put("cardholdername", newcardholdname);
        cv.put("number", newnumber);
        cv.put("branch", newbranch);
        cv.put("expirationmonth", newmonth);
        cv.put("expirationyear", newyear);
        cv.put("cvv", newcvv);
        return db.update("card",cv,"title= ?",new String[]{String.valueOf(oldtitle)});
    }

   /* Cipher cipher;
    SecretKey secretKey;
    byte[] encryptedBytes;
    public byte[] encryption(String var1) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException {
        secretKey = KeyGenerator.getInstance("AES").generateKey();

        String plaintext = var1;
        byte[] plaintextBytes = plaintext.getBytes("UTF-8");

        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        encryptedBytes = cipher.doFinal(plaintextBytes);

        return encryptedBytes;

    }
    String decryptedString;
    public String decryption(byte[] b8) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException {

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(b8);
        decryptedString = new String(decryptedBytes, "UTF-8");
        return decryptedString;
    }*/
}
