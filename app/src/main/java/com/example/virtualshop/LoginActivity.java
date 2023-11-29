package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository(getApplication());

        // Obtain references to UI elements
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button backButton = findViewById(R.id.backButton);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the username and password when the button is clicked
                String inputUsername = usernameEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

                // Check if the user exists
                userRepository.getUserByUsernameAndPassword(inputUsername, inputPassword, new UserRepository.UserRepositoryCallback() {
                    @Override
                    public void onUserLoaded(User user) {
                        if (user != null) {
                            // Perform login
                            startHomeActivity(user.getUsername());
                        } else {
                            // Invalid credentials, handle accordingly
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            Log.d("LoginActivity", "Invalid credentials for username: " + inputUsername);
                        }
                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate back to the landing page
                Intent landingIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(landingIntent);
                finish(); // Call finish() to close the current activity if needed
            }
        });
    }

    private void startHomeActivity(String username) {
        Intent homeIntent;
        if (username.equalsIgnoreCase("admin2")) {
            // Admin login
            homeIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            homeIntent.putExtra("USERNAME_KEY", username);
        } else {
            // User login
            homeIntent = new Intent(LoginActivity.this, WelcomeHomeActivity.class);
            homeIntent.putExtra("USERNAME_KEY", username); // Pass the username as an extra
        }

        startActivity(homeIntent);
        finish();
    }

}