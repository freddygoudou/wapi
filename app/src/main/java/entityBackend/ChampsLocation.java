package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ChampsLocation implements Parcelable {

    private Long id;
    private Double longitutde;
    private Double latitude;

    public ChampsLocation() {
    }

    public ChampsLocation(Long id, Double longitutde, Double latitude) {
        this.id = id;
        this.longitutde = longitutde;
        this.latitude = latitude;
    }


    public ChampsLocation(Double longitutde, Double latitude) {
        this.longitutde = longitutde;
        this.latitude = latitude;
    }


    protected ChampsLocation(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            longitutde = null;
        } else {
            longitutde = in.readDouble();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
    }

    public static final Creator<ChampsLocation> CREATOR = new Creator<ChampsLocation>() {
        @Override
        public ChampsLocation createFromParcel(Parcel in) {
            return new ChampsLocation(in);
        }

        @Override
        public ChampsLocation[] newArray(int size) {
            return new ChampsLocation[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(Double longitutde) {
        this.longitutde = longitutde;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
        if (longitutde == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longitutde);
        }
        if (latitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitude);
        }
    }
}




/*
package entityBackend;

        import android.os.Parcel;
        import android.os.Parcelable;

        import java.util.Date;

public class ChampsLocation implements Parcelable {

    private Long id;
    private Double longitutde;
    private Double latitude;
    private Date createdAt;
    private Date updatedAt;

    public ChampsLocation() {
    }

    public ChampsLocation(Long id, Double longitutde, Double latitude, Date createdAt, Date updatedAt) {
        this.id = id;
        this.longitutde = longitutde;
        this.latitude = latitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ChampsLocation(Double longitutde, Double latitude) {
        this.longitutde = longitutde;
        this.latitude = latitude;
    }


    protected ChampsLocation(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            longitutde = null;
        } else {
            longitutde = in.readDouble();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
    }

    public static final Creator<ChampsLocation> CREATOR = new Creator<ChampsLocation>() {
        @Override
        public ChampsLocation createFromParcel(Parcel in) {
            return new ChampsLocation(in);
        }

        @Override
        public ChampsLocation[] newArray(int size) {
            return new ChampsLocation[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(Double longitutde) {
        this.longitutde = longitutde;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "ChampsLocation{" +
                "id=" + id +
                ", longitutde=" + longitutde +
                ", latitude=" + latitude +
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
        if (longitutde == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longitutde);
        }
        if (latitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitude);
        }
    }
}
*/
