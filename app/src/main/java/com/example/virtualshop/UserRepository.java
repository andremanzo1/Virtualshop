package com.example.virtualshop;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        try {
            AppDatabase db = AppDatabase.getDatabase(application);
            userDao = db.userDao();
            allUsers = userDao.getAllUsers();
        } catch (ExceptionInInitializerError | RuntimeException e) {
            e.printStackTrace();
            Log.e("UserRepository", "Error initializing UserRepository", e);
            // Handle initialization error
        }
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void getUserByUsernameAndPassword(String username, String password, UserRepositoryCallback callback) {
        new GetUserAsyncTask(userDao, callback).execute(username, password);
    }

    public void getUserByUsername(String username, UserRepositoryCallback callback) {
        new GetUserByUsernameAsyncTask(userDao, callback).execute(username);
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }
    public void deleteUser(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
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

    private static class GetUserByUsernameAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private UserRepositoryCallback callback;

        GetUserByUsernameAsyncTask(UserDao userDao, UserRepositoryCallback callback) {
            this.userDao = userDao;
            this.callback = callback;
        }

        @Override
        protected User doInBackground(String... params) {
            String username = params[0];
            return userDao.getUserByUsername(username);
        }

        @Override
        protected void onPostExecute(User user) {
            if (callback != null) {
                callback.onUserLoaded(user);
            }
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    public interface UserRepositoryCallback {
        void onUserLoaded(User user);
    }
}