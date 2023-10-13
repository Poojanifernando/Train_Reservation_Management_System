//package com.example.reservationmanagement;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//
//public class MyReservations extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.my_reservations);
//
//        AppCompatButton buttonAddNew = findViewById(R.id.buttonAddNew);
//        // Set an OnClickListener for the "New Reservations" button
//        buttonAddNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an Intent to open the NewReservation activity
//                Intent intent = new Intent(MyReservations.this, NewReservation.class);
//
//                // Start the NewReservation activity
//                startActivity(intent);
//            }
//        });
//    }
//}
//
//
//

package com.example.reservationmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyReservations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservations);

        recyclerView = findViewById(R.id.recyclerViewReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReservationAdapter();
        recyclerView.setAdapter(adapter);

        AppCompatButton buttonAddNew = findViewById(R.id.buttonAddNew);
        // Set an OnClickListener for the "New Reservations" button
        buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the NewReservation activity
                Intent intent = new Intent(MyReservations.this, NewReservation.class);

                // Start the NewReservation activity
                startActivity(intent);
            }
        });

        // Make the API call to retrieve all reservations
        getAllReservations();
    }

    private void getAllReservations() {
        // Create a Retrofit instance
        Retrofit retrofit = RetrofitClient.getClient();

        // Create a ReservationService instance
        ReservationService reservationService = retrofit.create(ReservationService.class);

        // Make the GET request to retrieve all reservations
        Call<List<Reservation>> call = reservationService.getAllReservations();

        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(@NonNull Call<List<Reservation>> call, @NonNull Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    // Data retrieval is successful
                    List<Reservation> reservations = response.body();
                    if (reservations != null && !reservations.isEmpty()) {
                        adapter.setReservations(reservations);
                    } else {
                        // Handle the case where no reservations were found
                        Toast.makeText(MyReservations.this, "No reservations found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle API errors here (e.g., server errors)
                    Toast.makeText(MyReservations.this, "API error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Reservation>> call, @NonNull Throwable t) {
                // Handle network errors here
                Toast.makeText(MyReservations.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Print the error stack trace for debugging
            }
        });
    }
}

