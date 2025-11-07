package plants.finalexam.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "plant")
public class Plant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Scientific name cannot be blank,empty or null")
    private String scientificName ;
    @NotBlank(message = "Common name cannot be blank,empty or null")
    private String commonName;
    @NotBlank(message = "Family name cannot be blank,empty or null")
    
    private String imageUrl;
    
    private boolean toxic = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benefit_plant",
                joinColumns = @JoinColumn(name="plant_id"),
                inverseJoinColumns = @JoinColumn(name="benefit_id"))
    private Set<Benefit> benefits;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "controindication_plant",
                joinColumns = @JoinColumn(name="plant_id"),
                inverseJoinColumns = @JoinColumn(name="controindication_id"))
    private Set<Controindication> controindications;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScientificName() {
        return this.scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isToxic() {
        return this.toxic;
    }

    public boolean getToxic() {
        return this.toxic;
    }

    public void setToxic(boolean toxic) {
        this.toxic = toxic;
    }

    public Set<Benefit> getBenefits() {
        return this.benefits;
    }

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }

    public Set<Controindication> getControindications() {
        return this.controindications;
    }

    public void setControindications(Set<Controindication> controindications) {
        this.controindications = controindications;
    }


    public Family getFamily() {
        return this.family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }


    
}
