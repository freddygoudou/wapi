package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Depense implements Parcelable {

    private Long id;
    private String montant;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Depense() {
    }

    public Depense(Long id, String montant, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.montant = montant;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Depense(String montant, String description) {
        this.montant = montant;
        this.description = description;
    }

    protected Depense(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(montant);
        parcel.writeString(description);
    }

    @Override
    public String toString() {
        return "Depense{" +
                "id=" + id +
                ", montant='" + montant + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
