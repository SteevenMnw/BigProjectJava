package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie getByName(String name);
}
