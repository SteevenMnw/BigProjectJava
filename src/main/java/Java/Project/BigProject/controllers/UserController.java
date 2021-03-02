package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.Categorie;
import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.services.CategorieService;
import Java.Project.BigProject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public User getOne(@PathVariable("id") Long id){
        return service.read(id);
    }

//    @PostMapping("/save")
//    public Actor createActor(@RequestBody Actor actor){
//        return service.create(actor);
//    }

}
