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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
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
        lst.forEach(a -> log.trace("Image {}",a));
        assertEquals(lst.size(),service.getAll().size());
    }

    @Test
    @Transactional
    void read() {
        Long id = 7L;
        Image image = service.read(id);
        log.trace("image {}",image);
        assertEquals(service.read(image.getId()).getName(),"Test");
    }

    @Test
    void create(){
        User user = new User();
        Image image = new Image();
        Categorie categorie = new Categorie();

        user.setId(1L);
        user.setName("TestCreateImageUser");
        user.setSurname("TCIU");
        user.setIdentifier("TestCIU");
        user.setPassword("test");
        user.setRole(0L);

        categorie.setId(100L);
        categorie.setName("TestCreateImageCategorie");
        categorie.setImages(Collections.singletonList(image));

        image.setUsers(user);
        image.setTitle("test");
        image.setName("TestCreateImage");
        image.setDescription("DescriptionTestCreateImage");
        image.setCopyright(1L);
        image.setDate(LocalDate.now());
        image.setState(1L);
        image.setLink("TestCreateImage");
        image.setCategories(Collections.singletonList(categorie));

        service.create(image);
        assertEquals(service.read(image.getId()).getName(),"TestCreateImage");
    }

    @Test
    void update() {
        Image image = service.read(7L);

        image.setTitle("testUpdateImage");
        image.setName("TestUpdateImage");
        image.setDescription("DescriptionTestUpdateImage");
        image.setCopyright(0L);
        image.setDate(LocalDate.now());
        image.setState(0L);
        image.setLink("TestUpdateImage");

        service.update(image);
        assertEquals(service.read(image.getId()).getName(),"TestUpdateImage");
    }

    @Test
    void delete() {
        service.delete(8L);
        assertNull(service.read(6L));
    }

    @Test
    void addImage(){
        service.addImage(1L,"test","test", "test",1L,1L, "test.png");
    }

}
