package entity;


import java.net.URL;

public class SlideItem {
    private int image;
    private String audioUrl;


    public SlideItem(int image, String uri) {
        this.image = image;
        this.audioUrl = uri;
    }

    public SlideItem(int image ){
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUri(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
