package bj.app.wapi;

import adapter.ChampsLocationAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import api.RetrofitClient;
import bj.app.wapi.ui.InfosCulture;
import bj.app.wapi.ui.main.MainActivity;
import entityBackend.Champs;
import entityBackend.ChampsLocation;
import entityBackend.Farmer;
import entityBackend.Recolte;
import entityBackend.SaisonCulture;
import entityBackend.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import storage.SharedPrefManager;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

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
    List<SaisonCulture> saisonCultures;
    boolean exist;
    String oldCulture, newCulture, nomChamps;
    ProgressDialog mProgressDialog;
    Champs champs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_champs);

        //PROGRESS DIALOG
        mProgressDialog = new ProgressDialog(this);

        //VALIDATION DES COORDONNÉES
        btnValiderNewChamps =  findViewById(R.id.btnValiderNewChamps);
        btnValiderNewChamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFarmerOldSaisonExist();
            }
        });

        //AJOUT DE COORDONNÉES
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

        //RECYCLERVIEW DES COORDONNÉES CHOISIES
        recyclerView = findViewById(R.id.rv_coordonnres);
        adapter = new ChampsLocationAdapter(NewChampsActivity.this, mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(NewChampsActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(NewChampsActivity.this));



        // VÉRIFIER SI LE GPS EST ACTIVÉ
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

    //VÉRIFIER SI UNE CULTURE A ÉTÉ FAITE L'ANNÉE PASSÉE SUR LE CHAMPS
    private void checkFarmerOldSaisonExist() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewChampsActivity.this);
        builder.setTitle("Infos sur les cultures");
        builder.setMessage(getString(R.string.ask_exist_old_culture));
        builder.setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                openInfosCultureDialogWithOldSaison();
            }
        });
        builder.setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                openInfosCultureDialogWithoutOldSaison();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //SI UNE CULTURE N'A PAS ÉTÉ FAITE L'ANNÉE PASSÉE, DEMANDER DES RENSEIGNEMENTS SUR LA NOUVELLE CULTURE AINSI QUE LE NOM DU CHAMPS
    private void openInfosCultureDialogWithoutOldSaison() {

        AlertDialog.Builder builder = new AlertDialog.Builder(NewChampsActivity.this);
        builder.setTitle("Infos sur les cultures");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.one_infos_cultures_layout, null);
        builder.setView(dialogView);
        AlertDialog alert = builder.create();

        EditText et_nom_champs = dialogView.findViewById(R.id.et_nom_champs);
        EditText et_new_culture = dialogView.findViewById(R.id.et_new_culture);
        Button btn_valider_one_culture_info = dialogView.findViewById(R.id.btn_valider_one_culture_info);

        btn_valider_one_culture_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCulture = et_new_culture.getText().toString();
                nomChamps = et_nom_champs.getText().toString();
                if((newCulture.length() > 0) && (nomChamps.length()>0)){
                    mProgressDialog.setTitle("Création d'une nouvelle exploitation");
                    mProgressDialog.setMessage("Patientez un instant ...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    saveChamps(nomChamps, null, newCulture, mData, false);
                    alert.dismiss();
                }
            }
        });
        alert.show();
    }

    //SI UNE CULTURE A ÉTÉ FAITE L'ANNÉE PASSÉE, DEMANDER DES RENSEIGNEMENTS SUR LA NOUVELLE CULTURE ET L'ANCIENNE AINSI QUE LE NOM DU CHAMPS
    private void openInfosCultureDialogWithOldSaison() {

        AlertDialog.Builder builder = new AlertDialog.Builder(NewChampsActivity.this);
        builder.setTitle("Infos sur les cultures");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.two_infos_cultures_layout, null);
        builder.setView(dialogView);
        AlertDialog alert = builder.create();

        EditText et_nom_champs = dialogView.findViewById(R.id.et_nom_champs);
        EditText et_old_culture = dialogView.findViewById(R.id.et_old_culture);
        EditText et_new_culture = dialogView.findViewById(R.id.et_new_culture);
        Button btn_valider_two_culture_info = dialogView.findViewById(R.id.btn_valider_two_culture_info);

        btn_valider_two_culture_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldCulture = et_old_culture.getText().toString();
                newCulture = et_new_culture.getText().toString();
                nomChamps = et_nom_champs.getText().toString();
                if( (oldCulture.length() > 0) && (newCulture.length() > 0) && (nomChamps.length()>0)){
                    mProgressDialog.setTitle("Création d'une nouvelle exploitation");
                    mProgressDialog.setMessage("Patientez un instant ...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    saveChamps(nomChamps, oldCulture, newCulture, mData, true);
                    alert.dismiss();
                }
            }
        });

        alert.show();


    }

    //AJOUTER UNE NOUVELLE EXPLOITATION
    private void saveChamps(String nomChamps, String ancienneCulture, String nouvelleCulture, ArrayList<ChampsLocation> champsLocationArrayList, boolean oldChampsExist) {

        ArrayList<SaisonCulture> list;
        User user = SharedPrefManager.getmInstance(NewChampsActivity.this).getUser();
        //Farmer farmer = new Farmer(user, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        if (oldChampsExist){
            list = new ArrayList<>();
            list.add(new SaisonCulture(ancienneCulture,nouvelleCulture));
            champs = new Champs(nomChamps, mData, list);
        }else{
            list = new ArrayList<>();
            list.add(new SaisonCulture("NEANT",nouvelleCulture));
            champs = new Champs(nomChamps, mData, list);
        }

        Call<Champs> call = RetrofitClient
                .getmInstance()
                .getApi()
                .saveChamps(champs);

        call.enqueue(new Callback<Champs>() {
            @Override
            public void onResponse(Call<Champs> call, Response<Champs> response) {
                try {
                    Champs champsReturned;
                    if (response.code() == 200){
                        champsReturned = response.body();
                        if (champsReturned != null){
                            Toast.makeText(NewChampsActivity.this, R.string.champs_creation_succed, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(NewChampsActivity.this, MainActivity.class));
                            mProgressDialog.dismiss();
                        }
                    }else {
                        Toast.makeText(NewChampsActivity.this, "Response code is :"+response.code()+"\n"+" S_Response message "+response.message(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Champs> call, Throwable t) {
                Toast.makeText(NewChampsActivity.this, "Error message "+t.getMessage(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.dismiss();
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
