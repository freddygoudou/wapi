package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Employee implements Parcelable {

    private String _id;
    private String name;
    private List<ContactEmployee> contactEmployees;

    public Employee() {
    }

    public Employee(String id, String nom, List<ContactEmployee> contactEmployees) {
        this._id = id;
        this.name = nom;
        this.contactEmployees = contactEmployees;
    }

    public Employee(String nom, List<ContactEmployee> contactEmployees) {
        this.name = nom;
        this.contactEmployees = contactEmployees;
    }


    protected Employee(Parcel in) {
        _id = in.readString();
        name = in.readString();
        contactEmployees = in.createTypedArrayList(ContactEmployee.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeTypedList(contactEmployees);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
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
                "id=" + _id +
                ", nom='" + name + '\'' +
                ", contactEmployees=" + contactEmployees.toString() +
                '}';
    }


}
