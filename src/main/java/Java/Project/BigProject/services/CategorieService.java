package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Categorie;

import java.util.List;

public interface CategorieService {

    List<Categorie> getAll();
    Categorie create(Categorie categorie);
    Categorie read(Long id);
    Categorie update(Categorie categorie);
    void delete(Long id);
}
