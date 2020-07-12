package com.example.assignment5task1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactDetails> cd;
    private ListView listView;
    private ContactListAdapter cla;
    private FloatingActionButton fab;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PhoneBook");

        fab = findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list);
        populateDB();
        UpdateContacts();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddContact.class);
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Confirm deletion");
                alertDialog.setMessage("Are you sure you want to delete this contact? ");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("Delete Contact: " + cd.get(position).getContactID());
                        db.deleteContact(cd.get(position));
                        cd.remove(position);
                        cla.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateDB()
    {
        db = new DatabaseHandler(this);
        cd = db.getAllContacts();
        if (cd != null) {

            cla = new ContactListAdapter(cd, this);
            listView.setAdapter(cla);
        }
    }

    public void UpdateContacts()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, ViewDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", cd.get(position).getContactID());
                bundle.putString("name", cd.get(position).getContactName());
                bundle.putString("number", cd.get(position).getContactNumber());
                bundle.putString("email", cd.get(position).getContactEmail());
                bundle.putString("category", cd.get(position).getContactCategory());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
