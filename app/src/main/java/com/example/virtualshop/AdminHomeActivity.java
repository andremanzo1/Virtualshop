package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Log.d("AdminHomeActivity", "Welcome Administrator Page Loaded");

        // Add welcome administrator page logic here

        Button logoutButton = findViewById(R.id.adminLogoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
                Intent landingPageIntent = new Intent(AdminHomeActivity.this, LandingPageActivity.class);
                startActivity(landingPageIntent);
                finish(); // Close the AdminHomeActivity
            }
        });
    }
}