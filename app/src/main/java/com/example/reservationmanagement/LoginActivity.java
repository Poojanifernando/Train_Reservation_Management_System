package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Get a reference to the "Login" button
        AppCompatButton buttonLogin = findViewById(R.id.buttonLogin);
        // Set an OnClickListener for the button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the LoginActivity
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });

    }




    }
