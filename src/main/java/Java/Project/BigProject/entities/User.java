package Java.Project.BigProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id ;

    @OneToMany(mappedBy = "users")
    @JsonIgnoreProperties("users")
    @ToString.Exclude
    private List<Image> images;

    private String Name;
    private String Surname;
    private String Identifier;
    private String Password;
    private Long Role;
}
