package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Employee implements Parcelable {

    private Long id;
    private String nom;
    private List<ContactEmployee> contactEmployees;
    private Date createdAt;
    private Date updatedAt;

    public Employee() {
    }

    public Employee(Long id, String nom, List<ContactEmployee> contactEmployees, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.contactEmployees = contactEmployees;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Employee(String nom, List<ContactEmployee> contactEmployees) {
        this.nom = nom;
        this.contactEmployees = contactEmployees;
    }


    protected Employee(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nom = in.readString();
        contactEmployees = in.createTypedArrayList(ContactEmployee.CREATOR);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ContactEmployee> getContactEmployees() {
        return contactEmployees;
    }

    public void setContactEmployees(List<ContactEmployee> contactEmployees) {
        this.contactEmployees = contactEmployees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", contactEmployees=" + contactEmployees.toString() +
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
        parcel.writeTypedList(contactEmployees);
    }
}
