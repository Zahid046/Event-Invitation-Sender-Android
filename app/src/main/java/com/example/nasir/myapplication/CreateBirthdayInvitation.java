package com.example.nasir.myapplication;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import java.util.Random;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CreateBirthdayInvitation extends AppCompatActivity implements View.OnClickListener {

    private EditText address, description,host;
    private Button clearButton, saveButton, previewButton, dateButton, timeButton;
    private TextView dateText, timeText;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_birthday_invitation);

        setTitle("Birthday");


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.event);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        address = findViewById(R.id.addressedittext);
        description = findViewById(R.id.descriptionedittext);
        host=findViewById(R.id.host);
        dateText = findViewById(R.id.datetext);
        timeText = findViewById(R.id.timetext);

        previewButton = findViewById(R.id.previewbutton);
        saveButton = findViewById(R.id.savebutton);
        clearButton = findViewById(R.id.clearbutton);
        dateButton = findViewById(R.id.setdate);
        timeButton = findViewById(R.id.settime);


        previewButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);
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
            Intent intent = new Intent(CreateBirthdayInvitation.this, Help.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.aboutusid) {
            Intent intent = new Intent(CreateBirthdayInvitation.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.Feedback) {
            Intent intent = new Intent(CreateBirthdayInvitation.this, Feedback.class);
            startActivity(intent);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.setdate) {

            DatePicker datePicker = new DatePicker(this);
            int currentday = datePicker.getDayOfMonth();
            int currentmonth = datePicker.getMonth();
            int currentyear = (datePicker.getYear());


            datePickerDialog = new DatePickerDialog(this,

                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            dateText.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                        }
                    }, currentyear, currentmonth, currentday);

            datePickerDialog.show();
        }


        if (v.getId() == R.id.settime) {

            TimePicker timePicker = new TimePicker(this);
            int currenthour = timePicker.getCurrentHour();
            int currentminute = timePicker.getCurrentMinute();


            timePickerDialog = new TimePickerDialog(this,

                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String am_pm;
                            if (hourOfDay < 12 && hourOfDay>0) {
                                am_pm = "AM";
                                if (hourOfDay < 10 && minute < 10) {
                                    timeText.setText("0" + hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (hourOfDay >= 10 && minute < 10) {
                                    timeText.setText(hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (hourOfDay < 10 && minute >= 10) {
                                    timeText.setText("0" + hourOfDay + ":" + minute + " " + am_pm);
                                }
                                else {
                                    timeText.setText(hourOfDay + ":" + minute + " " + am_pm);
                                }
                            }else if(hourOfDay==0){
                                am_pm="AM";
                                hourOfDay=hourOfDay+12;
                                if (minute < 10) {
                                    timeText.setText(hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (minute >= 10) {
                                    timeText.setText(hourOfDay + ":" + minute + " " + am_pm);
                                }

                            }
                            else if (hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                                am_pm = "PM";
                                if (hourOfDay < 10 && minute < 10) {
                                    timeText.setText("0" + hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (hourOfDay >= 10 && minute < 10) {
                                    timeText.setText(hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (hourOfDay < 10 && minute >= 10) {
                                    timeText.setText("0" + hourOfDay + ":" + minute + " " + am_pm);
                                }
                                else {
                                    timeText.setText(hourOfDay + ":" + minute + " " + am_pm);
                                }
                            } else {
                                am_pm = "PM";
                                if (minute < 10) {
                                    timeText.setText(hourOfDay + ":0" + minute + " " + am_pm);
                                } else if (minute >= 10) {
                                    timeText.setText(hourOfDay + ":" + minute + " " + am_pm);
                                }
                            }

                        }
                    }, currenthour, currentminute, true);

            timePickerDialog.show();


        }

        if (v.getId() == R.id.previewbutton) {
            Intent intent = new Intent(CreateBirthdayInvitation.this, BirthdayPreview.class);
            String s1 =address.getText().toString();
            String s2 = description.getText().toString();
            String hostname=host.getText().toString();
            String s3 = dateText.getText().toString();
            String s4 = timeText.getText().toString();
            if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || hostname.isEmpty()) {
                Toast.makeText(CreateBirthdayInvitation.this, "Fill up all requirements", Toast.LENGTH_SHORT).show();
            } else {
                String s5 = "Event : Birthday\n\nAddress : " + s1 + "\n\nDescription : " + s2 + "\n\nHosted By : "+hostname+"\n\nDate : " + s3 + "\n\nTime : " + s4;
                intent.putExtra("tag1", s5);
                Toast.makeText(CreateBirthdayInvitation.this, "Preview", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.savebutton) {
            Intent intent = new Intent(CreateBirthdayInvitation.this, ViewInvitationDetails.class);
            String s1 = address.getText().toString();
            String s2 =description.getText().toString();
            String hostname=host.getText().toString();
            String s3 = dateText.getText().toString();
            String s4 = timeText.getText().toString();

            if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
                Toast.makeText(CreateBirthdayInvitation.this, "Fill up all requirements", Toast.LENGTH_SHORT).show();
            } else {

                String s5 = "Event : Birthday\n\nAddress : " + s1 + "\n\nDescription : " + s2 + "\n\nHosted By : "+hostname+"\n\nDate : " + s3 + "\n\nTime : " + s4;
                intent.putExtra("tag1", s5);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                CreateBirthdayInvitation.this.finish();
                //startActivity(intent);
                String filename =s3+"__"+s4;
                String content = s5;
                saveTextAsFile(filename,content);

            }
        }
        if (v.getId() == R.id.clearbutton) {

            address.setText("");
            description.setText("");
            dateText.setText("");
            timeText.setText("");
            Toast.makeText(CreateBirthdayInvitation.this, "Cleared", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveTextAsFile(String filename, String content){
        int rand_fileNumber = rand.nextInt(100000000);
        filename ="Birthday__"+filename+"__"+rand_fileNumber+".txt";

        File file=new File(Environment.getExternalStorageDirectory()+"/Event Invitation Sender/Birthday",filename);



        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"File Not Found",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error Saving",Toast.LENGTH_SHORT).show();
        }

    }

}
