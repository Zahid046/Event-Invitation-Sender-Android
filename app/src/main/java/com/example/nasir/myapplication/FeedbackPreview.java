package com.example.nasir.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FeedbackPreview extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_preview);
        setTitle("Feedback Preview");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.feedback);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



        textView=findViewById(R.id.previewdetails);



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String v=bundle.getString("tag2");


            textView.setText(v);

        }


    }
}
