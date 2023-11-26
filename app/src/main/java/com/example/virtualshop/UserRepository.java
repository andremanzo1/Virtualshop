package com.example.virtualshop;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        try {
            AppDatabase db = AppDatabase.getDatabase(application);
            userDao = db.userDao();
        } catch (ExceptionInInitializerError | RuntimeException e) {
            e.printStackTrace();
            Log.e("UserRepository", "Error initializing UserRepository", e);
            // Handle initialization error
        }
    }

    public void getUserByUsernameAndPassword(String username, String password, UserRepositoryCallback callback) {
        new GetUserAsyncTask(userDao, callback).execute(username, password);
    }

    private static class GetUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private UserRepositoryCallback callback;

        GetUserAsyncTask(UserDao userDao, UserRepositoryCallback callback) {
            this.userDao = userDao;
            this.callback = callback;
        }

        @Override
        protected User doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return userDao.getUserByUsernameAndPassword(username, password);
        }

        @Override
        protected void onPostExecute(User user) {
            if (callback != null) {
                callback.onUserLoaded(user);
            }
        }
    }

    public interface UserRepositoryCallback {
        void onUserLoaded(User user);

        // Add this method
        // Your existing logic can be included here
        // For example:
        // Log.d("UserRepository", "User loaded: " + user);
        // // Your existing logic
    }
}