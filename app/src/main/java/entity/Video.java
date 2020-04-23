package entity;

public class Video {

    private String title;
    private String description;
    private String image;
    private String video;

    public Video() {
    }

    public Video(String title, String description, String image, String video) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
