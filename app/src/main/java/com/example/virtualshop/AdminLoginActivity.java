package com.example.virtualshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        EditText adminUsernameEditText = findViewById(R.id.adminUsernameEditText);
        EditText adminPasswordEditText = findViewById(R.id.adminPasswordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered admin credentials
                String enteredAdminUsername = adminUsernameEditText.getText().toString();
                String enteredAdminPassword = adminPasswordEditText.getText().toString();

                // Check if the entered credentials are for admin2
                if (enteredAdminUsername.equals("admin2") && enteredAdminPassword.equals("admin2")) {
                    // Admin2 login successful
                    Intent adminIntent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    startActivity(adminIntent);
                    finish(); // Optional: Close the admin login activity
                } else {
                    // Invalid credentials, show a message
                    Toast.makeText(AdminLoginActivity.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
