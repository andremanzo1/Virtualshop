package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        Button userButton = findViewById(R.id.userButton);
        Button adminButton = findViewById(R.id.adminButton);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle user button click
                startActivity(new Intent(LandingPageActivity.this, LoginActivity.class));
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle admin button click
                startActivity(new Intent(LandingPageActivity.this, AdminLoginActivity.class));
            }
        });
    }
}