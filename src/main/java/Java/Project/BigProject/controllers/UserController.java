package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

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
    public ResponseEntity<User> getUserByEmailAndPassword(@PathParam("identifier") String identifier, @PathParam("password") String password) {
        User user = service.findUserByIdentifierAndPassword(identifier, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/save")
//    public Actor createActor(@RequestBody Actor actor){
//        return service.create(actor);
//    }

}
