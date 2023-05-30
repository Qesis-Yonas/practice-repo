package com.user.service;


import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        User newUser=userRepository.save(user);
        return newUser;
    }

    public User getUser(int id) {
        Optional<User> availUser = userRepository.findById(id);
        return availUser.get();
    }

    public List<User> getUsersByVacCenter(int vacId) {
//        List<User> users= userRepository.getUsersByVacCenterId(vacId);
        List<User> users= userRepository.findByVaccinationCenterId(vacId);
        return users;
    }

    public User updateUser(int userId, User user) {
        Optional<User> userForUpdate =userRepository.findById(userId);
        userForUpdate.get().setUserName(user.getUserName());
        userForUpdate.get().setVaccinationCenterId(user.getVaccinationCenterId());
        userRepository.save(user);
        return userForUpdate.get();
    }

    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    public void deleteUser(int userId) {
        Optional<User>user=userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
        else return;
    }
}
