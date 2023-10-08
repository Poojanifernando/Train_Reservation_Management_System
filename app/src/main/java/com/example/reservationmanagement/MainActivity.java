package com.example.reservationmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the "GET STARTED!" button
        AppCompatButton buttonGetStart = findViewById(R.id.buttonGetStart);

        // Set an OnClickListener for the button
        buttonGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });
    }
}