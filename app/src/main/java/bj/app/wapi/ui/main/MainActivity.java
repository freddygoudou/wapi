package bj.app.wapi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import bj.app.wapi.R;
import bj.app.wapi.ui.compte.CompteActivity;
import bj.app.wapi.ui.formation.sousFragment.CarousselBackgroundAudioService;
import bj.app.wapi.ui.login.LoginActivity;
import bj.app.wapi.ui.splash.SplashActivity;
import bj.app.wapi.ui.wapi.Wapi;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
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
        }

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

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
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
                        stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        startService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        break;
                    default:
                        stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(MainActivity.this, CarousselBackgroundAudioService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        int id = item.getItemId();
        switch (id){
            case R.id.menu_search:
                openSearchDialog();
            case R.id.menu_deconnexion:
                deconnexion();
                return true;
            case R.id.menu_compte:
                startActivity(new Intent(MainActivity.this, CompteActivity.class));
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
        startActivity(new Intent(MainActivity.this, SplashActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }


}
