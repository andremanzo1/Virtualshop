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
                            if (user.isAdmin() && inputUsername.equals("admin2")) {
                                // Admin (admin2) trying to log in through user option - show error
                                Toast.makeText(LoginActivity.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
                            } else {
                                // User is not an admin or admin2 - perform login
                                Intent userIntent = new Intent(LoginActivity.this, WelcomeHomeActivity.class);
                                startActivity(userIntent);
                                finish();
                            }
                        } else {
                            // Invalid credentials, handle accordingly
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            Log.d("LoginActivity", "Invalid credentials for username: " + inputUsername);
                        }
                    }
                });

                // Continue with other login logic...
            }
        });
    }
}