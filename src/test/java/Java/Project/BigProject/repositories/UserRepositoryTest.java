package Java.Project.BigProject.repositories;

import Java.Project.BigProject.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"User Repository is NOT Inject !!!");
    }

    @Test
    void findUserByIdentifierEqualsAndPasswordEquals() {
        log.trace("Start findUserByIdentifierEqualsAndPasswordEquals");
        String identifier = "admin";
        String password = "admin";
        var user = repository.findByEmailEqualsAndPasswordEquals(identifier, password);
        assertNotNull(user);
        assertEquals(repository.getOne(user.getId()).getId(), 2);
    }
}
