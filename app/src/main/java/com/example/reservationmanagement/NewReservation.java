package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class NewReservation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reservation);

        // Get references to the buttons
        AppCompatButton buttonConfirmRes = findViewById(R.id.buttonConfirmRes);
        AppCompatButton buttonCancleRes = findViewById(R.id.buttonCancleRes);

        // Set an OnClickListener for the "confirm Reservations" button
        buttonConfirmRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the NewReservation activity
                Intent intent = new Intent(NewReservation.this, ResSummary.class);

                // Start the NewReservation activity
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the "cancle Reservations" button
        buttonCancleRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the NewReservation activity
                Intent intent = new Intent(NewReservation.this, HomeActivity.class);

                // Start the NewReservation activity
                startActivity(intent);
            }
        });
    }
}
