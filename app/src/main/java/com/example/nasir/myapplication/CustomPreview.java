package com.example.nasir.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class CustomPreview extends AppCompatActivity {
    private TextView customPreviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_preview);

        setTitle("Custom Preview");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        customPreviewText = findViewById(R.id.custompreviewtext);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String v=bundle.getString("tag1");


            customPreviewText.setText(v);

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
            Intent intent = new Intent(CustomPreview.this, Help.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutusid){
            Intent intent = new Intent(CustomPreview.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.Feedback){
            Intent intent = new Intent(CustomPreview.this,Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
