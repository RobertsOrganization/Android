package com.example.laborator2_smd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final EditText editText = findViewById(R.id.editTextThirdActivity);
        Button button = findViewById(R.id.third_activity_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                // We only send text back if there is any
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(ThirdActivity.this, "Insert text", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("text", text);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

}
