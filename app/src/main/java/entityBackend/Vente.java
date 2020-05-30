package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Vente implements Parcelable {

    private Long id;
    private String montant;
    private Double description;
    private Date createdAt;
    private Date updatedAt;

    public Vente() {
    }

    public Vente(String montant, Double description) {
        this.montant = montant;
        this.description = description;
    }

    public Vente(Long id, String montant, Double description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.montant = montant;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected Vente(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        montant = in.readString();
        if (in.readByte() == 0) {
            description = null;
        } else {
            description = in.readDouble();
        }
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
                "id=" + id +
                ", montant='" + montant + '\'' +
                ", description=" + description +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(montant);
        if (description == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(description);
        }
    }
}
