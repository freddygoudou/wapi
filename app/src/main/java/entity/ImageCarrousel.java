package entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ImageCarrousel implements Parcelable {
    private String url;
    private String baseUri;
    private int image;

    public ImageCarrousel(String url, String baseUri, int image) {
        this.url = url;
        this.baseUri = baseUri;
        this.image = image;
    }

    protected ImageCarrousel(Parcel in) {
        url = in.readString();
        baseUri = in.readString();
        image = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(baseUri);
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageCarrousel{" +
                "url='" + url + '\'' +
                ", baseUri='" + baseUri + '\'' +
                ", image=" + image +
                '}';
    }
}
