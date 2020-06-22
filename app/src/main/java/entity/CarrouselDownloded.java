package entity;

public class CarrouselDownloded {

    private Long id;
    private String name;
    private String subname;
    private String langue;

    public CarrouselDownloded() {
    }

    public CarrouselDownloded(Long id, String name, String subname, String langue) {
        this.id = id;
        this.name = name;
        this.subname = subname;
        this.langue = langue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    @Override
    public String toString() {
        return "CarrouselDownloded{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", langue='" + langue + '\'' +
                '}';
    }
}
