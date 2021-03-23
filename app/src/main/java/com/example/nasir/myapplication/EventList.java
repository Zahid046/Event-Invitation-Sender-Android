package com.example.nasir.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class EventList extends AppCompatActivity {

    ListView eventList;
    String[] event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        setTitle(" Event List");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        eventList=findViewById(R.id.eventlist);

        final ArrayList<File> events=findevents(new File(Environment.getExternalStorageDirectory() + "/Event Invitation Sender"));
        event=new String[events.size()];
        int sz=events.size();
        for(int i=0; i<sz; i++){
            event[i]=events.get(i).getName().toString().replace(".txt","");
            //System.out.println(event[i]);
        }
        //System.out.println(events);
        ArrayAdapter<String> adp=new ArrayAdapter<>(getApplicationContext(),R.layout.invitation_layout,R.id.invitationtext,event);
        eventList.setAdapter(adp);
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Eventview.class).putExtra("pos",position).putExtra("eventlist",events));
            }
        });
    }

    public ArrayList<File> findevents(File root){
        ArrayList<File> al=new ArrayList<>();
        File[] files=root.listFiles();
        for(File singlefile: files){
            if(singlefile.isDirectory() && !singlefile.isHidden()){
                al.addAll(findevents(singlefile));
            }
            else {
                if (singlefile.getName().endsWith(".txt")) {
                    al.add(singlefile);
                }
            }
        }
        return al;
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
            Intent intent = new Intent(EventList.this, Help.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutusid){
            Intent intent = new Intent(EventList.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.Feedback){
            Intent intent = new Intent(EventList.this,Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
