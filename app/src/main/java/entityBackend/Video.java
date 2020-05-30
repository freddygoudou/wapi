package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Video implements Parcelable {

    private Long id;
    private String name;
    private String videosPaths;
    private String description;
    private String captionPath;
    private Date createdAt;
    private Date updatedAt;

    public Video(){}

    public Video(Long id, String name, String videosPaths, String description, String captionPath, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Video(String name, String videosPaths, String description, String captionPath) {
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
    }

    public Video(Long id, String name, String videosPaths, String description, String captionPath) {
        this.id = id;
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
    }


    protected Video(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
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
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videosPaths='" + videosPaths + '\'' +
                ", description='" + description + '\'' +
                ", captionPath='" + captionPath + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(name);
        parcel.writeString(videosPaths);
        parcel.writeString(description);
        parcel.writeString(captionPath);
    }
}
