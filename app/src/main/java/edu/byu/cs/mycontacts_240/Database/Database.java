package edu.byu.cs.mycontacts_240.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.mycontacts_240.Model.Contact;

/**
 * Created by blaine on 5/27/15.
 */
public class Database extends SQLiteOpenHelper
{
    private static final String ROW_ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String ZIP = "zip";
    private static final String EMAIL = "email";
    static int currentVersion = 4;
    static String DB_NAME = "Contacts";

    public Database(Context context)
    {
        super(context, DB_NAME, null, currentVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "Create table contacts (" +
                ROW_ID + " integer PRIMARY KEY autoincrement , " +
                FIRST_NAME + " varchar(25),"+
                LAST_NAME + " varchar(25),"+
                PHONE + " varchar(25),"+
                ADDRESS + " varchar(25),"+
                CITY + " varchar(25),"+
                ZIP + " varchar(25),"+
                EMAIL + " varchar(25));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, contact.firstName);
        values.put(LAST_NAME, contact.lastName);
        values.put(PHONE, contact.phone);
        values.put(ADDRESS, contact.address);
        values.put(CITY, contact.city);
        values.put(ZIP, contact.zip);
        values.put(EMAIL, contact.email);
        contact.rowID = (int)db.insert(DB_NAME,null,values);

        //upDateContact(contact);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void upDateContact(Contact contact)
    {
        String strFilter = ROW_ID + " = " + String.valueOf(contact.rowID);
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, contact.firstName);
        values.put(LAST_NAME, contact.lastName);
        values.put(PHONE, contact.phone);
        values.put(ADDRESS, contact.address);
        values.put(CITY, contact.city);
        values.put(ZIP, contact.zip);
        values.put(EMAIL, contact.email);

        getWritableDatabase().update(DB_NAME, values, strFilter, null);

    }

    public boolean deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_NAME, ROW_ID + "=" + String.valueOf(contact.rowID), null) > 0;
    }

    public List<Contact> getAllContacts()
    {
        List<Contact> list = new ArrayList<>();

        String sql = "Select * from  contacts";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = null;
        try {
            cur = db.rawQuery(sql, null);

            if (cur.moveToFirst()) {

                do {
                    Contact contact = new Contact();
                    contact.rowID = cur.getInt(0);
                    contact.firstName = cur.getString(1);
                    contact.lastName = cur.getString(2);
                    contact.phone = cur.getString(3);
                    contact.address = cur.getString(4);
                    contact.city = cur.getString(5);
                    contact.zip = cur.getString(6);
                    contact.email = cur.getString(7);
                    list.add(contact);

                }while(cur.moveToNext());

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(cur != null)
                cur.close();
        }

        return list;
    }

}
