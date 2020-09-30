package bj.app.wapi.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import bj.app.wapi.R;
import bj.app.wapi.ui.login.LoginActivity;
import database.DatabaseHelper;
import entityBackend.User;
import storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
//53:A1:9B:90:0F:29:EE:F4:2A:D1:4D:E4:F0:8D:43:C7:D7:0E:E4:86
//03:E4:03:8E:4E:8D:6A:A9:A1:28:ED:86:6F:6C:6A:49:C6:99:5D:A5
//44:44:D1:17:89:90:5D:A3:42:59:87:F3:2A:BC:92:F9:45:7B:59:C7
    FirebaseAuth mAuth;
    public static final int PERMISSION_STORAGE_CODE = 1000;
    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        //navView.setLabelVisibilityMode();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_formation, R.id.navigation_annonce, R.id.navigation_entreprise, R.id.navigation_menage)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (getIntent().hasExtra("NEW_ANNONCE")){
            navController.navigate(R.id.navigation_annonce);
        }else if (getIntent().hasExtra("from_wapi_e_learning")){
            navController.navigate(R.id.navigation_formation);
        }else if (getIntent().hasExtra("from_wapi_mon_exploitation")){
            navController.navigate(R.id.navigation_entreprise);
        }else if (getIntent().hasExtra("from_wapi_business")){
            navController.navigate(R.id.navigation_annonce);
        }

        File[] fileArrayList = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile(), "/Wapi/Formation/Caroussel/TOMATE/apple-2924531_960_720.jpg");
        /*for (File file : fileArrayList){
            if (file.isDirectory()){
                System.out.println("IS DIRECTORY : "+ file.getPath());
                File[] files = file.listFiles();
                for (File filee : files){
                    if (filee.isDirectory())
                        System.out.println("IS A DIRECTORY : "+ filee.getPath());
                    else
                        System.out.println("IS A FILE : "+ filee.getPath());
                }
            }
            else
                System.out.println("IS FILE : "+ file.getPath());
        }*/

        System.out.println("ARE YOU FILE : "+ file.isFile());
        System.out.println("DO YOU EXIST : "+ file.exists());
        System.out.println("ROOT DIRECTORY STORAGE  IS : "+ Environment.getRootDirectory());


        System.out.println("EXTERNAL DIRECTORY STORAGE IS : "+ Environment.getExternalStorageDirectory());
        System.out.println("ROOT DIRECTORY STORAGE  IS : "+ Environment.getRootDirectory());
        System.out.println("DATA DIRECTORY STORAGE  IS : "+ Environment.getDataDirectory());
        System.out.println("DOWNLOAD EXTERNAL PUBLIC STORAGE DIRECTORY IS : "+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        System.out.println("DIRECTORY_DOWNLOADS STORAGE  IS : "+ Environment.DIRECTORY_DOWNLOADS);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        //databaseHelper.saveOneRessource(new Ressource("the path", "the name", "the type", "the formation", "firstImagePath"));

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

            }
        });
    }*/

    /*@Override
    protected void onPause() {
        super.onPause();
        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //LAISSER LE SOIN à onResume DANS LE FRAGMENT FORMATION DE , SUIVANT LA TAB SELECTIONNÉ DE DEMARRER LE SERVICE D'AUDIO LIÉ AU CARROUSSEL
        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
        //OLD CODE
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()){
                    case R.id.navigation_formation:
                        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        //startService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        break;
                    default:
                        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_search:
                //openSearchDialog();
                return true;
            case R.id.menu_deconnexion:
                deconnexion();
                return true;
            case R.id.menu_compte:
                //startActivity(new Intent(MainActivity.this, CompteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearchDialog() {
        Toast.makeText(MainActivity.this,"Faire un recherche de quoi?", Toast.LENGTH_LONG).show();
    }

    private void deconnexion() {
        FirebaseAuth.getInstance().signOut();
        SharedPrefManager.getmInstance(MainActivity.this).clear();
        startActivity(new Intent(MainActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestForpermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForpermission();
    }

    public void requestForpermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(MainActivity.this, "Permission d'accès au stockage externe insdipensable pour la suite ! ...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public boolean choisirLangue(){

        if (SharedPrefManager.getmInstance(MainActivity.this).getUser().getLangue() == null){
            User user = SharedPrefManager.getmInstance(MainActivity.this).getUser();
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Selectionnez une lague que vous comprenez ...");
            CharSequence options [] = new CharSequence[]{
                    getResources().getString(R.string.langueBariba),
                    getResources().getString(R.string.langueBiali),
                    getResources().getString(R.string.langueGourmantche),
                    getResources().getString(R.string.langueMore),
                    getResources().getString(R.string.langueDjerma),
                    getResources().getString(R.string.langueHaoussa)};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if (i == 0){
                        user.setLangue(getResources().getString(R.string.langueBariba));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }else if (i == 1){
                        user.setLangue(getResources().getString(R.string.langueBiali));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }else if (i == 2){
                        user.setLangue(getResources().getString(R.string.langueGourmantche));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }else if (i == 3){
                        user.setLangue(getResources().getString(R.string.langueMore));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }else if (i == 4){
                        user.setLangue(getResources().getString(R.string.langueDjerma));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }else if (i == 5){
                        user.setLangue(getResources().getString(R.string.langueHaoussa));
                        SharedPrefManager.getmInstance(MainActivity.this).clear();
                        SharedPrefManager.getmInstance(MainActivity.this).saveUser(user);
                    }
                    result = false;
                }
            });
            builder.create().show();
        }else {
            Toast.makeText(this, "Sa langue est : "+SharedPrefManager.getmInstance(this).getUser().getLangue(), Toast.LENGTH_LONG).show();
            result = true;
        }

        return result;
    }


}
