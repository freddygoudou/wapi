package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Farmer implements Parcelable {
    private String _id;
    private String firebaseId;
    private String name;
    private String phoneNumber;
    private String langue;
    private List<PrixSacs> prixSacs;
    private List<Champs> champs;
    private Date createdAt;
    private Date updatedAt;

    public Farmer() {
    }

    public Farmer(String _id, String firebaseId, String name, String phoneNumber, String langue, List<PrixSacs> prixSacs, List<Champs> champs, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.firebaseId = firebaseId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
        this.prixSacs = prixSacs;
        this.champs = champs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Farmer(String firebaseId, String name, String phoneNumber, String langue, List<PrixSacs> prixSacs, List<Champs> champs) {
        this.firebaseId = firebaseId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
        this.prixSacs = prixSacs;
        this.champs = champs;
    }

    protected Farmer(Parcel in) {
        _id = in.readString();
        firebaseId = in.readString();
        name = in.readString();
        phoneNumber = in.readString();
        langue = in.readString();
        prixSacs = in.createTypedArrayList(PrixSacs.CREATOR);
        champs = in.createTypedArrayList(Champs.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(firebaseId);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(langue);
        dest.writeTypedList(prixSacs);
        dest.writeTypedList(champs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Farmer> CREATOR = new Creator<Farmer>() {
        @Override
        public Farmer createFromParcel(Parcel in) {
            return new Farmer(in);
        }

        @Override
        public Farmer[] newArray(int size) {
            return new Farmer[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
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

    public List<PrixSacs> getPrixSacs() {
        return prixSacs;
    }

    public void setPrixSacs(List<PrixSacs> prixSacs) {
        this.prixSacs = prixSacs;
    }

    public List<Champs> getChamps() {
        return champs;
    }

    public void setChamps(List<Champs> champs) {
        this.champs = champs;
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
        return "Farmer{" +
                "_id='" + _id + '\'' +
                ", firebaseId='" + firebaseId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", langue='" + langue + '\'' +
                ", prixSacs=" + prixSacs +
                ", champs=" + champs +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}
