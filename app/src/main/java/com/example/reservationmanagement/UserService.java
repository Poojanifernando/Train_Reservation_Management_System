package com.example.reservationmanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface UserService {

    @POST("api/UserRegistration") // Adjust the URL accordingly
    Call<Void> createUser(@Body UserRegistration userRegistration);

    @GET("api/UserRegistration") // Adjust the URL accordingly
    Call<List<UserRegistration>> getUsers();

    @PUT("api/UserRegistration/{id}") // Adjust the URL accordingly
    Call<Void> updateUser(@Path("id") String id, @Body UserRegistration updatedUser);

    @DELETE("api/UserRegistration/{id}") // Adjust the URL accordingly
    Call<Void> deleteUser(@Path("id") String id);
}