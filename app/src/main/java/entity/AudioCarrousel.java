package entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class AudioCarrousel implements Parcelable {
    private String _id;
    private String url;
    private int audio;
    private String texte;//raw
    private String name;

    public AudioCarrousel(String _id, String url, int audio, String name, String texte) {
        this._id = _id;
        this.url = url;
        this.audio = audio;
        this.name = name;
        this.texte = texte;
    }

    public AudioCarrousel(String url, int audio, String name, String texte) {
        this.url = url;
        this.audio = audio;
        this.name = name;
        this.texte = texte;
    }


    protected AudioCarrousel(Parcel in) {
        _id = in.readString();
        url = in.readString();
        audio = in.readInt();
        texte = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(url);
        dest.writeInt(audio);
        dest.writeString(texte);
        dest.writeString(name);
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AudioCarrousel{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", audio=" + audio +
                ", texte='" + texte + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
