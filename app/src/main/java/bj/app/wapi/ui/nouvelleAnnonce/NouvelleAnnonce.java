package bj.app.wapi.ui.nouvelleAnnonce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;

public class NouvelleAnnonce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_annonce);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.popBackStack();*/
        startActivity(new Intent(NouvelleAnnonce.this, MainActivity.class)
                .putExtra("NEW_ANNONCE","NEW_ANNONCE"));
    }

}
