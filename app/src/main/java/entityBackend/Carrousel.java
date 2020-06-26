package entityBackend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Carrousel implements Parcelable {

    private String  _id;
    private String name;
    private String subname;
    private String url;
    private String jsonfileUri;
    private String description;
    private String langue;
    private ArrayList<CarrouselFormation> carrouselFormations;

    public Carrousel(){}

    public Carrousel(String _id, String name, String subname, String url, String jsonfileUri, String description, String langue, ArrayList<CarrouselFormation> carrouselFormations) {
        this._id = _id;
        this.name = name;
        this.subname = subname;
        this.url = url;
        this.jsonfileUri = jsonfileUri;
        this.description = description;
        this.langue = langue;
        this.carrouselFormations = carrouselFormations;
    }

    public Carrousel(String name, String subname, String url, String jsonfileUri, String description, String langue, ArrayList<CarrouselFormation> carrouselFormations) {
        this.name = name;
        this.subname = subname;
        this.url = url;
        this.jsonfileUri = jsonfileUri;
        this.description = description;
        this.langue = langue;
        this.carrouselFormations = carrouselFormations;
    }

    public String getJsonfileUri() {
        return jsonfileUri;
    }

    public void setJsonfileUri(String jsonfileUri) {
        this.jsonfileUri = jsonfileUri;
    }

    public Carrousel(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public ArrayList<CarrouselFormation> getCarrouselFormations() {
        return carrouselFormations;
    }

    public void setCarrouselFormations(ArrayList<CarrouselFormation> carrouselFormations) {
        this.carrouselFormations = carrouselFormations;
    }

    @Override
    public String toString() {
        return "Carrousel{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", subname='" + subname + '\'' +
                ", url='" + url + '\'' +
                ", jsonfileUri='" + jsonfileUri + '\'' +
                ", description='" + description + '\'' +
                ", langue='" + langue + '\'' +
                ", carrouselFormations=" + carrouselFormations +
                '}';
    }

    public String toJsonString() {
        return "{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", subname='" + subname + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", langue='" + langue + '\'' +
                ", carrouselFormations=" + carrouselFormations.toString() +
                '}';
    }



    protected Carrousel(Parcel in) {
        _id = in.readString();
        name = in.readString();
        subname = in.readString();
        url = in.readString();
        description = in.readString();
        langue = in.readString();
        carrouselFormations = in.createTypedArrayList(CarrouselFormation.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(subname);
        dest.writeString(url);
        dest.writeString(description);
        dest.writeString(langue);
        carrouselFormations = new ArrayList<>();
        dest.writeTypedList(carrouselFormations);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carrousel> CREATOR = new Creator<Carrousel>() {
        @Override
        public Carrousel createFromParcel(Parcel in) {
            return new Carrousel(in);
        }

        @Override
        public Carrousel[] newArray(int size) {
            return new Carrousel[size];
        }
    };
}

