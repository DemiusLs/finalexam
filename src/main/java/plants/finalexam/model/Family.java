package plants.finalexam.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name cannot be blank,empty or null")
    private String name;
    @Lob
    private String description;

    @OneToMany(mappedBy = "family",  fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Plant> plants;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Plant> getPlants() {
        return this.plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public String toString(){
        return String.format("%s", name);
    }

}
