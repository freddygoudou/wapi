package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Depense implements Parcelable {

    private String _id;
    private String montant;
    private String description;

    public Depense() {
    }

    public Depense(String id, String montant, String description) {
        this._id = id;
        this.montant = montant;
        this.description = description;
    }

    public Depense(String montant, String description) {
        this.montant = montant;
        this.description = description;
    }


    protected Depense(Parcel in) {
        _id = in.readString();
        montant = in.readString();
        description = in.readString();
    }

    public static final Creator<Depense> CREATOR = new Creator<Depense>() {
        @Override
        public Depense createFromParcel(Parcel in) {
            return new Depense(in);
        }

        @Override
        public Depense[] newArray(int size) {
            return new Depense[size];
        }
    };

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(montant);
        parcel.writeString(description);
    }

    @Override
    public String toString() {
        return "Depense{" +
                "_id='" + _id + '\'' +
                ", montant='" + montant + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
