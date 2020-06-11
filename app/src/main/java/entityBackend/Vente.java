package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Vente implements Parcelable {

    private String _id;
    private String montant;
    private String description;

    public Vente() {
    }

    public Vente(String montant, String description) {
        this.montant = montant;
        this.description = description;
    }

    public Vente(String id, String montant, String description) {
        this._id = id;
        this.montant = montant;
        this.description = description;
    }


    protected Vente(Parcel in) {
        _id = in.readString();
        montant = in.readString();
        description = in.readString();
    }

    public static final Creator<Vente> CREATOR = new Creator<Vente>() {
        @Override
        public Vente createFromParcel(Parcel in) {
            return new Vente(in);
        }

        @Override
        public Vente[] newArray(int size) {
            return new Vente[size];
        }
    };

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + _id +
                ", montant='" + montant + '\'' +
                ", description=" + description +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(montant);
        if (description == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(description);
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
