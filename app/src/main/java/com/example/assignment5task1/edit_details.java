package com.example.assignment5task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class edit_details extends AppCompatActivity {
    TextView edit_name,edit_phone,edit_email;
    Spinner edit_category;
    Button edit_save;
    int id;
    Bundle b;
    String[] contactTypes = { "Co-worker", "Family", "Friends"};
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_number);
        edit_email = findViewById(R.id.edit_email);
        edit_category= findViewById(R.id.edit_category);
        db = new DatabaseHandler(this);
        b = getIntent().getExtras();
        edit_save = findViewById(R.id.saveContact);

        id = b.getInt("id");
        edit_name.setText(b.getString("name"));
        edit_phone.setText(b.getString("number"));
        edit_email.setText(b.getString("email"));
        edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, contactTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_category.setAdapter(adapter);
        edit_category.setSelection(Arrays.asList(contactTypes).indexOf(b.getString("category")));
    }

    private void updateContact() {
        String name = edit_name.getText().toString();
        String number = edit_phone.getText().toString();
        String email = edit_email.getText().toString();
        String category = edit_category.getSelectedItem().toString();

        if(name.length() !=0 && number.length() !=0 && email.length() !=0)
        {
            Toast.makeText(getApplicationContext(), "Contacts Updated", Toast.LENGTH_SHORT).show();
            db.updateContact( new ContactDetails(id, name, number, email, category));
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Empty Field cannot be updated", Toast.LENGTH_SHORT).show();
        }
    }


}
