package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userRepository = new UserRepository(getApplication());

        EditText usernameEditText = findViewById(R.id.createUsernameEditText);
        EditText passwordEditText = findViewById(R.id.createPasswordEditText);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered username and password
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Check if the username is available
                userRepository.getUserByUsername(enteredUsername, new UserRepository.UserRepositoryCallback() {
                    @Override
                    public void onUserLoaded(User user) {
                        if (user == null) {
                            // Username is available, create the account
                            User newUser = new User(enteredUsername, enteredPassword, false);
                            userRepository.insertUser(newUser);

                            // Auto-login after account creation
                            Intent homeIntent = new Intent(CreateAccountActivity.this, WelcomeHomeActivity.class);
                            startActivity(homeIntent);
                            finish(); // Optional: Close the create account activity
                        } else {
                            // Username is already taken, show a message
                            Toast.makeText(CreateAccountActivity.this, "Username already in use", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}