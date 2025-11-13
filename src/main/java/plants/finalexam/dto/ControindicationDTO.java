package plants.finalexam.dto;

import java.util.List;

import plants.finalexam.model.Controindication;

public class ControindicationDTO {
    
    private Integer id;
    private String name;
    private String description;
    private List<PlantSummaryDTO> plants;

    public ControindicationDTO(Integer id, String name , String description, List<PlantSummaryDTO> plants){
        this.id = id;
        this.name = name;
        this.description = description;
        this.plants = plants;
    }

    public ControindicationDTO(Controindication controindication){
        this.id = controindication.getId();
        this.name = controindication.getName();
        this.description = controindication.getDescription();
        this.plants = controindication.getPlants().stream()
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
