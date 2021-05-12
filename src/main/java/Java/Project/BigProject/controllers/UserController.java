package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"","/all"})
    public List<User> getAllUser(){
        return service.getAll();
    }

    @GetMapping("/identification")
    public ResponseEntity<User> getUserByEmailAndPassword(@PathParam("email") String email, @PathParam("password") String password) {
        User user = service.findUserByEmailAndPassword(email, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public void addUser(
            @PathParam("name") String name,
            @PathParam("surname") String surname,
            @PathParam("identifier") String identifier,
            @PathParam("password") String password,
            @PathParam("email") String email) {
        service.addUser(name, surname, identifier, password, email);
    }
}
