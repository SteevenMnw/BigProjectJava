package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Image;

import java.util.List;

public interface ImageService {

    List<Image> getAll();
    Image create(Image image);
    Image read(Long id);
    Image update(Image image);
    void delete(Long id);
}
