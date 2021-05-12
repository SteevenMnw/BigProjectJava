package Java.Project.BigProject.controllers;

import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.services.ImageService;
import Java.Project.BigProject.services.UserService;
import Java.Project.BigProject.services.client.ResponseDetection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {
    private final static Logger log = LoggerFactory.getLogger(ImageController.class);

    private ImageService service;

    public UserService userService;

    public ImageController(ImageService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    String linkFile = "D:/Documents/Cours_DEVOPS/java/BigProject/src/main/resources/image/";

    @GetMapping({"","/all"})
    public List<Image> getAllImage(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Image getOne(@PathVariable("id") Long id){
        return service.read(id);
    }

    @PostMapping("/add")
    public void addImage(
            @PathParam("id_user") Long id_user,
            @PathParam("title") String title,
            @PathParam("name") String name,
            @PathParam("description") String description,
            @PathParam("copyright") Long copyright,
            @PathParam("state") Long state,
            @PathParam("link") String link) {
        service.addImage(id_user, title, name, description, copyright, state, link);
    }

    @GetMapping("/state/all")
    public List<Image> getImageByState() {
        return service.getImageByState();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@PathParam("id_user") Long id_user, @PathParam("title") String title, @PathParam("description") String description, @PathParam("copyright") Long copyright, @RequestPart MultipartFile file) throws Exception {

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        Image image = new Image();

        image.setTitle(title);
        image.setName("");
        image.setUsers(userService.read(id_user));
        image.setDescription(description);
        image.setCopyright(copyright);
        image.setState(1L);
        image.setLink("null");

        service.create(image);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = uuidAsString + extension;

        File convertFile = new File(linkFile + name);
        convertFile.createNewFile();
        FileOutputStream fs = new FileOutputStream(convertFile);
        fs.write(file.getBytes());
        fs.close();

        Image newImage = service.read(image.getId());

        newImage.setName(name);
        newImage.setLink("http://localhost:8000/images/picture/" + name);
        service.update(newImage);

        return ResponseEntity.ok().body(newImage);
    }


    @GetMapping(value="/analyse/{id}")
    public ResponseDetection analyseImage(@PathVariable("id") Long id) throws Exception {
        Image image = service.read(id);
        return service.urlSignImage(image.getName());
    }

    @PutMapping(value="/update")
    public Image updateImage(Image image) {
        return service.update(image);
    }

    @RequestMapping(value = "/picture/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        File file = new File(linkFile + name);

        byte[] data = Files.readAllBytes(file.toPath());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
    }

    @PutMapping(value="/addCategorie/{id}")
    public void addCategorieForImage(@PathVariable("id") Long id_image, @PathParam("categories") String[] categories) {
        service.addCategorieForImage(id_image, categories);
    }

    @PutMapping(value="/date/{id}")
    public Image updateDate(@PathVariable("id") Long id, @PathParam("date") String date){
        Image image = service.read(id);
        image.setDate(LocalDate.parse(date));
        return service.update(image);
    }

    @PutMapping(value="/state/{id}")
    public Image updatePublication(@PathVariable("id") Long id, @PathParam("state") Long state) {
        Image image = service.read(id);
        image.setState(state);
        return service.update(image);
    }
}
