package entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ImageCarrousel implements Parcelable {
    private String url;
    private String baseUri;
    private int image;
    private int order;

    public ImageCarrousel(String url, String baseUri, int image, int order) {
        this.url = url;
        this.baseUri = baseUri;
        this.image = image;
        this.order = order;
    }


    protected ImageCarrousel(Parcel in) {
        url = in.readString();
        baseUri = in.readString();
        image = in.readInt();
        order = in.readInt();
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /*@Override
    public String toString() {
        return "ImageCarrousel{" +
                "url='" + url + '\'' +
                ", baseUri='" + baseUri + '\'' +
                ", image=" + image +
                '}';
    }*/

    @Override
    public String toString() {
        return "ImageCarrousel{" +
                "order=" + order +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(baseUri);
        parcel.writeInt(image);
        parcel.writeInt(order);
    }
}
