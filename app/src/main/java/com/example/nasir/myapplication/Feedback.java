package com.example.nasir.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener {

    private Button sendbutton, clearbutton, preview;
    private EditText nameedit, masseageedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        setTitle("Feedback");


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.feedback);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        sendbutton = findViewById(R.id.sendId);
        clearbutton = findViewById(R.id.clearid);
        preview=findViewById(R.id.viewid);

        nameedit = findViewById(R.id.nameedit);
        masseageedit = findViewById(R.id.massegeedit);


        sendbutton.setOnClickListener(this);
        clearbutton.setOnClickListener(this);
        preview.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        try {

            String name = nameedit.getText().toString();
            String masseage = masseageedit.getText().toString();


            if (v.getId() == R.id.sendId) {
                if (name.isEmpty() || masseage.isEmpty()) {
                    Toast.makeText(Feedback.this, "Fill up all requirements", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Feedback.this, "Choose a platform and Send", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");

                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zmemon046@gmail.com"});

                    intent.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");

                    intent.putExtra(Intent.EXTRA_TEXT, "Name : " + name + "\n Message : " + masseage+"\n\n");


                    startActivity(Intent.createChooser(intent, "Feedback via"));

                }
            }

            if (v.getId() == R.id.clearid) {

                nameedit.setText("");
                masseageedit.setText("");
                Toast.makeText(Feedback.this, "Cleared", Toast.LENGTH_SHORT).show();
            }


            if(v.getId()== R.id.viewid){

                Intent intent = new Intent(Feedback.this, FeedbackPreview.class);
                if (name.isEmpty() || masseage.isEmpty()) {
                    Toast.makeText(Feedback.this, "Fill up all requirements", Toast.LENGTH_SHORT).show();
                } else {
                    String s5 = "Name : " + name + "\n\nFeedback : " + masseage+"\n";
                    intent.putExtra("tag2", s5);
                    Toast.makeText(Feedback.this, "Preview", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }



            }


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Exception : " + e, Toast.LENGTH_SHORT).show();

        }


    }
}
