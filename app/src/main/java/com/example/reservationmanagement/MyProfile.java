package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MyProfile extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.deactivate_profile);


            AppCompatButton buttonProfileEdit = findViewById(R.id.buttonProfileEdit);
            AppCompatButton buttonDeactivate = findViewById(R.id.buttonDeactivate);


            // Retrieve user data from the Intent
            Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            String nic = intent.getStringExtra("nic");
            String email = intent.getStringExtra("email");

            buttonProfileEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an Intent to open the NewReservation activity
                    Intent intent = new Intent(MyProfile.this, EditProfile.class);

                    // Start the NewReservation activity
                    startActivity(intent);
                }
            });

            buttonDeactivate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an Intent to open the NewReservation activity
                    Intent intent = new Intent(MyProfile.this, DeactivateProfile.class);

                    // Start the NewReservation activity
                    startActivity(intent);
                }
            });

        }

        }




