package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User newUser=userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") int id){
        User availUser= userService.getUser(id);
        return new ResponseEntity<User>(availUser,HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
        List<User> userList=userService.getAllUsers();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    //Is not working
    @GetMapping("/getUserByVacCenter/{vacId}")
    public ResponseEntity<?> getUserByVacCenter(@PathVariable int vacId){
        List<User> users=userService.getUsersByVacCenter(vacId);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user){
        User updatedUser=userService.updateUser(userId,user);
        return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
