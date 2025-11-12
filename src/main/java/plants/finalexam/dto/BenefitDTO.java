package plants.finalexam.dto;

import java.util.List;

import plants.finalexam.model.Benefit;

public class BenefitDTO {
    private Integer id;
    private String name;
    private String description;
    private List<PlantSummaryDTO> plants;

    public BenefitDTO(Integer id, String name , String description, List<PlantSummaryDTO> plants){
        this.id = id;
        this.name = name;
        this.description = description;
        this.plants = plants;
    }

    public BenefitDTO(Benefit benefit){
        this.id = benefit.getId();
        this.name = benefit.getName();
        this.description = benefit.getDescription();
        this.plants = benefit.getPlants().stream()
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
