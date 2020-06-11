package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Champs implements Parcelable {

    private String _id;
    private String name;
    private boolean active;
    private String delaieReativite;
    private List<ChampsLocation> champsLocations;
    private List<SaisonCulture> saisonCultures;
    private List<Employee> employees;



    public Champs() {
    }

    public Champs(String _id, String name, boolean active, String delaieReativite, List<ChampsLocation> champsLocations, List<SaisonCulture> saisonCultures, List<Employee> employees) {
        this._id = _id;
        this.name = name;
        this.active = active;
        this.delaieReativite = delaieReativite;
        this.champsLocations = champsLocations;
        this.saisonCultures = saisonCultures;
        this.employees = employees;
    }

    public Champs(String name, boolean active, String delaieReativite, List<ChampsLocation> champsLocations, List<SaisonCulture> saisonCultures, List<Employee> employees) {
        this.name = name;
        this.active = active;
        this.delaieReativite = delaieReativite;
        this.champsLocations = champsLocations;
        this.saisonCultures = saisonCultures;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Champs{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", delaieReativite='" + delaieReativite + '\'' +
                ", champsLocations=" + champsLocations +
                ", saisonCultures=" + saisonCultures +
                ", employees=" + employees +
                '}';
    }

    protected Champs(Parcel in) {
        _id = in.readString();
        name = in.readString();
        active = in.readByte() != 0;
        delaieReativite = in.readString();
        champsLocations = in.createTypedArrayList(ChampsLocation.CREATOR);
        saisonCultures = in.createTypedArrayList(SaisonCulture.CREATOR);
        employees = in.createTypedArrayList(Employee.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(delaieReativite);
        dest.writeTypedList(champsLocations);
        dest.writeTypedList(saisonCultures);
        dest.writeTypedList(employees);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Champs> CREATOR = new Creator<Champs>() {
        @Override
        public Champs createFromParcel(Parcel in) {
            return new Champs(in);
        }

        @Override
        public Champs[] newArray(int size) {
            return new Champs[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDelaieReativite() {
        return delaieReativite;
    }

    public void setDelaieReativite(String delaieReativite) {
        this.delaieReativite = delaieReativite;
    }

    public List<ChampsLocation> getChampsLocations() {
        return champsLocations;
    }

    public void setChampsLocations(List<ChampsLocation> champsLocations) {
        this.champsLocations = champsLocations;
    }

    public List<SaisonCulture> getSaisonCultures() {
        return saisonCultures;
    }

    public void setSaisonCultures(List<SaisonCulture> saisonCultures) {
        this.saisonCultures = saisonCultures;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}






/*
package entityBackend;

        import android.os.Parcel;
        import android.os.Parcelable;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

public class Champs implements Parcelable {

    private Long id;
    private String nom;
    private List<ChampsLocation> champsLocations;
    private List<SaisonCulture> saisonCultures;
    private List<Employee> employees;
    private boolean active;
    private String delaieReativite;
    private Farmer farmer;
    private Date createdAt;
    private Date updatedAt;

    public Champs() {
    }

    public Champs(Long id, String nom, List<ChampsLocation> champsLocations, List<SaisonCulture> saisonCultures, List<Employee> employees, boolean active, String delaieReativite, Farmer farmer, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.champsLocations = champsLocations;
        this.saisonCultures = saisonCultures;
        this.employees = employees;
        this.active = active;
        this.delaieReativite = delaieReativite;
        this.farmer = farmer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Champs(String nom, boolean active, String delaieReativite) {
        this.nom = nom;
        this.active = active;
        this.delaieReativite = delaieReativite;
    }

    public Champs(String nom, List<ChampsLocation> champsLocations, List<SaisonCulture> saisonCultures, List<Employee> employees, boolean active, String delaieReativite, Farmer farmer) {
        this.nom = nom;
        this.champsLocations = champsLocations;
        this.saisonCultures = saisonCultures;
        this.employees = employees;
        this.active = active;
        this.delaieReativite = delaieReativite;
        this.farmer = farmer;
    }


    protected Champs(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nom = in.readString();
        champsLocations = in.createTypedArrayList(ChampsLocation.CREATOR);
        saisonCultures = in.createTypedArrayList(SaisonCulture.CREATOR);
        employees = in.createTypedArrayList(Employee.CREATOR);
        active = in.readByte() != 0;
        delaieReativite = in.readString();
    }

    public static final Creator<Champs> CREATOR = new Creator<Champs>() {
        @Override
        public Champs createFromParcel(Parcel in) {
            return new Champs(in);
        }

        @Override
        public Champs[] newArray(int size) {
            return new Champs[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChampsLocation> getChampsLocations() {
        return champsLocations;
    }

    public void setChampsLocations(List<ChampsLocation> champsLocations) {
        this.champsLocations = champsLocations;
    }

    public List<SaisonCulture> getSaisonCultures() {
        return saisonCultures;
    }

    public void setSaisonCultures(List<SaisonCulture> saisonCultures) {
        this.saisonCultures = saisonCultures;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDelaieReativite() {
        return delaieReativite;
    }

    public void setDelaieReativite(String delaieReativite) {
        this.delaieReativite = delaieReativite;
    }

    @Override
    public String toString() {
        return "Champs{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", champsLocations=" + champsLocations.toString() +
                ", saisonCultures=" + saisonCultures.toString() +
                ", employees=" + employees.toString() +
                ", active=" + active +
                ", delaieReativite='" + delaieReativite + '\'' +
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
        parcel.writeString(nom);
        parcel.writeTypedList(champsLocations);
        parcel.writeTypedList(saisonCultures);
        parcel.writeTypedList(employees);
        parcel.writeByte((byte) (active ? 1 : 0));
        parcel.writeString(delaieReativite);
    }
}*/
