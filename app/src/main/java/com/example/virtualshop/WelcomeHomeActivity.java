package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_home);

        Log.d("WelcomeHomeActivity", "Welcome Home Page Loaded");

        // Add welcome home page logic here

        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(WelcomeHomeActivity.this, LandingPageActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the WelcomeHomeActivity
            }
        });

    }
}