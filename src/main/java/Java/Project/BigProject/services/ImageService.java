package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.services.client.ResponseDetection;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ImageService {

    List<Image> getAll();
    Image create(Image image);
    Image read(Long id);
    Image update(Image image);
    void delete(Long id);
    void addImage(Long id_user, String title, String name,  String description, Long copyright, Long state, String link);
    void addCategorieForImage(Long id_image, String[] categories);
    ResponseDetection urlSignImage(String nameImage) throws Exception;
    List<Image> getImageByState();
}
