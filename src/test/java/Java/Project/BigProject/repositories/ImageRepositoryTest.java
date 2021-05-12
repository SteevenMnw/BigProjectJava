package Java.Project.BigProject.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private ImageRepository repository;

    @BeforeEach
    void setUp() {
        log.trace("setUp before All ...");
        assertNotNull(repository,"Image Repository is NOT Inject !!!");
    }
}