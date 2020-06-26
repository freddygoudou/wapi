package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class AllCarrousels implements Parcelable {

    private ArrayList<Carrousel> carrousels;

    public AllCarrousels(){}

    public AllCarrousels(ArrayList<Carrousel> carrousels) {
        this.carrousels = carrousels;
    }

    public ArrayList<Carrousel> getCarrousels() {
        return carrousels;
    }

    public void setCarrousels(ArrayList<Carrousel> carrousels) {
        this.carrousels = carrousels;
    }

    protected AllCarrousels(Parcel in) {
        carrousels = in.createTypedArrayList(Carrousel.CREATOR);
    }

    public static final Creator<AllCarrousels> CREATOR = new Creator<AllCarrousels>() {
        @Override
        public AllCarrousels createFromParcel(Parcel in) {
            return new AllCarrousels(in);
        }

        @Override
        public AllCarrousels[] newArray(int size) {
            return new AllCarrousels[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(carrousels);
    }
}

