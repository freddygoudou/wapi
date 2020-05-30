package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class SacsPoids implements Parcelable {

    private Long id;
    private Double nombreSacs;
    private Double poids;
    private Date createdAt;
    private Date updatedAt;

    public SacsPoids() {
    }

    public SacsPoids(Long id, Double nombreSacs, Double poids, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nombreSacs = nombreSacs;
        this.poids = poids;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SacsPoids(Double nombreSacs, Double poids) {
        this.nombreSacs = nombreSacs;
        this.poids = poids;
    }

    protected SacsPoids(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            nombreSacs = null;
        } else {
            nombreSacs = in.readDouble();
        }
        if (in.readByte() == 0) {
            poids = null;
        } else {
            poids = in.readDouble();
        }
    }

    public static final Creator<SacsPoids> CREATOR = new Creator<SacsPoids>() {
        @Override
        public SacsPoids createFromParcel(Parcel in) {
            return new SacsPoids(in);
        }

        @Override
        public SacsPoids[] newArray(int size) {
            return new SacsPoids[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNombreSacs() {
        return nombreSacs;
    }

    public void setNombreSacs(Double nombreSacs) {
        this.nombreSacs = nombreSacs;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
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
        if (nombreSacs == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(nombreSacs);
        }
        if (poids == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(poids);
        }
    }

    @Override
    public String toString() {
        return "SacsPoids{" +
                "id=" + id +
                ", nombreSacs=" + nombreSacs +
                ", poids=" + poids +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
