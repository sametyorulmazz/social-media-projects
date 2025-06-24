package com.example.questapp_project.controllers;

import com.example.questapp_project.entities.User;
import com.example.questapp_project.services.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.questapp_project.repos.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController
{
    //private UserRepository userRepository;
    private UserService userService;

    public UserController(UserService userService){
        //this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        //return userRepository.findAll();
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        //return userRepository.save(newUser);
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //return userRepository.findById(userId).orElse(null);
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        /*
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else return null;
        */
        return userService.updateOneUser(userId,newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        //userRepository.deleteById(userId);
        userService.deleteById(userId);
    }

}
