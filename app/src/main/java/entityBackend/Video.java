package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Video implements Parcelable {

    private String _id;
    private String name;
    private String videosPaths;
    private String description;
    private String captionPath;
    private Date createdAt;
    private Date updatedAt;

    public Video(){}

    public Video(String id, String name, String videosPaths, String description, String captionPath, Date createdAt, Date updatedAt) {
        this._id = id;
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

    public Video(String id, String name, String videosPaths, String description, String captionPath) {
        this._id = id;
        this.name = name;
        this.videosPaths = videosPaths;
        this.description = description;
        this.captionPath = captionPath;
    }


    protected Video(Parcel in) {
        _id = in.readString();
        name = in.readString();
        videosPaths = in.readString();
        description = in.readString();
        captionPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(videosPaths);
        dest.writeString(description);
        dest.writeString(captionPath);
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

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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
                "id=" + _id +
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


}
