package entity;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;

public class FileAndExtention {

    private String location;
    private String  extention;


    public FileAndExtention(String location, String extention) {
        this.location = location;
        this.extention = extention;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return "FileAndExtention{" +
                "location='" + location + '\'' +
                ", extention='" + extention + '\'' +
                '}';
    }
}

