package com.betting.backend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
        @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public void createUser(@RequestParam String username,@RequestParam String password, @RequestParam String Bio){
        userService.createUser(username, password, Bio);

    }
    @DeleteMapping
    public void deleteUser(@RequestParam Long ID){
        userService.deleteUser(ID);

    }
    @PatchMapping
    public void updateUser(@RequestParam Long ID,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                            @RequestParam(required = false) String Bio){
                                if (username != null) {
                                    userService.updateUsername(ID, username);
                                }
                                if (password != null) {
                                    userService.updatePassword(ID, password);
                                }
                                if (Bio != null) {
                                    userService.updateBio(ID,Bio);
                                }

    }


}
