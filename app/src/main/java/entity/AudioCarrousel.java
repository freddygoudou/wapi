package entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class AudioCarrousel implements Parcelable {
    private String url;
    private String baseUri;
    private int order;
    private int audio;

    public AudioCarrousel(String url, String baseUri, int order, int audio) {
        this.url = url;
        this.baseUri = baseUri;
        this.order = order;
        this.audio = audio;
    }

    protected AudioCarrousel(Parcel in) {
        url = in.readString();
        baseUri = in.readString();
        order = in.readInt();
        audio = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(baseUri);
        dest.writeInt(order);
        dest.writeInt(audio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AudioCarrousel> CREATOR = new Creator<AudioCarrousel>() {
        @Override
        public AudioCarrousel createFromParcel(Parcel in) {
            return new AudioCarrousel(in);
        }

        @Override
        public AudioCarrousel[] newArray(int size) {
            return new AudioCarrousel[size];
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "AudioCarrousel{" +
                "url='" + url + '\'' +
                ", baseUri='" + baseUri + '\'' +
                ", order='" + order + '\'' +
                ", audio=" + audio +
                '}';
    }


}
