package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class PrixSacs implements Parcelable {

    private String _id;
    private String nomCulture;
    private Double prixUnitaire;

    public PrixSacs() {
    }

    public PrixSacs(String id, String nomCulture, Double prixUnitaire) {
        this._id = id;
        this.nomCulture = nomCulture;
        this.prixUnitaire = prixUnitaire;
    }

    public PrixSacs(String nomCulture, Double prixUnitaire) {
        this.nomCulture = nomCulture;
        this.prixUnitaire = prixUnitaire;
    }


    protected PrixSacs(Parcel in) {
        _id = in.readString();
        nomCulture = in.readString();
        if (in.readByte() == 0) {
            prixUnitaire = null;
        } else {
            prixUnitaire = in.readDouble();
        }
    }

    public static final Creator<PrixSacs> CREATOR = new Creator<PrixSacs>() {
        @Override
        public PrixSacs createFromParcel(Parcel in) {
            return new PrixSacs(in);
        }

        @Override
        public PrixSacs[] newArray(int size) {
            return new PrixSacs[size];
        }
    };

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(nomCulture);
        if (prixUnitaire == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(prixUnitaire);
        }
    }

    @Override
    public String toString() {
        return "PrixSacs{" +
                "_id='" + _id + '\'' +
                ", nomCulture='" + nomCulture + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
