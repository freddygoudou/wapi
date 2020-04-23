package entity;

public class Article {

    private String name;
    private String quantite;
    private String date;

    public Article() {
    }

    public Article(String name, String quantite, String date) {
        this.name = name;
        this.quantite = quantite;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
