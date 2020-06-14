package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

import entity.AudioCarrousel;
import entity.ImageCarrousel;

public class CarrouselFormation implements Parcelable {
    private String texte;
    private String order;
    private ArrayList<ImageCarrousel> images;
    private ArrayList<AudioCarrousel> audios;

    public CarrouselFormation() {
    }

    public CarrouselFormation(String texte, String order, ArrayList<ImageCarrousel> images, ArrayList<AudioCarrousel> audios) {
        this.texte = texte;
        this.order = order;
        this.images = images;
        this.audios = audios;
    }

    protected CarrouselFormation(Parcel in) {
        texte = in.readString();
        order = in.readString();
        images = in.createTypedArrayList(ImageCarrousel.CREATOR);
        audios = in.createTypedArrayList(AudioCarrousel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(texte);
        dest.writeString(order);
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

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
                "texte='" + texte + '\'' +
                ", order='" + order + '\'' +
                ", images=" + images.toString()+
                ", audios=" + audios.toString()+
                '}';
    }
}
