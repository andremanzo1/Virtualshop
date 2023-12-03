package com.example.virtualshop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Product.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    private static volatile AppDatabase INSTANCE;

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertPredefinedUsersAsyncTask(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class InsertPredefinedUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        InsertPredefinedUsersAsyncTask(AppDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            insertPredefinedUsers(userDao);
            return null;
        }
    }

    private static void insertPredefinedUsers(UserDao userDao) {
        try {
            User existingUser = userDao.getUserByUsername("testuser1");
            if (existingUser == null) {
                User user1 = new User();
                user1.username = "testuser1";
                user1.password = "testuser1";
                user1.isAdmin = false;

                User user2 = new User();
                user2.username = "admin2";
                user2.password = "admin2";
                user2.isAdmin = true;

                userDao.insert(user1);
                userDao.insert(user2);

                Log.d("AppDatabase", "Predefined users inserted successfully");
            } else {
                Log.d("AppDatabase", "Predefined users already exist");
            }
        } catch (Exception e) {
            Log.e("AppDatabase", "Error inserting predefined users", e);
        }
    }
}