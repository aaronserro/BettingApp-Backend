package com.betting.backend.User;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(String Username, String Password, String Bio){
        User user = new User(Username, Password, Bio);
        return userRepository.save(user);


    }
    public void deleteUser(Long ID){
        User user = userRepository.findById(ID)
        .orElseThrow(() -> new UserNotFoundException(ID));
    userRepository.delete(user);


    }
    public User updateUsername(Long ID, String Username){
        User user = userRepository.findById(ID)
        .orElseThrow(() -> new UserNotFoundException(ID));

    user.setUsername(Username);
    userRepository.save(user);
    return user;
    }
    public User updatePassword(Long ID, String Password){
        User user = userRepository.findById(ID)
        .orElseThrow(() -> new UserNotFoundException(ID));

    user.setpassword(Password);
    userRepository.save(user);
    return user;
    }
    public User updateBio(Long ID, String bio) {
        User user = userRepository.findById(ID)
            .orElseThrow(() -> new UserNotFoundException(ID));

        user.setBio(bio);
        userRepository.save(user);
        return user;
    }



}
