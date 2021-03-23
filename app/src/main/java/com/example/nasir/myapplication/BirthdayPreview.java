package com.example.nasir.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class BirthdayPreview extends AppCompatActivity {
    private TextView birthdayPreviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday_preview);

        setTitle("Birthday Preview");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        birthdayPreviewText = findViewById(R.id.birthdaypreviewtext);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String v=bundle.getString("tag1");


            birthdayPreviewText.setText(v);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menulayout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.helpid){
            Intent intent = new Intent(BirthdayPreview.this, Help.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutusid){
            Intent intent = new Intent(BirthdayPreview.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.Feedback){
            Intent intent = new Intent(BirthdayPreview.this,Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
