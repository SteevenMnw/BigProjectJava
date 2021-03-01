package Java.Project.BigProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    @JsonIgnoreProperties("Categories")
    private List<Categorie> categories;

    private String Name;
    private String Description;
    private Long Copyright;
    private Date Date;
    private Long State;
    private String Link;
}
