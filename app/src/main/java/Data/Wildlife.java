package Data;

public class Wildlife {
    private String name;
    private String scientificName;
    private String habitat;
    private String description;

    public Wildlife(String name, String scientificName, String habitat, String description) {
        this.name = name;
        this.scientificName = scientificName;
        this.habitat = habitat;
        this.description = description;
    }

    // Getters and Setters for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
