package com.example.virtualshop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);
    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);
    @Insert
    void insert(User user);

}