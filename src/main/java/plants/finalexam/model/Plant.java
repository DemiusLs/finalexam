package plants.finalexam.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "plant")
public class Plant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Scientific name cannot be blank,empty or null")
    private String scientific_name ;
    @NotBlank(message = "Common name cannot be blank,empty or null")
    private String common_name;
    @NotBlank(message = "Family name cannot be blank,empty or null")
    private String family;
    private String image_url;
    @NotBlank(message = "Toxicity cannot be blank,empty or null")
    private boolean toxic;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benefit_plant",
                joinColumns = @JoinColumn(name="plant_id"),
                inverseJoinColumns = @JoinColumn(name="benefit_id"))
    private Set<Benefit> benefits;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "controindication_plant",
                joinColumns = @JoinColumn(name="plant_id"),
                inverseJoinColumns = @JoinColumn(name="controindication_id"))
    private Set<Benefit> controindications;


}
