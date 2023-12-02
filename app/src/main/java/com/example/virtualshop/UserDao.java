package com.example.virtualshop;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);
    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
    @Insert
    void insert(User user);
    @Delete
    void delete(User user);
}