package com.example.virtualshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private List<User> users = new ArrayList<>();
    private OnDeleteButtonClickListener onDeleteButtonClickListener;

    public UserListAdapter(OnDeleteButtonClickListener onDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = users.get(position);
        holder.usernameTextView.setText(currentUser.getUsername());

        // Set click listener for delete button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete button click
                onDeleteButtonClickListener.onDeleteButtonClick(currentUser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        Button deleteButton;

        UserViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(User user);
    }
}