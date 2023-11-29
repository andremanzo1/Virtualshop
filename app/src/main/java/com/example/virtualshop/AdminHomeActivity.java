package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Log.d("AdminHomeActivity", "Admin Home Page Loaded");

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME_KEY");

        // Set the username to a TextView
        TextView adminUsernameTextView = findViewById(R.id.adminUsernameTextView);
        adminUsernameTextView.setText("Welcome, " + username + "!");

        // Add admin home page logic here

        Button adminLogoutButton = findViewById(R.id.adminLogoutButton);

        adminLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(AdminHomeActivity.this, MainActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the AdminHomeActivity
            }
        });
    }
}