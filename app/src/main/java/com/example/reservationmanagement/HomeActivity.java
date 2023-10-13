package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_reservation);

        // Get references to the buttons
        AppCompatButton buttonnew = findViewById(R.id.buttonnew);
        AppCompatButton buttonprofile = findViewById(R.id.buttonprofile);
        AppCompatButton buttonres = findViewById(R.id.buttonres);
        AppCompatButton buttonongoing = findViewById(R.id.buttonongoing);

        // Set an OnClickListener for the "New Reservations" button
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the NewReservation activity
                Intent intent = new Intent(HomeActivity.this, NewReservation.class);

                // Start the NewReservation activity
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the "My Profile" button
        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the MyProfile activity
                Intent intent = new Intent(HomeActivity.this, MyProfile.class);

                // Start the MyProfile activity
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the "My Reservations" button
        buttonres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the MyReservations activity
                Intent intent = new Intent(HomeActivity.this, MyReservations.class);

                // Start the MyReservations activity
                startActivity(intent);
            }
        });

        buttonongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the my bookings activity
                Intent intent = new Intent(HomeActivity.this, MyBookings.class);

                // Start the MyReservations activity
                startActivity(intent);
            }
        });
    }
}
