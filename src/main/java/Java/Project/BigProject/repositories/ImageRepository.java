package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getByStateEquals(Long state);
}
