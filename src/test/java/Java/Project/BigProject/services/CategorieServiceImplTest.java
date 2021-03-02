package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Categorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategorieServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(CategorieServiceImplTest.class);

    @Autowired
    private CategorieService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service Categorie is Injected ...");
        assertNotNull(service, "ERROR Service Categorie NOT Injected !!!");
        log.trace("Service Categorie Injected");
    }

    @Test
    void getAll() {
        var lst = service.getAll();
        lst.forEach(a -> log.trace("{}",a));
        log.trace("Total number of Categorie :{}",lst.size());
        assertEquals(lst.size(),service.getAll().size());
    }

    @Test
    @Transactional
    void read() {
        Long id = 1L;
        Categorie categorie = service.read(id);
        log.trace("Information of one Categorie :{}",categorie);
        assertEquals(service.read(categorie.getId()).getName(),"Paysage");
    }

    @Test
    void create(){
        String nc = "Test" ;
        Categorie categorie = new Categorie();
        categorie.setName(nc);
        service.create(categorie);
        log.trace("Create Categorie :{}",categorie);
        assertEquals(service.read(categorie.getId()).getName(),"Test");
    }

    @Test
    void update(){
        Categorie categorie = service.read(4L);
        categorie.setName("Maison");
        service.update(categorie);
        log.trace("Update Categorie : {}", categorie);
        assertEquals(service.read(categorie.getId()).getName(),"Maison");
    }

    @Test
    void delete() {
        service.delete(4L);
        assertNull(service.read(4L));
    }
}
