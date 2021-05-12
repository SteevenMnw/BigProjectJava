package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailEqualsAndPasswordEquals(String email, String password);
}
