package bj.app.wapi.ui.wapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import bj.app.wapi.R;

import android.os.Bundle;

public class Wapi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wapi);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_login, R.id.navigation_register, R.id.navigation_confirm_code, R.id.navigation_register_user_form, R.id.navigation_main)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.wapi_host_fragment);

    }
}
