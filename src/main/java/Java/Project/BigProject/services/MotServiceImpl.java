package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Mot;
import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.repositories.MotRepository;
import Java.Project.BigProject.repositories.MotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MotServiceImpl implements  MotService{

    private final static Logger log = LoggerFactory.getLogger(MotServiceImpl.class);

    private final MotRepository repository;

    private ImageService imageService;

    @Autowired
    public void setMissionService(ImageService missionService) {
        this.imageService = missionService;
    }

    public MotServiceImpl(MotRepository repository) {
        log.trace("MotServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<Mot> getAll() {
        return repository.findAll();
    }

    @Override
    public Mot create(Mot mot) {
        return repository.save(mot);
    }

    @Override
    public Mot read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Mot update(Mot mot) {
        return repository.saveAndFlush(mot);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void addMot(Long id_image, String libelle ){
        Mot mot = new Mot();
        Image image = new Image();

        image.setId(id_image);
        mot.setImages(image);
        mot.setLibelle(libelle);
        create(mot);
    }

    public void addMots(Long id, String[] mot) {

        for (String libelleMotcle : mot) {
            addMot(id, libelleMotcle);
        }
    }
}
