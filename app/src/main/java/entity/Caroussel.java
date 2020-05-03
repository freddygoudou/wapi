package entity;

import android.os.Parcel;
import android.os.Parcelable;


public class Caroussel implements Parcelable {

    private int id;
    private String name;
    private String description;
    private String audiosPaths;
    private String imagesPaths;

    public Caroussel(int id, String name, String description, String audiosPaths, String imagesPaths) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.audiosPaths = audiosPaths;
        this.imagesPaths = imagesPaths;
    }

    public Caroussel(String name, String description, String audiosPaths, String imagesPaths) {
        this.name = name;
        this.description = description;
        this.audiosPaths = audiosPaths;
        this.imagesPaths = imagesPaths;
    }

    protected Caroussel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        audiosPaths = in.readString();
        imagesPaths = in.readString();
    }

    public static final Creator<Caroussel> CREATOR = new Creator<Caroussel>() {
        @Override
        public Caroussel createFromParcel(Parcel in) {
            return new Caroussel(in);
        }

        @Override
        public Caroussel[] newArray(int size) {
            return new Caroussel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudiosPaths() {
        return audiosPaths;
    }

    public void setAudiosPaths(String audiosPaths) {
        this.audiosPaths = audiosPaths;
    }

    public String getImagesPaths() {
        return imagesPaths;
    }

    public void setImagesPaths(String imagesPaths) {
        this.imagesPaths = imagesPaths;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(audiosPaths);
        parcel.writeString(imagesPaths);
    }

    @Override
    public String toString() {
        return "Caroussel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", audiosPaths='" + audiosPaths + '\'' +
                ", imagesPaths='" + imagesPaths + '\'' +
                '}';
    }
}
