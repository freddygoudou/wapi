package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ContactEmployee implements Parcelable {
    private String _id;
    private String phoneNumber;

    public ContactEmployee() {
    }

        public ContactEmployee(String id, String phoneNumber) {
        this._id = id;
        this.phoneNumber = phoneNumber;
    }

    public ContactEmployee(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    protected ContactEmployee(Parcel in) {
        _id = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<ContactEmployee> CREATOR = new Creator<ContactEmployee>() {
        @Override
        public ContactEmployee createFromParcel(Parcel in) {
            return new ContactEmployee(in);
        }

        @Override
        public ContactEmployee[] newArray(int size) {
            return new ContactEmployee[size];
        }
    };

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactEmployee{" +
                "id=" + _id +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(phoneNumber);
    }
}
