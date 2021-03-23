package com.example.nasir.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Eventview extends AppCompatActivity implements View.OnClickListener {
    ArrayList<File> events;
    int position;
    int sz;
    public Uri u;
    private TextView filetext;

    private Button sendinviteButton,deleteinviteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventview);
        setTitle(" Event");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        filetext=findViewById(R.id.details);
        sendinviteButton=findViewById(R.id.shareinvite);
        deleteinviteButton=findViewById(R.id.deleteinvite);

        sendinviteButton.setOnClickListener(this);
        deleteinviteButton.setOnClickListener(this);


        Intent i = getIntent();
        Bundle b = i.getExtras();

        events = (ArrayList) b.getParcelableArrayList("eventlist");
        position = b.getInt("pos", 0);
        sz = events.size();


        u = Uri.parse(events.get(position).toString());
        System.out.println(u);
        File file=new File(String.valueOf(u));
        String [] loadtext=Load(file);

        String finalString="";
        for(int j=0; j<loadtext.length; j++){
            finalString += loadtext[j]+System.getProperty("line.separator");

        }
        filetext.setText(finalString);


    }
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.shareinvite){
            Toast.makeText(Eventview.this, "Choose a platform", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");

            String subject= "Event";
            String body= filetext.getText().toString()+"\n";


            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);

            startActivity(Intent.createChooser(intent,"Invite via"));
            Toast.makeText(Eventview.this, "Select Receiver and Send", Toast.LENGTH_SHORT).show();

        }
        if(v.getId()==R.id.deleteinvite){
            File file = new File(String.valueOf(u));
            file.delete();
            Toast.makeText(Eventview.this, "Invitation Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Eventview.this, EventList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Eventview.this.finish();
            //startActivity(intent);

        }

    }

    public static String[] Load(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        int anzahl = 0;
        try {
            while (( br.readLine()) != null) {
                anzahl++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                array[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menulayout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.helpid) {
            Intent intent = new Intent(Eventview.this, Help.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.aboutusid) {
            Intent intent = new Intent(Eventview.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.Feedback) {
            Intent intent = new Intent(Eventview.this, Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }


}
