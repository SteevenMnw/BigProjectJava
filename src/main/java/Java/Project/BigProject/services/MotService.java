package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Mot;

import java.util.List;

public interface MotService {

    List<Mot> getAll();
    Mot create(Mot mot);
    Mot read(Long id);
    Mot update(Mot mot);
    void delete(Long id);
    void addMot(Long id_image, String libelle);
    void addMots(Long id, String[] mot);
}
