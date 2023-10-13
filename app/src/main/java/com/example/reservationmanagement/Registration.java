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

public class Registration extends AppCompatActivity {

    private UserService userService;

    // Declare EditText fields and the registration button
    private EditText nameEditText, nicEditText, emailEditText, passwordEditText, retypePasswordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        // Initialize the Retrofit client and service
        Retrofit retrofit = RetrofitClient.getClient();
        userService = retrofit.create(UserService.class);

        // Initialize EditText fields and the registration button
        nameEditText = findViewById(R.id.Name);
        nicEditText = findViewById(R.id.NIC);
        emailEditText = findViewById(R.id.Email);
        passwordEditText = findViewById(R.id.password);
        retypePasswordEditText = findViewById(R.id.retypepassword);
        registerButton = findViewById(R.id.buttonRegister);

        // Set a click listener for the registration button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String name = nameEditText.getText().toString();
                String nic = nicEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String retypePassword = retypePasswordEditText.getText().toString();

                // Create a new UserRegistration object
                UserRegistration newUser = new UserRegistration();
                newUser.setName(name);
                newUser.setNIC(nic);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRetypePassword(retypePassword);

                // Make a POST request to register the user
                Call<Void> call = userService.createUser(newUser);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            // Create an Intent to open the MyProfile activity
                            Intent intent = new Intent(Registration.this, MyProfile.class);

                            // Pass the user data to the MyProfile activity using Intent extras
                            intent.putExtra("name", name);
                            intent.putExtra("nic", nic);
                            intent.putExtra("email", email);

                            // Optionally, you can navigate to the MyProfile activity here
                            startActivity(intent);
                        } else {
                            // Registration failed, handle the error response
                            Toast.makeText(Registration.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle network or other failures here
                        Toast.makeText(Registration.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
