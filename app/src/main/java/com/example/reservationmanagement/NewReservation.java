package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.widget.AppCompatButton;

public class NewReservation extends AppCompatActivity {

    private EditText dateEditText, timeEditText, fromEditText, toEditText, seatsEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reservation);

        // Get references to the buttons
//        AppCompatButton buttonConfirmRes = findViewById(R.id.buttonConfirmRes);
        AppCompatButton buttonCancleRes = findViewById(R.id.buttonCancleRes);

//        // Set an OnClickListener for the "confirm Reservations" button
//        buttonConfirmRes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an Intent to open the NewReservation activity
//                Intent intent = new Intent(NewReservation.this, ResSummary.class);
//
//                // Start the NewReservation activity
//                startActivity(intent);
//            }
//        });

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

        // Initialize UI components
        dateEditText = findViewById(R.id.SelectDate);
        timeEditText = findViewById(R.id.SelectTime);
        fromEditText = findViewById(R.id.DesFrom);
        toEditText = findViewById(R.id.DesTo);
        seatsEditText = findViewById(R.id.Seats);
        Button confirmButton = findViewById(R.id.buttonConfirmRes);

        // Set an OnClickListener for the "Confirm" button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReservation();
            }
        });
    }

    private void createReservation() {
        // Get user input from EditText fields
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String from = fromEditText.getText().toString();
        String to = toEditText.getText().toString();
        String seats = seatsEditText.getText().toString();

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5010/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create a ReservationService instance
        ReservationService reservationService = retrofit.create(ReservationService.class);

        // Create a Reservation object with user input
        Reservation reservation = new Reservation();
        reservation.setDate(date);
        reservation.setTime(time);
        reservation.setDestinationFrom(from);
        reservation.setDestinationTo(to);
        reservation.setSeats(seats);

        // Make a POST request to create the reservation
        Call<Void> call = reservationService.createReservation(reservation);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Reservation was successfully created
                    Intent intent = new Intent(NewReservation.this, ResSummary.class);
                    // Pass data as extras in the intent
                    intent.putExtra("date", date);
                    intent.putExtra("time", time);
                    intent.putExtra("from", from);
                    intent.putExtra("to", to);
                    intent.putExtra("seats", seats);
                    startActivity(intent);
                } else {
                    // Handle API errors (e.g., validation errors, server errors)
                    Toast.makeText(NewReservation.this, "reservation failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network errors
                Toast.makeText(NewReservation.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Print the error stack trace for debugging
            }
        });


    }

    }

