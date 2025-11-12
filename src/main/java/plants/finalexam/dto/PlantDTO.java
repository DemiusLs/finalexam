package plants.finalexam.dto;

import java.util.List;

import plants.finalexam.model.Plant;

public class PlantDTO {
    
    private Integer id;
    private String scientificName ;
    private String commonName;
    private String imageUrl;
    private String description;
    private String habitat;
    private boolean toxic ;
    private String family;
    private List<BenefitSummaryDTO> benefits;
    private List<ControindicationSummaryDTO> controindications;


    public PlantDTO( Integer id,String scientificName ,String commonName, String imageUrl, String description,String habitat,
     boolean toxic ,String family,List<BenefitSummaryDTO> benefits, List<ControindicationSummaryDTO> controindications){

        this.id = id;
        this.scientificName = scientificName ;
        this.commonName = commonName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.habitat = habitat;
        this.toxic = toxic;
        this.family = family;
        this.benefits = benefits;
        this.controindications = controindications;
     }
     
    public PlantDTO(Plant plant) {
        this.id = plant.getId();
        this.scientificName = plant.getScientificName();
        this.commonName = plant.getCommonName();
        this.imageUrl = plant.getImageUrl();
        this.description = plant.getDescription();
        this.habitat = plant.getHabitat();
        this.toxic = plant.isToxic();
        this.family = plant.getFamily() != null ? plant.getFamily().getName() : null;

        this.benefits = plant.getBenefits().stream()
            .map(b -> new BenefitSummaryDTO(b.getId(), b.getName(), b.getDescription()))
            .toList();

        this.controindications = plant.getControindications().stream()
            .map(c -> new ControindicationSummaryDTO(c.getId(), c.getName(), c.getDescription()))
            .toList();
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

    public List<BenefitSummaryDTO> getBenefits() {
        return this.benefits;
    }

    public List<ControindicationSummaryDTO> getControindications() {
        return this.controindications;
    }
    

}
