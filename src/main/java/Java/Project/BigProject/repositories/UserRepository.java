package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByIdentifierEqualsAndPasswordEquals(String identifier, String password);
}
