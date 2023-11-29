package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_home);

        Log.d("WelcomeHomeActivity", "Welcome Home Page Loaded");

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME_KEY");

        // Set the username to a TextView
        TextView usernameTextView = findViewById(R.id.welcomeTextView);
        usernameTextView.setText("Welcome, " + username + "!");

        // Add welcome home page logic here

        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(WelcomeHomeActivity.this, MainActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the WelcomeHomeActivity
            }
        });
    }
}