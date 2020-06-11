package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class SacsPoids implements Parcelable {

    private String _id;
    private Double nombreSacs;
    private Double poids;

    public SacsPoids() {
    }

    public SacsPoids(String id, Double nombreSacs, Double poids) {
        this._id = id;
        this.nombreSacs = nombreSacs;
        this.poids = poids;
    }

    public SacsPoids(Double nombreSacs, Double poids) {
        this.nombreSacs = nombreSacs;
        this.poids = poids;
    }


    protected SacsPoids(Parcel in) {
        _id = in.readString();
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

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    }


    @Override
    public String toString() {
        return "SacsPoids{" +
                "id=" + _id +
                ", nombreSacs=" + nombreSacs +
                ", poids=" + poids +
                '}';
    }
}
