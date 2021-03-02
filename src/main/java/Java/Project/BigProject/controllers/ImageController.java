package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final static Logger log = LoggerFactory.getLogger(ImageController.class);

    private ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping({"","/all"})
    public List<Image> getAllImage(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Image getOne(@PathVariable("id") Long id){
        return service.read(id);
    }

//    @PostMapping("/save")
//    public Actor createActor(@RequestBody Actor actor){
//        return service.create(actor);
//    }

}
