package entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ImageCarrousel implements Parcelable {
    private String _id;
    private String url;
    private String name;
    private int image;

    public ImageCarrousel(String _id, String url, String name, int image) {
        this._id = _id;
        this.url = url;
        this.name = name;
        this.image = image;
    }

    public ImageCarrousel(String url, String name, int image) {
        this.url = url;
        this.name = name;
        this.image = image;
    }

    protected ImageCarrousel(Parcel in) {
        _id = in.readString();
        url = in.readString();
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<ImageCarrousel> CREATOR = new Creator<ImageCarrousel>() {
        @Override
        public ImageCarrousel createFromParcel(Parcel in) {
            return new ImageCarrousel(in);
        }

        @Override
        public ImageCarrousel[] newArray(int size) {
            return new ImageCarrousel[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImageCarrousel{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(url);
        parcel.writeString(name);
        parcel.writeInt(image);
    }
}
