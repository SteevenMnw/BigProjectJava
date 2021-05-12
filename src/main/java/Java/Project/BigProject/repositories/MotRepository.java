package Java.Project.BigProject.repositories;

import Java.Project.BigProject.entities.Mot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotRepository extends JpaRepository<Mot, Long> {
    Mot getByLibelle(String Libelle);
}
