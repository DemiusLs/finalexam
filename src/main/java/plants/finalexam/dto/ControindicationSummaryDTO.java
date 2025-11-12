package plants.finalexam.dto;

public class ControindicationSummaryDTO {
    private Integer id;
    private String name;
    private String description;

    public ControindicationSummaryDTO(Integer id, String name , String description){
        this.id = id;
        this.name = name;
        this.description = description;
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
}
