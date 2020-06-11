package entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarrouselFormation implements Parcelable {
    private String _id;
    private String name;
    private ArrayList<ImageCarrousel> images;
    private ArrayList<AudioCarrousel> audios;
    private Date createdAt;
    private Date updatedAt;

    public CarrouselFormation() {
    }

    public CarrouselFormation(String _id, String texte, String name, ArrayList<ImageCarrousel> images, ArrayList<AudioCarrousel> audios, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.name = name;
        this.images = images;
        this.audios = audios;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CarrouselFormation(String _id, String name, ArrayList<ImageCarrousel> images, ArrayList<AudioCarrousel> audios) {
        this._id = _id;
        this.name = name;
        this.images = images;
        this.audios = audios;
    }

    public CarrouselFormation(String _id, String name, ArrayList<ImageCarrousel> images, ArrayList<AudioCarrousel> audios, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.name = name;
        this.images = images;
        this.audios = audios;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected CarrouselFormation(Parcel in) {
        _id = in.readString();
        name = in.readString();
        images = in.createTypedArrayList(ImageCarrousel.CREATOR);
        audios = in.createTypedArrayList(AudioCarrousel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeTypedList(images);
        dest.writeTypedList(audios);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarrouselFormation> CREATOR = new Creator<CarrouselFormation>() {
        @Override
        public CarrouselFormation createFromParcel(Parcel in) {
            return new CarrouselFormation(in);
        }

        @Override
        public CarrouselFormation[] newArray(int size) {
            return new CarrouselFormation[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ImageCarrousel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageCarrousel> images) {
        this.images = images;
    }

    public ArrayList<AudioCarrousel> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<AudioCarrousel> audios) {
        this.audios = audios;
    }

    @Override
    public String toString() {
        return "CarrouselFormation{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images.toString() +
                ", audios=" + audios.toString() +
                '}';
    }

}
