package com.example.admin.olayscontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 8/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactsDatabase";

    public static final String TABLE_NAME = "Contacts";
    public static final String CONTACT_ID = "Id";
    public static final String CONTACT_FIRSTNAME = "FirstName";
    public static final String CONTACT_LASTNAME = "LastName";
    public static final String CONTACT_PHONENUMBER = "PhoneNumber";
    private static final String CONTACT_COMPANY = "Company";
    public static final String CONTACT_PHOTO = "Photo";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACT_FIRSTNAME +
            " TEXT," + CONTACT_LASTNAME + " TEXT," + CONTACT_PHONENUMBER + " TEXT," +
            CONTACT_COMPANY + " Text," + CONTACT_PHOTO + " BLOB" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public void saveNewContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_FIRSTNAME, contact.getFirstName());
        cv.put(CONTACT_LASTNAME, contact.getLastName());
        cv.put(CONTACT_PHONENUMBER, contact.getPhoneNumber());
        cv.put(CONTACT_COMPANY, contact.getCompany());
        cv.put(CONTACT_PHOTO, contact.getPhoto());

        db.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Contact> getContact(){

        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact(
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getBlob(5)
                );
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        return contacts;
    }


    public Contact getOneContact(int position){

        Contact contact = new Contact();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ CONTACT_ID + "=" + 0;

        //String[] selectionArg = new String[]{CONTACT_NAME, "John"};

        Cursor cursor = db.rawQuery(query, null);

        if(cursor == null)
            return null;

        contact = new Contact(
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getBlob(5)
        );

        return contact;
    }

    public void deleteContact(int position){

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + CONTACT_ID + "=" + position;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, CONTACT_ID + " = ?", new String[] {String.valueOf(position)});

        Cursor cursor = db.rawQuery(query, null);
    }

    public void saveContact(Contact contact, int position){
        // work on logic here
        if(position != -1) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(CONTACT_FIRSTNAME, contact.getFirstName());
            cv.put(CONTACT_LASTNAME, contact.getFirstName());
            cv.put(CONTACT_PHONENUMBER, contact.getFirstName());
            cv.put(CONTACT_COMPANY, contact.getFirstName());
            cv.put(CONTACT_PHOTO, contact.getPhoto());

            db.update(TABLE_NAME, cv, "_id=" + position, null);
        }
    }
}
