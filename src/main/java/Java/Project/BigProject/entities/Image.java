package Java.Project.BigProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinTable(name = "avoir",
            joinColumns = @JoinColumn(name = "id_image"),
            inverseJoinColumns = @JoinColumn(name = "id_cat"))
    @ToString.Exclude
    @JsonIgnoreProperties("images")
    private List<Categorie> categories;

    @OneToMany(mappedBy = "images")
    @JsonIgnoreProperties("images")
    @ToString.Exclude
    private List<Mot> mots;

    private String title;
    private String name;

    @Nullable
    private String description;

    private Long copyright;

    @Nullable
    private LocalDate date;

    private Long state;
    private String link;
}
