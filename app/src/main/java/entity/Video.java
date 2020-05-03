package entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

    private int id;
    private String name;
    private String videosPaths;
    private String description;
    private String captionPath;


    public Video(int id, String name, String videosPaths, String description, String captionPath) {
        this.id = id;
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
    }

    public Video(String name, String videosPaths, String description, String captionPath) {
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
    }

    protected Video(Parcel in) {
        id = in.readInt();
        name = in.readString();
        videosPaths = in.readString();
        description = in.readString();
        captionPath = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideosPaths() {
        return videosPaths;
    }

    public void setVideosPaths(String videosPaths) {
        this.videosPaths = videosPaths;
    }

    public String getCaptionPath() {
        return captionPath;
    }

    public void setCaptionPath(String captionPath) {
        this.captionPath = captionPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(videosPaths);
        parcel.writeString(description);
        parcel.writeString(captionPath);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videosPaths='" + videosPaths + '\'' +
                ", description='" + description + '\'' +
                ", captionPath='" + captionPath + '\'' +
                '}';
    }
}
