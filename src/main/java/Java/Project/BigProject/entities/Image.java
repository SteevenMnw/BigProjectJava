package Java.Project.BigProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("images")
    private User users;

    @ManyToMany
    @JoinTable(name="avoir",
            joinColumns={@JoinColumn(name="id_image")},
            inverseJoinColumns={@JoinColumn(name="id_cat")})
    @JsonIgnoreProperties("images")
    @ToString.Exclude
    private List<Categorie> categories;

    private String name;
    private String description;
    private Long copyright;
    private LocalDateTime date;
    private Long state;
    private String link;
}
