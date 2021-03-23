package com.example.nasir.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Category extends AppCompatActivity implements View.OnClickListener {


    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        setTitle("Create Event");

        File file=new File(Environment.getExternalStorageDirectory()+"/Event Invitation Sender");
        if(!file.exists()){
            file.mkdirs();
        }
        File birthfile=new File(Environment.getExternalStorageDirectory()+"/Event Invitation Sender/Birthday");
        File marriagefile=new File(Environment.getExternalStorageDirectory()+"/Event Invitation Sender/Marriage");
        File customfile=new File(Environment.getExternalStorageDirectory()+"/Event Invitation Sender/Custom");

        if(!birthfile.exists()){
            birthfile.mkdirs();
        }
        if(!marriagefile.exists()){
            marriagefile.mkdirs();
        }
        if(!customfile.exists()){
            customfile.mkdirs();
        }


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        button1=findViewById(R.id.birthday);
        button2=findViewById(R.id.marriage);
        button3=findViewById(R.id.custom);


        button1.setOnClickListener(this);

        button2.setOnClickListener(this);

        button3.setOnClickListener(this);





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
            Intent intent = new Intent(Category.this, Help.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutusid){
            Intent intent = new Intent(Category.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.Feedback){
            Intent intent = new Intent(Category.this,Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


        if(v.getId()==R.id.birthday) {
            Intent intent = new Intent(Category.this, CreateBirthdayInvitation.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.marriage) {
            Intent intent = new Intent(Category.this, CreateMarriageInvitation.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.custom) {
            Intent intent = new Intent(Category.this, CreateCustomInvitation.class);
            startActivity(intent);
        }

    }
}
