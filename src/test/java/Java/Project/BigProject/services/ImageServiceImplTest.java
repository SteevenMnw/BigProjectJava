package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Categorie;
import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(ImageServiceImplTest.class);

    @Autowired
    private ImageService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service Image is Injected ...");
        assertNotNull(service, "ERROR Service Image NOT Injected !!!");
        log.trace("Service Image Injected");
    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}",a));
        log.trace("Total number of Image :{}",lst.size());
        assertEquals(lst.size(),service.getAll().size());
    }

    @Test
    @Transactional
    void read() {
        Long id = 1L;
        Image image = service.read(id);
        var lst = image.getCategories();
        lst.forEach(f -> log.trace("{}",f));
        log.trace("Number of Image for a Categorie :{}, {}",image,lst.size());
        assertEquals(image.getId(),id);
    }

    @Test
    void create(){
        Long idu = 1L;
        String ni = "TEST";
        String di = "description TEST";
        Long ci = 1L;
        LocalDateTime datei = LocalDateTime.now();
        Long li = 1L;
        Image image = new Image();
        image.setUsers(User.class.);
        service.create(image);
        log.trace("Create Categorie :{}",image);
        assertEquals(service.read(image.getId()).getName(),"Test");
    }

    @Test
    void update(){
        Categorie categorie = service.read(4L);
        categorie.setName("Maison");
        service.update(categorie);
        log.trace("Update Categorie : {}", categorie);
        assertEquals(categorie.getName(),"Maison");
    }

    @Test
    void delete() {
        service.delete(6L);
        assertNull(service.read(6L));
    }
}
