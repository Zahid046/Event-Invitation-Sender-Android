package com.example.nasir.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewInvitationDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView viewText;
    private Button shareButton,createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_invitaion);
        setTitle("Events");


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);




        viewText = findViewById(R.id.viewdetails);
        shareButton=findViewById(R.id.share);
        createButton=findViewById(R.id.create);



        shareButton.setOnClickListener(this);
        createButton.setOnClickListener(this);



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String v=bundle.getString("tag1");


            viewText.setText(v);

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
            Intent intent = new Intent(ViewInvitationDetails.this, Help.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.aboutusid){
            Intent intent = new Intent(ViewInvitationDetails.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.Feedback){
            Intent intent = new Intent(ViewInvitationDetails.this,Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.share){
            Toast.makeText(ViewInvitationDetails.this, "Choose a platform", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");

                String subject= "Event";
                String body= viewText.getText().toString()+"\n";


                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,body);

                startActivity(Intent.createChooser(intent,"Invite via"));
                Toast.makeText(ViewInvitationDetails.this, "Select Receiver and Send", Toast.LENGTH_SHORT).show();

        }
        if(v.getId()==R.id.create){
            Intent intent=new Intent(ViewInvitationDetails.this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //startActivity(intent);
            //startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            ViewInvitationDetails.this.finish();
        }

    }
}
