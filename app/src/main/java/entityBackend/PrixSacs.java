package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class PrixSacs implements Parcelable {

    private Long id;
    private String nomCulture;
    private Double prixUnitaire;
    private Date createdAt;
    private Date updatedAt;

    public PrixSacs() {
    }

    public PrixSacs(Long id, String nomCulture, Double prixUnitaire, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nomCulture = nomCulture;
        this.prixUnitaire = prixUnitaire;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PrixSacs(String nomCulture, Double prixUnitaire) {
        this.nomCulture = nomCulture;
        this.prixUnitaire = prixUnitaire;
    }

    protected PrixSacs(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", nomCulture='" + nomCulture + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
