package entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class VideoYoutube implements Parcelable {

    private String _id;
    private String name;
    private String videoId;
    private String description;
    private String captionPath;
    private Date createdAt;
    private Date updatedAt;

    public VideoYoutube(){}

    public VideoYoutube(String id, String name, String videoId, String description, String captionPath, Date createdAt, Date updatedAt) {
        this._id = id;
        this.name = name;
        this.videoId = videoId;
        this.description = description;
        this.captionPath = captionPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public VideoYoutube(String name, String videoId, String description, String captionPath) {
        this.name = name;
        this.videoId = videoId;
        this.description = description;
        this.captionPath = captionPath;
    }

    public VideoYoutube(String id, String name, String videoId, String description, String captionPath) {
        this._id = id;
        this.name = name;
        this.videoId = videoId;
        this.description = description;
        this.captionPath = captionPath;
    }


    protected VideoYoutube(Parcel in) {
        _id = in.readString();
        name = in.readString();
        videoId = in.readString();
        description = in.readString();
        captionPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(videoId);
        dest.writeString(description);
        dest.writeString(captionPath);
    }

    public static final Creator<VideoYoutube> CREATOR = new Creator<VideoYoutube>() {
        @Override
        public VideoYoutube createFromParcel(Parcel in) {
            return new VideoYoutube(in);
        }

        @Override
        public VideoYoutube[] newArray(int size) {
            return new VideoYoutube[size];
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videosPaths) {
        this.videoId = videosPaths;
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
                ", videosPaths='" + videoId + '\'' +
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
