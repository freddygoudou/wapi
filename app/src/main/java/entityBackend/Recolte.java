package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recolte implements Parcelable {

    private Long id;
    private String venteTotale;
    private SaisonCulture saisonCulture;
    private List<Vente> ventes;
    private List<SacsPoids> sacsPoids;
    private Date createdAt;
    private Date updatedAt;

    public Recolte() {
    }

    public Recolte(Long id, String venteTotale, SaisonCulture saisonCulture, List<Vente> ventes, List<SacsPoids> sacsPoids, Date createdAt, Date updatedAt) {
        this.id = id;
        this.venteTotale = venteTotale;
        this.saisonCulture = saisonCulture;
        this.ventes = ventes;
        this.sacsPoids = sacsPoids;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Recolte(String venteTotale, SaisonCulture saisonCulture, List<Vente> ventes, List<SacsPoids> sacsPoids) {
        this.venteTotale = venteTotale;
        this.saisonCulture = saisonCulture;
        this.ventes = ventes;
        this.sacsPoids = sacsPoids;
    }


    protected Recolte(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        venteTotale = in.readString();
        saisonCulture = in.readParcelable(SaisonCulture.class.getClassLoader());
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenteTotale() {
        return venteTotale;
    }

    public void setVenteTotale(String venteTotale) {
        this.venteTotale = venteTotale;
    }

    public SaisonCulture getSaisonCulture() {
        return saisonCulture;
    }

    public void setSaisonCulture(SaisonCulture saisonCulture) {
        this.saisonCulture = saisonCulture;
    }

    @Override
    public String toString() {
        return "Recolte{" +
                "id=" + id +
                ", venteTotale='" + venteTotale + '\'' +
                ", saisonCulture=" + saisonCulture +
                ", ventes=" + ventes.toString() +
                ", sacsPoids=" + sacsPoids.toString() +
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
        parcel.writeString(venteTotale);
        parcel.writeParcelable(saisonCulture, i);
        parcel.writeTypedList(ventes);
        parcel.writeTypedList(sacsPoids);
    }
}
