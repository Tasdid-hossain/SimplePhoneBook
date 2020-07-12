package com.example.assignment5task1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHandler extends SQLiteOpenHelper
{

    private static final int Database_version = 2;
    private static final String Databse_name = "Contacts Record";
    private static final String Table_contacts = "Contacts";

    private static final String Id = "ID";
    private static final String Email = "Email";
    private static final String Name = "Name";
    private static final String Number = "Number";
    private static final String Category = "Category";

    public DatabaseHandler(Context context) {
        super(context, Databse_name, null, Database_version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLdb)
    {

        String Create_contacts_table = "CREATE TABLE " + Table_contacts + "(" + Id +" INTEGER PRIMARY KEY,"
                + Name + " TEXT," + Number + " TEXT," + Email + " TEXT," + Category + " TEXT" + ")";
        sqLdb.execSQL(Create_contacts_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLdb, int oldtable, int newtable)
    {

        sqLdb.execSQL("DROP TABLE IF EXISTS " + Table_contacts);
        onCreate(sqLdb);
    }

    public void addContact(ContactDetails cd)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Name, cd.getContactName());
        cv.put(Number, cd.getContactNumber());
        cv.put(Email, cd.getContactEmail());
        cv.put(Category,cd.getContactCategory());

        db1.insert(Table_contacts, null, cv);
        db1.close();
    }

    public ArrayList<ContactDetails> getAllContacts()
    {
        ArrayList<ContactDetails> contactList = new ArrayList<>();
        String selectquery = "SELECT * FROM " + Table_contacts;

        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do{
                ContactDetails cd = new ContactDetails();
                cd.setContactID(cursor.getInt(0));
                cd.setContactName(cursor.getString(1));
                cd.setContactNumber(cursor.getString(2));
                cd.setContactEmail(cursor.getString(3));
                cd.setContactCategory(cursor.getString(4));
                contactList.add(cd);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public void deleteContact(ContactDetails cd1)
    {
        SQLiteDatabase db3 = this.getWritableDatabase();
        db3.delete(Table_contacts, Id + "=?", new String[]{String.valueOf(cd1.getContactID())});

        List<ContactDetails> check_Contacts = getAllContacts();
        System.out.println(check_Contacts.size());
    }

    public int updateContact (ContactDetails cd2)
    {
        SQLiteDatabase db4 = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Name, cd2.getContactName());
        values.put(Number, cd2.getContactNumber());
        values.put(Email, cd2.getContactEmail());
        values.put(Category, cd2.getContactCategory());
        return db4.update(Table_contacts, values, Id + "=" + cd2.getContactID(),null);
    }

}
