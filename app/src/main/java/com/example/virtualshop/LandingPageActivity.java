package com.example.virtualshop;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class LandingPageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView isAdminTextView = findViewById(R.id.isAdminTextView);
        Button adminButton = findViewById(R.id.adminButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Retrieve user details from SharedPreferences or Room database
        String username = "testuser1"; // Replace with actual username
        boolean isAdmin = false; // Replace with actual isAdmin value

        usernameTextView.setText("Username: " + username);
        isAdminTextView.setText("Is Admin: " + (isAdmin ? "Yes" : "No"));

        // Show/hide admin button based on isAdmin status
        adminButton.setVisibility(isAdmin ? View.VISIBLE : View.GONE);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to access admin area
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to logout (clear SharedPreferences) and start MainActivity
                startActivity(new Intent(LandingPageActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
