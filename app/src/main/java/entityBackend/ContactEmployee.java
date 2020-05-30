package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ContactEmployee implements Parcelable {
    private Long id;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;

    public ContactEmployee() {
    }

    public ContactEmployee(Long id, String phoneNumber, Date createdAt, Date updatedAt) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ContactEmployee(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected ContactEmployee(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
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
        parcel.writeString(phoneNumber);
    }
}
