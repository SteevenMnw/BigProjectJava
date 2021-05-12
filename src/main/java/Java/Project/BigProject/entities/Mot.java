package Java.Project.BigProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mot")
public class Mot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mot")
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "id_image")
    @JsonIgnoreProperties("images")
    private Image images;

    private String libelle;
}
