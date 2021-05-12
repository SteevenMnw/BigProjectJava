package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService service;

    @BeforeEach
    void setUp() {
        log.trace("Check if service User is Injected ...");
        assertNotNull(service, "ERROR Service User NOT Injected !!!");
        log.trace("Service User Injected");
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
        User user = service.read(id);
        assertEquals(service.read(user.getId()).getName(),"Dupont");
    }

    @Test
    void create(){
        User user = new User();

        user.setName("TestCreateUser");
        user.setSurname("TestCreateUser");
        user.setIdentifier("TCU");
        user.setPassword("tcu");
        user.setEmail("tcu@email.com");
        user.setRole(0L);

        service.create(user);
        assertEquals(service.read(user.getId()).getName(),"TestCreateUser");
    }

    @Test
    void update(){
        User user = service.read(5L);

        user.setName("TestUpdateUser");
        user.setSurname("TestUpdateUser");
        user.setIdentifier("TUU");
        user.setPassword("tuu");
        user.setEmail("tuu@email.com");
        user.setRole(0L);

        service.update(user);
        assertEquals(service.read(user.getId()).getName(),"TestUpdateUser");
    }

    @Test
    void delete() {
        service.delete(5L);
        assertNull(service.read(5L));
    }

    @Test
    void findUserByEmailAndPassword() {
        String mail = "TestUpdate@gmail.com";
        String password = "TestPassword";
        User user = service.findUserByEmailAndPassword(mail, password);
        log.trace("User : {}", user);
    }

    @Test
    void addUser(){
        service.addUser("test","test","test","test","test");
    }
}
