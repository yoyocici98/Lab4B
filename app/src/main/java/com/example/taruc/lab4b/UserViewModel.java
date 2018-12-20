package com.example.taruc.lab4b;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

//TODO 7: Create an Android View Model class to link data to UI

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> allUsers; // A cache copy of record

    public UserViewModel(@NonNull Application application) {
        super(application);
        //create an instance of repository
        userRepository = new UserRepository(application);
        // retrieve all the user records
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public  void insertUser(User user){
        userRepository.insertUser(user); //call repository as an interface and do insertion
    }
}
