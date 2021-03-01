package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
