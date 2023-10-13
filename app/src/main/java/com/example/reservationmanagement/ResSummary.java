package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ResSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_summary);

        // Retrieve data from Intent extras
        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");
        final String from = intent.getStringExtra("from");
        final String to = intent.getStringExtra("to");
        final String seats = intent.getStringExtra("seats");

        // Display data in TextViews
        TextView dateTextView = findViewById(R.id.textViewDate);
        TextView timeTextView = findViewById(R.id.textViewTime);
        TextView fromTextView = findViewById(R.id.textViewdestifrom);
        TextView toTextView = findViewById(R.id.textViewdestito);
        TextView seatsTextView = findViewById(R.id.textViewnumseats);

        dateTextView.setText("Date: " + date);
        timeTextView.setText("Time: " + time);
        fromTextView.setText("Destination From: " + from);
        toTextView.setText("Destination To: " + to);
        seatsTextView.setText("Number of Seats: " + seats);

        // Set an OnClickListener for the "EDIT" button
        AppCompatButton editButton = findViewById(R.id.buttonEdit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the Updatereservation activity
                Intent editIntent = new Intent(ResSummary.this, Updatereservation.class);

                // Pass reservation details as extras to the Updatereservation activity
                editIntent.putExtra("date", date);
                editIntent.putExtra("time", time);
                editIntent.putExtra("from", from);
                editIntent.putExtra("to", to);
                editIntent.putExtra("seats", seats);

                // Start the Updatereservation activity
                startActivity(editIntent);
            }
        });
    }
}
