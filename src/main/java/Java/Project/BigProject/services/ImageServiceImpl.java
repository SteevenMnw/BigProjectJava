package Java.Project.BigProject.services;

import Java.Project.BigProject.entities.Categorie;
import Java.Project.BigProject.entities.Image;
import Java.Project.BigProject.entities.Mot;
import Java.Project.BigProject.entities.User;
import Java.Project.BigProject.repositories.ImageRepository;
import Java.Project.BigProject.services.client.FileNameDTO;
import Java.Project.BigProject.services.client.ResponseDetection;
import Java.Project.BigProject.services.client.SignUrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    private final static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository repository;

    private CategorieService categorieService;

    @Autowired
    public void setMissionService(CategorieService missionService) {
        this.categorieService = missionService;
    }

    public ImageServiceImpl(ImageRepository repository) {
        log.trace("ImageServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<Image> getAll() {
        return repository.findAll();
    }

    @Override
    public Image create(Image image) {
        return repository.save(image);
    }

    @Override
    public Image read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Image update(Image image) {
        return repository.saveAndFlush(image);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void addImage(Long id_user, String name, String title, String description, Long copyright, Long state, String link){
        Image image = new Image();
        User user = new User();

        user.setId(id_user);
        image.setUsers(user);

        image.setTitle(title);
        image.setName(name);
        image.setDescription(description);
        image.setCopyright(copyright);
        image.setDate(LocalDate.now());
        image.setState(state);
        image.setLink(link);
        create(image);
    }

    public void addCategorieForImage(Long id_image, String[] categories) {
        Image image = read(id_image);
        var listCategories = image.getCategories();

        for (String name : categories) {
            if(categorieService.getOneCategoryByName(name) != null) {
                listCategories.add(categorieService.getOneCategoryByName(name));
            }
            else {
                Categorie categorie = new Categorie();
                categorie.setName(name);
                var categorieCreated = categorieService.create(categorie);
                listCategories.add(categorieCreated);
            }
        }

        image.setCategories(listCategories);

        update(image);
    }

    public ResponseDetection urlSignImage(String nameImage) throws Exception {

        WebClient client = WebClient.builder().baseUrl("https://uf8m8gb2k0.execute-api.eu-central-1.amazonaws.com").defaultHeader("x-api-key","meRxq0l2cE5SQfNNrfnKUalbs0VIRkIA5KFI8hSF").build();
        var filename = new FileNameDTO();
        filename.setFilename(nameImage);

        var result = client.post().uri("/epsi/generate-signed-url").bodyValue(filename).retrieve().bodyToMono(SignUrlResponse.class);
        var response = result.block();
        var signedUrl = response.getSignedUrl();
        var fileName = response.getFilename();

        File file = new File("D:/Documents/Cours_DEVOPS/java/BigProject/src/main/resources/image/" + nameImage);

        byte[] data = Files.readAllBytes(file.toPath());

        var urlFactory = new DefaultUriBuilderFactory(signedUrl);
        urlFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        var uploadResponse = WebClient.builder()
                .uriBuilderFactory(urlFactory)
                .build()
                .put()
                .contentType(MediaType.parseMediaType("image/png"))
                .bodyValue(data)
                .retrieve()
                .toBodilessEntity()
                .block();

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/epsi/detect-labels/{url}").build(fileName))
                .retrieve()
                .bodyToMono(ResponseDetection.class)
                .block();
    }

    public List<Image> getImageByState() {
        return repository.getByStateEquals(Long.parseLong("1", 10));
    }
}
