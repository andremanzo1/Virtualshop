package com.example.virtualshop;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAccountsActivity extends AppCompatActivity implements UserListAdapter.OnDeleteButtonClickListener {
    private UserViewModel userViewModel;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);

        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe changes to the list of users
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Update the UI with the new list of users
                adapter.setUsers(users);
            }
        });
    }

    @Override
    public void onDeleteButtonClick(User user) {
        // Implement the logic to delete the user from the database
        // Update your UserRepository or ViewModel to handle user deletion
        // For example:
        userViewModel.deleteUser(user);
    }
}