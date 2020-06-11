package bj.app.wapi.ui;

import adapter.EmployeeAdapter;
import adapter.LocationAdapter;
import adapter.SaisonAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entityBackend.Champs;
import entityBackend.ChampsLocation;
import entityBackend.Employee;
import entityBackend.SaisonCulture;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChampsDetailsActivity extends AppCompatActivity {

    Champs champs;
    TextView tvChampsActif, tvDelaiActivation;
    RecyclerView rvEmployee, rvSaisonCulture, rvChampsLocation;
    List<Employee> employees;
    List<ChampsLocation> locations;
    List<SaisonCulture> saisonCultures;
    EmployeeAdapter adapterEmployee;
    LocationAdapter locationAdapter;
    SaisonAdapter saisonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champs_details);

        if(getIntent().hasExtra("champs")){

            champs = getIntent().getParcelableExtra("champs");
            tvChampsActif = findViewById(R.id.tv_is_active);
            tvDelaiActivation = findViewById(R.id.tv_delaie_is_active);

            if (champs.isActive()){
                tvChampsActif.setText(R.string.champs_actif);
                tvDelaiActivation.setText("");
            }else {
                tvChampsActif.setText(R.string.champs_inactif);
                tvChampsActif.setText(champs.getDelaieReativite());
            }



            employees = champs.getEmployees();
            locations = champs.getChampsLocations();
            saisonCultures = champs.getSaisonCultures();

            /*employees = champs.getEmployees();
            locations = champs.getChampsLocations();
            saisonCultures = champs.getSaisonCultures();*/

            rvEmployee = findViewById(R.id.rv_employe_list);
            rvSaisonCulture = findViewById(R.id.rv_saison_list);
            rvChampsLocation = findViewById(R.id.rv_location_list);

            adapterEmployee = new EmployeeAdapter(ChampsDetailsActivity.this, employees);
            locationAdapter = new LocationAdapter(ChampsDetailsActivity.this, locations);
            saisonAdapter = new SaisonAdapter(ChampsDetailsActivity.this, saisonCultures);

            rvEmployee.setAdapter(adapterEmployee);
            rvChampsLocation.setAdapter(locationAdapter);
            rvSaisonCulture.setAdapter(saisonAdapter);

            rvEmployee.setLayoutManager(new GridLayoutManager(ChampsDetailsActivity.this,1, LinearLayoutManager.HORIZONTAL,false));
            rvChampsLocation.setLayoutManager(new GridLayoutManager(ChampsDetailsActivity.this,1, LinearLayoutManager.VERTICAL,false));
            rvSaisonCulture.setLayoutManager(new GridLayoutManager(ChampsDetailsActivity.this,1, LinearLayoutManager.HORIZONTAL,false));

        }
    }
}
