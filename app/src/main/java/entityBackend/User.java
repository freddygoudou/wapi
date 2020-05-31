package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User implements Parcelable {

    private Long id;
    private String firebasUid;
    private String name;
    private String phoneNumber;
    private String langue;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    public User(Long id, String firebasUid, String name, String phoneNumber, String langue, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firebasUid = firebasUid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(Long id, String firebasUid, String name, String phoneNumber, String langue) {
        this.id = id;
        this.firebasUid = firebasUid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
    }

    public User(String firebasUid, String name, String phoneNumber, String langue) {
        this.firebasUid = firebasUid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
    }

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        phoneNumber = in.readString();
        langue = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirebasUid() {
        return firebasUid;
    }

    public void setFirebasUid(String firebasUid) {
        this.firebasUid = firebasUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", langue='" + langue + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeString(langue);
    }
}
