package code.parce.devops.controller;

import code.parce.devops.model.User;
import code.parce.devops.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
        logger.info("Petición recibida en /api/login para el usuario: {}", user.getEmail());
        String envs = "URL: "+ url +" - Test: " + test + " - profile : " + profile;
        if ("admin@gmail.com".equals(user.getEmail()) &&
            "123456".equals(user.getPassword())) {
            logger.info("Login exitoso para el usuario: {}", user.getEmail());
            return ResponseEntity.ok("Login exitoso { "+envs +" }");
        }

        logger.warn("Login fallido para el usuario: {}. Credenciales inválidas.", user.getEmail());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales inválidas");
    }

    // SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        logger.info("Petición recibida en /api/signup para el usuario: {}", user.getEmail());
        userService.addUser(user);
        logger.info("Usuario registrado exitosamente: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

    // LIST USERS
    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        logger.info("Petición recibida en /api/users para listar usuarios");
        List<User> users = userService.getUsers();
        logger.info("Retornando {} usuario(s) registrado(s)", users.size());
        return ResponseEntity.ok(users);
    }
}
