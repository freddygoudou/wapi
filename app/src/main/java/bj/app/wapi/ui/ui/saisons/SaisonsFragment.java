package bj.app.wapi.ui.ui.saisons;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.SaisonAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entityBackend.Recolte;
import entityBackend.SaisonCulture;

public class SaisonsFragment extends Fragment {

    RecyclerView rv_saisons;
    FloatingActionButton fab_saisons;
    SaisonAdapter adapter;
    ArrayList<SaisonCulture> mData = new ArrayList<>();
    String nomCulture, dateSemie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_saisons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_saisons = view.findViewById(R.id.rv_saisons);



        fab_saisons = view.findViewById(R.id.fab_saisons);

        fab_saisons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSaisonDialog();
            }
        });

        loadResources();
    }

    private void loadResources() {

        mData.add(new SaisonCulture("Manioc","12/12/12",true, new ArrayList<>(),new Recolte()));
        mData.add(new SaisonCulture("Tomate","12/02/12",true, new ArrayList<>(),new Recolte()));
        mData.add(new SaisonCulture("Piment","01/12/11",true, new ArrayList<>(),new Recolte()));
        mData.add(new SaisonCulture("Cajoux","17/06/12",true, new ArrayList<>(),new Recolte()));

        adapter = new SaisonAdapter(SaisonsFragment.this.getActivity(), mData);
        rv_saisons.setAdapter(adapter);
        rv_saisons.setLayoutManager(new LinearLayoutManager(SaisonsFragment.this.getContext()));
        adapter.notifyDataSetChanged();
    }

    private void openAddSaisonDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ajouter un employé");

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.add_saison_layout, null);
        builder.setView(dialogView);
        AlertDialog alert = builder.create();

        EditText et_nom_culture = dialogView.findViewById(R.id.et_nom_culture);
        EditText et_date_semie = dialogView.findViewById(R.id.et_date_semie);
        Button btn_valider = dialogView.findViewById(R.id.btn_valider);

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomCulture = et_nom_culture.getText().toString();
                dateSemie = et_date_semie.getText().toString();
                if((nomCulture.length() > 0) && (dateSemie.length()>0)){
                    /*mProgressDialog.setTitle("Création d'une nouvelle exploitation");
                    mProgressDialog.setMessage("Patientez un instant ...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    saveChamps(nomChamps, null, newCulture, mData, false);*/
                    alert.dismiss();
                    saveSaison(nomCulture, dateSemie);
                }
            }
        });
        alert.show();
    }

    private void saveSaison(String nomCulture, String dateSemie) {
    }
}
