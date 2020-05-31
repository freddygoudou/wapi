package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

public class SaisonCulture implements Parcelable {

    private Long id;
    private String ancienneCulture;
    private String nouvelleCulture;


    public SaisonCulture() {
    }


    public SaisonCulture(Long id, String ancienneCulture, String nouvelleCulture) {
        this.id = id;
        this.ancienneCulture  = ancienneCulture;
        this.nouvelleCulture = nouvelleCulture;
    }


    public SaisonCulture(String ancienneCulture, String nouvelleCulture) {
        this.ancienneCulture  = ancienneCulture;
        this.nouvelleCulture = nouvelleCulture;
    }


    protected SaisonCulture(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        ancienneCulture = in.readString();
        nouvelleCulture = in.readString();
    }

    public static final Creator<SaisonCulture> CREATOR = new Creator<SaisonCulture>() {
        @Override
        public SaisonCulture createFromParcel(Parcel in) {
            return new SaisonCulture(in);
        }

        @Override
        public SaisonCulture[] newArray(int size) {
            return new SaisonCulture[size];
        }
    };

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
        parcel.writeString(ancienneCulture);
        parcel.writeString(nouvelleCulture);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAncienneCulture() {
        return ancienneCulture;
    }

    public void setAncienneCulture(String ancienneCulture) {
        this.ancienneCulture = ancienneCulture;
    }

    public String getNouvelleCulture() {
        return nouvelleCulture;
    }

    public void setNouvelleCulture(String nouvelleCulture) {
        this.nouvelleCulture = nouvelleCulture;
    }
}
















/*package entityBackend;

        import android.os.Parcel;
        import android.os.Parcelable;

        import java.util.Date;
        import java.util.List;

public class SaisonCulture implements Parcelable {

    private Long id;
    private String nomCulture;
    private String anneeeCulture; //ex : 2020- 2021
    private boolean beforeApp;
    private List<Depense> depenses;
    private Recolte recolte;
    private Date createdAt;
    private Date updatedAt;


    public SaisonCulture() {
    }

    public SaisonCulture(Long id, String nomCulture, String anneeeCulture, boolean beforeApp, List<Depense> depenses, Recolte recolte, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nomCulture = nomCulture;
        this.anneeeCulture = anneeeCulture;
        this.beforeApp = beforeApp;
        this.depenses = depenses;
        this.recolte = recolte;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SaisonCulture(String nomCulture, String anneeeCulture, boolean beforeApp, List<Depense> depenses, Recolte recolte) {
        this.nomCulture = nomCulture;
        this.anneeeCulture = anneeeCulture;
        this.beforeApp = beforeApp;
        this.depenses = depenses;
        this.recolte = recolte;
    }


    protected SaisonCulture(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nomCulture = in.readString();
        anneeeCulture = in.readString();
        beforeApp = in.readByte() != 0;
        depenses = in.createTypedArrayList(Depense.CREATOR);
        recolte = in.readParcelable(Recolte.class.getClassLoader());
    }

    public static final Creator<SaisonCulture> CREATOR = new Creator<SaisonCulture>() {
        @Override
        public SaisonCulture createFromParcel(Parcel in) {
            return new SaisonCulture(in);
        }

        @Override
        public SaisonCulture[] newArray(int size) {
            return new SaisonCulture[size];
        }
    };

    @Override
    public String toString() {
        return "SaisonCulture{" +
                "id=" + id +
                ", nomCulture='" + nomCulture + '\'' +
                ", anneeeCulture='" + anneeeCulture + '\'' +
                ", beforeApp=" + beforeApp +
                ", depenses=" + depenses.toString() +
                ", recolte=" + recolte +
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
        parcel.writeString(nomCulture);
        parcel.writeString(anneeeCulture);
        parcel.writeByte((byte) (beforeApp ? 1 : 0));
        parcel.writeTypedList(depenses);
        parcel.writeParcelable(recolte, i);
    }
}*/
