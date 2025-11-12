package plants.finalexam.dto;

import java.util.List;

import plants.finalexam.model.Family;

public class FamilyDTO {
    private Integer id;
    private String name;
    private String description;
    private List<PlantSummaryDTO> plants;

    public FamilyDTO(Integer id, String name , String description, List<PlantSummaryDTO> plants){
        this.id = id;
        this.name = name;
        this.description = description;
        this.plants = plants;
    }

    public FamilyDTO(Family family){
        this.id = family.getId();
        this.name = family.getName();
        this.description = family.getDescription();
        this.plants = family.getPlants().stream()
        .map(p -> new PlantSummaryDTO(p))
        .toList();
    }



    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<PlantSummaryDTO> getPlants() {
        return this.plants;
    }
}
