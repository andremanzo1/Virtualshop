package com.example.virtualshop;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the username and role from the Intent
        String username = getIntent().getStringExtra("USERNAME_KEY");
        boolean isAdmin = getIntent().getBooleanExtra("IS_ADMIN", false);

        // Dynamically set the content view based on user type
        if (isAdmin) {
            setContentView(R.layout.activity_admin_home);
            initializeAdminHome(username);
        } else {
            setContentView(R.layout.activity_welcome_home);
            initializeUserHome(username);
        }
    }

    private void initializeUserHome(String username) {
        Log.d("LandingPageActivity", "User Landing Page Loaded");

        // Set the welcome message to a TextView in activity_welcome_home.xml
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome, " + username + "!");

        // Add user home page logic here

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the LandingPageActivity
            }
        });
    }

    private void initializeAdminHome(String username) {
        Log.d("LandingPageActivity", "Admin Landing Page Loaded");

        // Set the welcome message to a TextView in activity_admin_home.xml
        TextView adminUsernameTextView = findViewById(R.id.adminUsernameTextView);
        adminUsernameTextView.setText("Welcome, " + username + "!");

        // Add admin home page logic here

        Button adminLogoutButton = findViewById(R.id.adminLogoutButton);
        adminLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the LandingPageActivity
            }
        });
        Button viewProductsButton = findViewById(R.id.viewProductsButton);
        viewProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewProductsIntent = new Intent(LandingPageActivity.this, ViewProductsActivity.class);
                startActivity(viewProductsIntent);
            }
        });

    }
    public void onViewAccountsButtonClick(View view) {
        Intent viewAccountsIntent = new Intent(this, ViewAccountsActivity.class);
        startActivity(viewAccountsIntent);
    }

}



