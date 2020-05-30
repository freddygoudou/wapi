package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import entityBackend.ChampsLocation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InfosCulture extends AppCompatActivity {

    EditText et_ancienne_culture, et_nouvelle_culture;
    Button btnValiderCultureInfos;
    ArrayList<ChampsLocation> mData = new ArrayList<>();

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

    public void saveChamps(String ancienneCulture, String nouvelleCulture, ArrayList<ChampsLocation> champsLocationArrayList){

    }
}
