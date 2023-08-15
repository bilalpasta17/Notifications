package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent intent = getIntent();
        // Initialize the TextView
        TextView textView = findViewById(R.id.text);

        // Retrieve the "message" extra from the intent
        String message = getIntent().getStringExtra("message");
        // Set the text of the TextView to the retrieved message
        textView.setText(message);
        // Retrieve the "data" extra from the intent
        String data=getIntent().getStringExtra("data");
        // Set the text of the TextView to the retrieved data
        textView.setText(data);


    }
}