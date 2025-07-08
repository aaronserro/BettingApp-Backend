package com.betting.backend.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder password)
     {
        this.userRepository = userRepository;
        this.passwordEncoder=password;
    }
    public User getUserbyID(Long ID){
            User user = userRepository.findById(ID)
        .orElseThrow(() -> new UserNotFoundException(ID));
        return user;
    }
    public User authenticateUser(String username, String rawPassword) {
            User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Invalid username or password"));

    if (!passwordEncoder.matches(rawPassword, user.getpassword())) {
        throw new RuntimeException("Invalid username or password");
    }

    return user;
}
    public User createUser(String username, String password, String bio, String heardFrom, String niche, String university) {

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, bio, heardFrom, niche, university);
        userRepository.save(user);
        return user;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
