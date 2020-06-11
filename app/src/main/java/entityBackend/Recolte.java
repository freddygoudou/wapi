package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recolte implements Parcelable {

    private String _id;
    private String venteTotale;
    private List<Vente> ventes;
    private List<SacsPoids> sacsPoids;

    public Recolte() {
    }

    public Recolte(String id, String venteTotale, List<Vente> ventes, List<SacsPoids> sacsPoids) {
        this._id = id;
        this.venteTotale = venteTotale;
        this.ventes = ventes;
        this.sacsPoids = sacsPoids;
    }

    public Recolte(String venteTotale, List<Vente> ventes, List<SacsPoids> sacsPoids) {
        this.venteTotale = venteTotale;
        this.ventes = ventes;
        this.sacsPoids = sacsPoids;
    }


    protected Recolte(Parcel in) {
        _id = in.readString();
        venteTotale = in.readString();
        ventes = in.createTypedArrayList(Vente.CREATOR);
        sacsPoids = in.createTypedArrayList(SacsPoids.CREATOR);
    }

    public static final Creator<Recolte> CREATOR = new Creator<Recolte>() {
        @Override
        public Recolte createFromParcel(Parcel in) {
            return new Recolte(in);
        }

        @Override
        public Recolte[] newArray(int size) {
            return new Recolte[size];
        }
    };

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getVenteTotale() {
        return venteTotale;
    }

    public void setVenteTotale(String venteTotale) {
        this.venteTotale = venteTotale;
    }


    @Override
    public String toString() {
        return "Recolte{" +
                "id=" + _id +
                ", venteTotale='" + venteTotale + '\'' +
                ", ventes=" + ventes.toString() +
                ", sacsPoids=" + sacsPoids.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


}
