package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.Categorie;
import Java.Project.BigProject.services.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategorieController {
    private final static Logger log = LoggerFactory.getLogger(CategorieController.class);

    private CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping({"","/all"})
    public List<Categorie> getAllCategorie(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getOne(@PathVariable("id") Long id){
        return service.read(id);
    }

    @PostMapping("/add")
    public void addCategorie(
            @PathParam("name") String name) {
        service.addCategorie(name);
    }

}
