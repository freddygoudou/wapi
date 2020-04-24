package entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

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

    protected Video(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readString();
        video = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(video);
    }

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
