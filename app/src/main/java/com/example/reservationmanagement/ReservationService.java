package com.example.reservationmanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface ReservationService {

    // GET Request to Retrieve All Reservations
    @GET("api/reservation")
    Call<List<Reservation>> getAllReservations();

    // POST Request to Create a Reservation
    @POST("api/reservation")
    Call<Void> createReservation(@Body Reservation reservation);

    // PUT Request to Update a Reservation by ID
    @PUT("api/reservation/{id}")
    Call<Void> updateReservation(@Path("id") String id, @Body Reservation updatedReservation);

    // DELETE Request to Delete a Reservation by ID
    @DELETE("api/reservation/{id}")
    Call<Void> deleteReservation(@Path("id") String id);

    // GET Request to Retrieve a Single Reservation by ID
    @GET("api/reservation/{id}")
    Call<Reservation> getReservationById(@Path("id") String id);
}
