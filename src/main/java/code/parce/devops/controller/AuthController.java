package code.parce.devops.controller;

import code.parce.devops.model.User;
import code.parce.devops.service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Value("${app.url}")
    private String url;

    @Value("${app.test}")
    private String test;

    @Value("${app.profile}")
    private String profile;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String envs = "URL: "+ url +" - Test: " + test + " - profile : " + profile;
        if ("admin@gmail.com".equals(user.getEmail()) &&
            "123456".equals(user.getPassword())) {
            return ResponseEntity.ok("Login exitoso { "+envs +" }");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales inválidas");
    }

    // SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

    // LIST USERS
    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
