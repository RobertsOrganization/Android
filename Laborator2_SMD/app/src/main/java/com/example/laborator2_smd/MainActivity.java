package com.example.laborator2_smd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.second_activity_button);
        final EditText editText = findViewById(R.id.editText);
        Button goToWebPageButton = findViewById(R.id.go_to_web_page_button);
        Button makeACallButton = findViewById(R.id.make_a_call_button);
        Button sendEmailButton = findViewById(R.id.send_email_button);
        Button getDataFromActivityButton = findViewById(R.id.get_data_button);
        Button sendTextToReceiverButton = findViewById(R.id.send_text_to_receiver_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String text = editText.getText().toString();
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });

        goToWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.webpage.com"));
                startActivity(intent);
            }
        });

        makeACallButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"enter the phone number"));
                startActivity(intent);
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = editText.getText().toString().trim();
                // We only send an email if there is text in the EditText
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(MainActivity.this, "Insert text", Toast.LENGTH_SHORT).show();
                } else {
                    Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
                    sendEmailIntent.setType("text/html");
                    sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"robert.rotsching@stud.acs.upb.ro"});
                    sendEmailIntent.putExtra(Intent.EXTRA_TITLE, "Android Title Email");
                    sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Email");
                    sendEmailIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString().trim());
                    startActivity(sendEmailIntent);
                }
            }
        });

        getDataFromActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        sendTextToReceiverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                // We only send text back if there is any
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(MainActivity.this, "Insert text", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, MyReceiver.class);
                    i.putExtra("text", text);
                    MainActivity.this.sendBroadcast(i);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(data.getStringExtra("text"));
            }
        }
    }
}
