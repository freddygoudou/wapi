package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Caroussel implements Parcelable {

    private String  _id;
    private String name;
    private String description;
    private String audiosPaths;
    private String imagesPaths;
    private Date createdAt;
    private Date updatedAt;

    public Caroussel(){}

    public Caroussel(String id, String name, String description, String audiosPaths, String imagesPaths, Date createdAt, Date updatedAt) {
        this._id = id;
        this.name = name;
        this.description = description;
        this.audiosPaths = audiosPaths;
        this.imagesPaths = imagesPaths;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Caroussel(String name, String description, String audiosPaths, String imagesPaths) {
        this.name = name;
        this.description = description;
        this.audiosPaths = audiosPaths;
        this.imagesPaths = imagesPaths;
    }

    protected Caroussel(Parcel in) {
        _id = in.readString();
        name = in.readString();
        description = in.readString();
        audiosPaths = in.readString();
        imagesPaths = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(audiosPaths);
        dest.writeString(imagesPaths);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "Caroussel{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", audiosPaths='" + audiosPaths + '\'' +
                ", imagesPaths='" + imagesPaths + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
