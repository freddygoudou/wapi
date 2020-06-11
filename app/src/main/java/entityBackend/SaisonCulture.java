package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SaisonCulture implements Parcelable {

    private String _id;
    private String nomCulture;
    private String dateSemie;
    private boolean beforeApp;
    private List<Depense> depenses;
    private Recolte recolte;

    /*

            Nom de la plantation , planifier les taches, date de semi, etape de ... (en fonction de la de semi), superficie du champs, culture actuelle

     */

    public SaisonCulture() {
    }

    public SaisonCulture(String _id, String nomCulture, String dateSemie, boolean beforeApp, List<Depense> depenses, Recolte recolte) {
        this._id = _id;
        this.nomCulture = nomCulture;
        this.dateSemie = dateSemie;
        this.beforeApp = beforeApp;
        this.depenses = depenses;
        this.recolte = recolte;
    }

    public SaisonCulture(String nomCulture, String dateSemie, boolean beforeApp, List<Depense> depenses, Recolte recolte) {
        this.nomCulture = nomCulture;
        this.dateSemie = dateSemie;
        this.beforeApp = beforeApp;
        this.depenses = depenses;
        this.recolte = recolte;
    }


    protected SaisonCulture(Parcel in) {
        _id = in.readString();
        nomCulture = in.readString();
        dateSemie = in.readString();
        beforeApp = in.readByte() != 0;
        depenses = in.createTypedArrayList(Depense.CREATOR);
        recolte = in.readParcelable(Recolte.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(nomCulture);
        dest.writeString(dateSemie);
        dest.writeByte((byte) (beforeApp ? 1 : 0));
        dest.writeTypedList(depenses);
        dest.writeParcelable(recolte, flags);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public String getDateSemie() {
        return dateSemie;
    }

    public void setDateSemie(String dateSemie) {
        this.dateSemie = dateSemie;
    }

    public boolean getBeforeApp() {
        return beforeApp;
    }

    public void setBeforeApp(boolean beforeApp) {
        this.beforeApp = beforeApp;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public Recolte getRecolte() {
        return recolte;
    }

    public void setRecolte(Recolte recolte) {
        this.recolte = recolte;
    }

    @Override
    public String toString() {
        return "SaisonCulture{" +
                "_id='" + _id + '\'' +
                ", nomCulture='" + nomCulture + '\'' +
                ", dateSemie='" + dateSemie + '\'' +
                ", beforeApp=" + beforeApp +
                ", depenses=" + depenses +
                ", recolte=" + recolte +
                '}';
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
