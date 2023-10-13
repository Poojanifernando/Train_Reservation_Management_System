package com.example.reservationmanagement;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Updatereservation extends AppCompatActivity {

    private EditText dateEditText, timeEditText, fromEditText, toEditText, seatsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reservation);

        dateEditText = findViewById(R.id.UpdateDate);
        timeEditText = findViewById(R.id.UpdateTime);
        fromEditText = findViewById(R.id.UpdateDesFrom);
        toEditText = findViewById(R.id.UpdateDesTo);
        seatsEditText = findViewById(R.id.UpdateSeats);
        Button updateButton = findViewById(R.id.buttonConfirmUpdate);

        // Retrieve the reservation ID from the intent
        final String reservationId = getIntent().getStringExtra("reservationId");

        // Retrieve the existing reservation details from the intent
        String existingDate = getIntent().getStringExtra("date");
        String existingTime = getIntent().getStringExtra("time");
        String existingFrom = getIntent().getStringExtra("from");
        String existingTo = getIntent().getStringExtra("to");
        String existingSeats = getIntent().getStringExtra("seats");

        // Set the existing data in the respective EditText fields
        dateEditText.setText(existingDate);
        timeEditText.setText(existingTime);
        fromEditText.setText(existingFrom);
        toEditText.setText(existingTo);
        seatsEditText.setText(existingSeats);

        // Set an OnClickListener for the "Update" button
        updateButton.setOnClickListener(view -> {
            // Get the updated data from EditText fields
            String updatedDate = dateEditText.getText().toString();
            String updatedTime = timeEditText.getText().toString();
            String updatedFrom = fromEditText.getText().toString();
            String updatedTo = toEditText.getText().toString();
            String updatedSeats = seatsEditText.getText().toString();

            // Create an instance of the ReservationService
            ReservationService reservationService = RetrofitClient.getClient().create(ReservationService.class);

            // Create an instance of the updated reservation
            Reservation updatedReservation = new Reservation();
            updatedReservation.setDate(updatedDate);
            updatedReservation.setTime(updatedTime);
            updatedReservation.setDestinationFrom(updatedFrom);
            updatedReservation.setDestinationTo(updatedTo);
            updatedReservation.setSeats(updatedSeats);

            // Make a PUT request to update the reservation
            Call<Void> call = reservationService.updateReservation(reservationId, updatedReservation);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Reservation was successfully updated
                        Toast.makeText(Updatereservation.this, "Reservation updated successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close the activity
                    } else {
                        // Handle API errors (e.g., validation errors, server errors)
                        try {
                            String errorBody = response.errorBody().string();
                            Log.d("UpdateReservation", "Error Response Body: " + errorBody);
                            // Handle the error based on the response errorBody
                            Toast.makeText(Updatereservation.this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle network errors
                    Toast.makeText(Updatereservation.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace(); // Print the error stack trace for debugging
                }
            });
        });
    }
}
