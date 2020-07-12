package com.example.assignment5task1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView name, phone, email;
    private ImageView categoryBigImage, sendCall, sendEmail;
    int id;
    String phoneNumber, emailAddress;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        name = findViewById(R.id.viewName);
        phone = findViewById(R.id.viewPhone);
        email = findViewById(R.id.viewEmail);
        categoryBigImage= findViewById(R.id.viewImage);
        sendCall = findViewById(R.id.call);
        sendEmail = findViewById(R.id.sendemail);

        b = getIntent().getExtras();
        id = b.getInt("id");
        name.setText(b.getString("name"));
        phone.setText(b.getString("number"));
        email.setText(b.getString("email"));
        setImage(b.getString("category"));
        phoneNumber=(b.getString("number"));
        emailAddress=(b.getString("email"));
        sendCall.setOnClickListener(this);
        sendEmail.setOnClickListener(this);
    }

    private void setImage(String category) {
        if(category.equals("Co-worker")){
            categoryBigImage.setImageResource(R.drawable.co_worker_icon_big1);
        }
        else if(category.equals("Family")){
            categoryBigImage.setImageResource(R.drawable.family_icon_big1);
        }
        else if(category.equals("Friends")){
            categoryBigImage.setImageResource(R.drawable.friends_icon_big1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_edit){
            Intent i = new Intent(getApplicationContext(), edit_details.class);
            i.putExtras(b);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v==sendCall){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+phoneNumber));
            startActivity(callIntent);
        }
        else if(v==sendEmail){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",emailAddress, null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        }
    }
}
