package com.example.assignment5task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    private EditText eName, eEmail, eNumber;
    private Spinner eCategory;
    private int i;
    private Button save;
    DatabaseHandler dhandler;
    String[] contactTypes = { "Co-worker", "Family", "Friends"};

    ArrayList<ContactDetails> cD = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        eName = (EditText) findViewById(R.id.edit_name);
        eNumber = (EditText) findViewById(R.id.edit_number);
        eEmail = (EditText) findViewById(R.id.edit_email);
        eCategory = (Spinner) findViewById(R.id.edit_category);
        save = (Button) findViewById(R.id.save);

        getSupportActionBar().setTitle("Add Contact");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, contactTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eCategory.setAdapter(adapter);

        dhandler = new DatabaseHandler(this);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String name = eName.getText().toString();
                String number = eNumber.getText().toString();
                String email = eEmail.getText().toString();
                String category = eCategory.getSelectedItem().toString();

                if(name.length() !=0 && number.length() !=0 && email.length() !=0)
                {

                    Toast.makeText(getApplicationContext(), "Contacts Saved", Toast.LENGTH_SHORT).show();
                    dhandler.addContact(new ContactDetails(name, number, email, category));
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Complete all the required fields", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
