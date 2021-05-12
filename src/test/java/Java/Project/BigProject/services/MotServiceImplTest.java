package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.entities.Mot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MotServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(MotServiceImplTest.class);

    @Autowired
    private MotService service;

    @Autowired
    public ImageService imageService;

    @BeforeEach
    void setUp() {
        log.trace("Check if service Mot is Injected ...");
        assertNotNull(service, "ERROR Service Mot NOT Injected !!!");
        log.trace("Service Mot Injected");
    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}",a));
        assertEquals(lst.size(),service.getAll().size());
    }

    @Test
    @Transactional
    void read() {
        Long id = 1L;
        Mot mot = service.read(id);
        assertEquals(service.read(mot.getId()).getLibelle(),"test");
    }

    @Test
    void create(){
        String nc = "testt" ;
        Mot mot = new Mot();

        mot.setImages(imageService.read(1L));
        mot.setLibelle(nc);
        service.create(mot);
        assertEquals(service.read(mot.getId()).getLibelle(),"testt");
    }

    @Test
    void update(){
        Mot mot = service.read(4L);
        Image image = new Image();

        image.setId(4L);
        mot.setImages(image);
        mot.setLibelle("table");
        service.update(mot);
        assertEquals(service.read(mot.getId()).getLibelle(),"table");
    }

    @Test
    void delete() {
        service.delete(6L);
        assertNull(service.read(6L));
    }

    @Test
    void addMot(){
        service.addMot(1L,"chaise");
    }
}
