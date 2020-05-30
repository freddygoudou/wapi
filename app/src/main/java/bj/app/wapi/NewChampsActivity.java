package bj.app.wapi;

import adapter.ChampsLocationAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.ui.InfosCulture;
import entityBackend.ChampsLocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;

public class NewChampsActivity extends AppCompatActivity implements ChampsLocationAdapter.UiInterface{

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    Location mLocation;
    GoogleApiClient mGoogleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private long UPDATE_INTERVAL = 4000;
    private double posLat=1.0, posLong=1.0;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private boolean isCoordFetch = false;

    Button btnValiderNewChamps;
    LinearLayout llAddCoordonnees;
    RecyclerView recyclerView;
    ChampsLocationAdapter adapter;
    ArrayList<ChampsLocation> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_champs);

        /*locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);*/

        isCoordFetch = false;

        btnValiderNewChamps =  findViewById(R.id.btnValiderNewChamps);
        btnValiderNewChamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewChampsActivity.this, InfosCulture.class)
                    .putExtra("listeCoordonnees", mData));
            }
        });

        llAddCoordonnees = findViewById(R.id.ll_add_coordonnees);
        llAddCoordonnees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true){ //isCoordGpsFetch()
                    //double tab[] = currentPosition();
                    //mData.add(new ChampsLocation(tab[0],tab[1]));
                    mData.add(new ChampsLocation(1.33367, 56.1211));
                    adapter.notifyDataSetChanged();
                    if (mData.size()>=3)
                        btnValiderNewChamps.setVisibility(View.VISIBLE);
                    else
                        btnValiderNewChamps.setVisibility(View.INVISIBLE);
                }else {
                    Toast.makeText(NewChampsActivity.this, "Position not fetched ... lat = "+posLat+" long = "+posLong, Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView = findViewById(R.id.rv_coordonnres);
        adapter = new ChampsLocationAdapter(NewChampsActivity.this, mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(NewChampsActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(NewChampsActivity.this));



        // Vérifier si le GPS est activé
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Bien ! Le GPS est activé.", Toast.LENGTH_SHORT).show();
        }else{
            showGPSDisabledAlertToUser();
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                posLat = location.getLatitude();
                posLong = location.getLongitude();
                isCoordFetch = true;
                Log.v("LOCATION", "IN ON LOCATION CHANGE, lat=" + posLat + ", lon=" + posLong);
                Toast.makeText(NewChampsActivity.this, "Longitude : "+posLong+" et Latitude : "+posLat, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(NewChampsActivity.this, "Prêt pour fetch data GPS", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        };

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
                return;
            }
        }else{
            currentPositionByGps();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case 10:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    currentPositionByGps();
                return;
        }
    }

    public void currentPositionByGps() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
                return ;
            }
            else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL,
                        0, locationListener);
            }
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL,
                    0, locationListener);
        }

    }

    public boolean isCoordGpsFetch(){
        return isCoordFetch;
    }

    private boolean checkPermissionACCESS_LOCATION(final Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
                return false;
            }
            else{
                return true;
            }
        }else{
            return true;
        }
    }


    public double[] currentPosition(){
        double tab[] = {posLat, posLong};
        return tab;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Votre GPS est désactivé. Veuillez l'activer " +
                "afin d'utiliser certaines fonctionnalités de l'application ")
                .setCancelable(false)
                .setPositiveButton("Activer le GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Ignorer",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void updateActivityUI(int size) {
        if (size>=3)
            btnValiderNewChamps.setVisibility(View.VISIBLE);
        else
            btnValiderNewChamps.setVisibility(View.INVISIBLE);
    }
}
