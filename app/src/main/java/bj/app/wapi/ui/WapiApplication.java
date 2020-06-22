package bj.app.wapi.ui;

import android.app.Application;

import java.util.ArrayList;

import entityBackend.CarrouselFormation;

public class WapiApplication extends Application {

    private ArrayList<CarrouselFormation> carrouselFormations;


    public WapiApplication(ArrayList<CarrouselFormation> carrouselFormations) {
        this.carrouselFormations = carrouselFormations;
    }

    public ArrayList<CarrouselFormation> getCarrouselFormations() {
        return carrouselFormations;
    }

    public void setCarrouselFormations(ArrayList<CarrouselFormation> carrouselFormations) {
        this.carrouselFormations = carrouselFormations;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
