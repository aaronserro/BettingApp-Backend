package com.betting.backend.auth;

import com.betting.backend.User.User;
import com.betting.backend.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController//tells Springboot that this class handles Restful web Requests and returns valus in the form of JSON
/**
 * This class exposes a single endpoint /auth/login
 * is authenticates the user using the UserService class
 * Generates a JWT token using the jwtUtil class
 * Returns a token to the frontend for future Auth
 *
 *
 *
 *
 */
@RequestMapping("/auth")//all endpoints will begin with /auth prefix
public class AuthController {


    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        /**
         * this class injects the nessessary dependencies UserService and JwlUtil
         *
         */
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")//This desinds the endpoint and expects a request body
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        /**
         * This class expects a Json body with username and bassword
         * it is successful if it generates a token, wraps it in an auth response object and returns it to the client
         *
         */

  try {
    User user = userService.authenticateUser(request.getUsername(), request.getPassword());
    String token = jwtUtil.generateToken(user.getUsername());
    return ResponseEntity.ok(new AuthResponse(token));
  } catch (RuntimeException ex) {
    return ResponseEntity.status(401).body(java.util.Map.of("error", "Invalid username or password"));
  }
    }
}
