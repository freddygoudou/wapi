package entity;

public class Ressource {
    private int id;
    private String path;
    private String name;
    private String type;
    private String formation;
    private String firstImagePath;

    public Ressource(int id, String path, String name, String type, String formation, String firstImagePath) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.type = type;
        this.formation = formation;
        this.firstImagePath = firstImagePath;
    }

    public Ressource(String path, String name, String type, String formation, String firstImagePath) {
        this.path = path;
        this.name = name;
        this.type = type;
        this.formation = formation;
        this.firstImagePath = firstImagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }
}
