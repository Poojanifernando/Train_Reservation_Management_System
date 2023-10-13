package com.example.reservationmanagement;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservationmanagement.Reservation;
import com.example.reservationmanagement.ReservationAdapter;
import com.example.reservationmanagement.ReservationService;
import com.example.reservationmanagement.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyBookings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_bookings);

        recyclerView = findViewById(R.id.recyclerViewBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReservationAdapter();
        recyclerView.setAdapter(adapter);

        // Make the API call to retrieve all reservations
        getAllFutureBookings();
    }

    private void getAllFutureBookings() {
        // Create a Retrofit instance
        Retrofit retrofit = RetrofitClient.getClient();

        // Create a ReservationService instance
        ReservationService reservationService = retrofit.create(ReservationService.class);

        // Make the GET request to retrieve all reservations
        Call<List<Reservation>> call = reservationService.getAllReservations();

        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    // Data retrieval is successful
                    List<Reservation> allReservations = response.body();

                    // Filter reservations for those with dates after today
                    List<Reservation> filteredReservations = filterReservationsAfterToday(allReservations);

                    // Sort the filtered reservations by date in ascending order
                    Collections.sort(filteredReservations, new ReservationDateComparator());

                    if (!filteredReservations.isEmpty()) {
                        adapter.setReservations(filteredReservations);
                    } else {
                        // Handle the case where no future reservations were found
                        Toast.makeText(MyBookings.this, "No future reservations found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle API errors here (e.g., server errors)
                    Toast.makeText(MyBookings.this, "API error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                // Handle network errors here
                Toast.makeText(MyBookings.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Print the error stack trace for debugging
            }
        });
    }

    // Helper method to filter reservations with dates after today
    private List<Reservation> filterReservationsAfterToday(List<Reservation> allReservations) {
        List<Reservation> filteredReservations = new ArrayList<>();
        Date currentDate = new Date();

        for (Reservation reservation : allReservations) {
            // Convert the reservation date to a Date object (you'll need to implement this)
            Date reservationDate = parseReservationDate(reservation.getDate());

            // Check if the reservation date is after today's date
            if (reservationDate != null && reservationDate.after(currentDate)) {
                filteredReservations.add(reservation);
            }
        }

        return filteredReservations;
    }

    // Helper method to parse the reservation date (you need to implement this)
    private Date parseReservationDate(String dateStr) {
        // Parse the date string and return a Date object
        // You can use SimpleDateFormat or any other suitable method for parsing
        // Return null if parsing fails
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Comparator to sort reservations by date in ascending order
    private static class ReservationDateComparator implements Comparator<Reservation> {
        @Override
        public int compare(Reservation reservation1, Reservation reservation2) {
            // Implement date comparison logic here
            // Example: return reservation1.getDate().compareTo(reservation2.getDate());
            return 0; // Replace with actual comparison logic
        }
    }
}
