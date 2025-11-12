package plants.finalexam.dto;

import plants.finalexam.model.Plant;

public class PlantSummaryDTO {
    
    private Integer id;
    private String scientificName ;
    private String commonName;
    private String imageUrl;
    private String description;
    private String habitat;
    private boolean toxic ;
    private String family;


    public PlantSummaryDTO( Integer id,String scientificName ,String commonName, String imageUrl, String description,String habitat,
     boolean toxic ,String family){

        this.id = id;
        this.scientificName = scientificName ;
        this.commonName = commonName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.habitat = habitat;
        this.toxic = toxic;
        this.family = family;

     }
    public PlantSummaryDTO(Plant plant) {
        this.id = plant.getId();
        this.scientificName = plant.getScientificName();
        this.commonName = plant.getCommonName();
        this.imageUrl = plant.getImageUrl();
        this.description = plant.getDescription();
        this.habitat = plant.getHabitat();
        this.toxic = plant.isToxic();
        this.family = plant.getFamily() != null ? plant.getFamily().getName() : null;
    }


    public Integer getId() {
        return this.id;
    }

    public String getScientificName() {
        return this.scientificName;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHabitat() {
        return this.habitat;
    }

    public boolean getToxic() {
        return this.toxic;
    }

    public String getFamily() {
        return this.family;
    }


}
