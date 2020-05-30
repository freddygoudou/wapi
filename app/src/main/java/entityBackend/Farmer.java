package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Farmer implements Parcelable {
    private Long id;
    private User user;
    private List<PrixSacs> prixSacs;
    private List<Recolte> recoltes;
    private List<Champs> champs;
    private Date createdAt;
    private Date updatedAt;

    public Farmer() {
    }

    public Farmer(Long id, User user, List<PrixSacs> prixSacs, List<Recolte> recoltes, List<Champs> champs, Date createdAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.prixSacs = prixSacs;
        this.recoltes = recoltes;
        this.champs = champs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Farmer(User user, List<PrixSacs> prixSacs, List<Recolte> recoltes, List<Champs> champs) {
        this.user = user;
        this.prixSacs = prixSacs;
        this.recoltes = recoltes;
        this.champs = champs;
    }

    protected Farmer(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        user = in.readParcelable(User.class.getClassLoader());
        prixSacs = in.createTypedArrayList(PrixSacs.CREATOR);
        recoltes = in.createTypedArrayList(Recolte.CREATOR);
        champs = in.createTypedArrayList(Champs.CREATOR);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PrixSacs> getPrixSacs() {
        return prixSacs;
    }

    public void setPrixSacs(List<PrixSacs> prixSacs) {
        this.prixSacs = prixSacs;
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
        parcel.writeParcelable(user, i);
        parcel.writeTypedList(prixSacs);
        parcel.writeTypedList(recoltes);
        parcel.writeTypedList(champs);
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", user=" + user +
                ", prixSacs=" + prixSacs.toString() +
                ", recoltes=" + recoltes.toString() +
                ", champs=" + champs.toString() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
