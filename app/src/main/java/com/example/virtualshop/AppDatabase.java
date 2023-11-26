package com.example.virtualshop;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();

                    // Insert predefined users during database creation
                    insertPredefinedUsers(INSTANCE);
                }
            }
        }
        return INSTANCE;
    }

    private static void insertPredefinedUsers(final AppDatabase db) {
        try {
            User existingUser = db.userDao().getUserByUsername("testuser1");
            if (existingUser == null) {
                User user1 = new User();
                user1.username = "testuser1";
                user1.password = "testuser1";
                user1.isAdmin = false;

                User user2 = new User();
                user2.username = "admin2";
                user2.password = "admin2";
                user2.isAdmin = true;

                db.userDao().insert(user1);
                db.userDao().insert(user2);

                Log.d("AppDatabase", "Predefined users inserted successfully");
            } else {
                Log.d("AppDatabase", "Predefined users already exist");
            }
        } catch (Exception e) {
            Log.e("AppDatabase", "Error inserting predefined users", e);
        }
    }
}
