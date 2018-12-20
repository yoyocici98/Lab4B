package com.example.taruc.lab4b;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

//TODO 6: Create a repository class to manage data query thread

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        //obtain a copy of database
        AppDatabase db = AppDatabase.getDatabase(application);

        //retrieve the DAO
        userDao = db.userDao();

        //Retrieve all the user records
        allUsers = userDao.loadAllUsers();
    }
    
    LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    
    public void insertUser(User user){
        new insertAsyncTask(userDao).execute(user);
    }

    //<Param, Progress, Results> //the insertion.... must do in asyn class
    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {    //in background trend
        private UserDao userDao;

        public insertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }
}
