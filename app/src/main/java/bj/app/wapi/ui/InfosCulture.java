package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;
import entityBackend.Champs;
import entityBackend.ChampsLocation;
import entityBackend.SaisonCulture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InfosCulture extends AppCompatActivity {

    EditText et_ancienne_culture, et_nouvelle_culture;
    Button btnValiderCultureInfos;
    ArrayList<ChampsLocation> mData = new ArrayList<>();
    Champs champs;
    SaisonCulture ancienneCulture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_culture);

        if(getIntent().hasExtra("listeCoordonnees")){
            mData = getIntent().getParcelableArrayListExtra("listeCoordonnees");
            System.out.println("Voici la liste "+ mData.toString());
        }
        et_ancienne_culture = findViewById(R.id.et_ancienne_culture);
        et_ancienne_culture = findViewById(R.id.et_ancienne_culture);
        btnValiderCultureInfos = findViewById(R.id.btnValiderCultureInfos);

        btnValiderCultureInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InfosCulture.this, "Création d'une nouvelle exploitation avec succès champs", Toast.LENGTH_LONG).show();
            }
        });
    }

}
