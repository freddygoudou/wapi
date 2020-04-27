package bj.app.wapi.ui.annonce.sousFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import adapter.AnnonceAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.Article;

public class VenteFragment extends Fragment {

    View root;
    RecyclerView recyclerView;
    AnnonceAdapter adapter;
    ArrayList<Article> mData;
    String VENTE = "VENTE";
    String QUANTITE = "Quantité";
    String DATE = "Date";

    public VenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_vente);
        mData = new ArrayList<>();
        mData.add(new Article(VENTE+"-"+"CAJOUX", QUANTITE+": "+"33 Tonnes", DATE+": 2020-04-20", VENTE));
        mData.add(new Article(VENTE+"-"+"RIZ", QUANTITE+": "+"21 Tonnes", DATE+": 2020-04-20", VENTE));
        mData.add(new Article(VENTE+"-"+"POMME DE TERRE", QUANTITE+": "+"17 Tonnes", DATE+": 2020-04-22", VENTE));
        mData.add(new Article(VENTE+"-"+"HARICOT", QUANTITE+": "+"8 Tonnes", DATE+": 2020-04-21", VENTE));
        mData.add(new Article(VENTE+"-"+"MANIOC", QUANTITE+": "+"12 Tonnes", DATE+": 2020-04-19", VENTE));
        mData.add(new Article(VENTE+"-"+"SOJA", QUANTITE+": "+"11 Tonnes", DATE+": 2020-04-18", VENTE));
        mData.add(new Article(VENTE+"-"+"SORGHO", QUANTITE+": "+"10.5 Tonnes", DATE+": 2020-04-17", VENTE));
        mData.add(new Article(VENTE+"-"+"MIL", QUANTITE+": "+"22 Tonnes", DATE+": 2020-04-16", VENTE));
        mData.add(new Article(VENTE+"-"+"TOMATE", QUANTITE+": "+"33 Tonnes", DATE+": 2020-04-15", VENTE));
        mData.add(new Article(VENTE+"-"+"PIMENT", QUANTITE+": "+"5 Tonnes", DATE+": 2020-04-14", VENTE));


        adapter = new AnnonceAdapter(VenteFragment.this.getContext(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(VenteFragment.this.getContext()));

    }
}
