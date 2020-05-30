package bj.app.wapi.ui.entreprise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.ExploitationAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.NewChampsActivity;
import bj.app.wapi.R;
import entityBackend.Champs;
import entityBackend.ChampsLocation;
import entityBackend.Depense;
import entityBackend.Employee;
import entityBackend.SaisonCulture;

public class EntrepriseFragment extends Fragment {

    RecyclerView recyclerView;
    ExploitationAdapter adapter;
    ArrayList<Champs> mData = new ArrayList<>();
    ArrayList<ChampsLocation> mDataLoacation = new ArrayList<>();
    ArrayList<SaisonCulture> mDataSaison = new ArrayList<>();
    ArrayList<Employee> mDataEmployee = new ArrayList<>();
    ArrayList<Depense> mDataDepense = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    @Override
    public void onStart() {
        super.onStart();
        loadRessoucesChamps();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entreprise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = view.findViewById(R.id.fab_exploitation);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewChampsActivity.class));
            }
        });

        recyclerView = view.findViewById(R.id.rv_exploitation);
        adapter = new ExploitationAdapter(EntrepriseFragment.this.getActivity(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(EntrepriseFragment.this.getContext()));

        loadRessoucesChamps();
    }

    private void loadRessoucesChamps() {

        /*mDataLoacation = new ArrayList<>();
        mDataEmployee = new ArrayList<>();
        mDataDepense = new ArrayList<>();
        mDataSaison = new ArrayList<>();
        mData = new ArrayList<>();*/

        mDataLoacation.add(new ChampsLocation(10.33, 12.558));
        mDataLoacation.add(new ChampsLocation(10.33, 12.558));
        mDataLoacation.add(new ChampsLocation(10.33, 12.558));
        mDataLoacation.add(new ChampsLocation(10.33, 12.558));

        /*mDataEmployee.add(new Employee(1L, "Marc Landers",new ArrayList<>()));
        mDataEmployee.add(new Employee(1L, "Tom Landers", new ArrayList<>()));
        mDataEmployee.add(new Employee(1L, "Jimy Landers", new ArrayList<>()));
        mDataEmployee.add(new Employee(1L, "Paul Landers", new ArrayList<>()));

        mDataDepense.add(new Depense(1L,"120000","Pour achat d'intrant"));
        mDataDepense.add(new Depense(1L,"20000","Pour achat de sacs"));
        mDataDepense.add(new Depense(1L,"170000","Pour achat de produit de culture"));

        mDataSaison.add(new SaisonCulture(1L, "Tomate","2020-2021",mDataDepense));
        mDataSaison.add(new SaisonCulture(1L, "Piment","2019-2020",mDataDepense));
        mDataSaison.add(new SaisonCulture(1L, "Pomme","2018-2019",mDataDepense));
        mDataSaison.add(new SaisonCulture(1L, "Patate","2017-2018",mDataDepense));*/

        mData.add(new Champs("Cocotomey",false, "Bientôt"));
        mData.add(new Champs("Ouidah",false, "Dans deux jours"));
        mData.add(new Champs("Akpakpa",false, "En juin"));
        mData.add(new Champs("Zè",false, "Le 15 mai"));

        adapter.notifyDataSetChanged();

    }
}