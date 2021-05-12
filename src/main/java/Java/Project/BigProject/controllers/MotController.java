package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.Mot;
import Java.Project.BigProject.services.MotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mots")
public class MotController {
    private final static Logger log = LoggerFactory.getLogger(MotController.class);

    private MotService service;

    public MotController(MotService service) {
        this.service = service;
    }

    @GetMapping({"","/all"})
    public List<Mot> getAllMot(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mot getOne(@PathVariable("id") Long id){
        return service.read(id);
    }

    @PostMapping("/add")
    public void addMot(
            @PathParam("id_image") Long id_image,
            @PathParam("libelle") String libelle) {
        service.addMot(id_image, libelle);
    }

    @PutMapping(value="/add/{id}")
    public void addMots(@PathVariable("id") Long id, @PathParam("mot") String[] mot) {
        service.addMots(id, mot);
    }
}
