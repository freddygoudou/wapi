package bj.app.wapi.ui.annonce.sousFragment;


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


public class AchatFragment extends Fragment {


    RecyclerView recyclerView;
    AnnonceAdapter adapter;
    ArrayList<Article> mData;
    View root;

    String ACHAT = "ACHAT";
    String QUANTITE = "Quantité";
    String DATE = "Date";

    public AchatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_achat);
        mData = new ArrayList<>();
        mData.add(new Article(ACHAT+"-"+"CAJOUX", QUANTITE+": "+"33 Tonnes", DATE+": 2020-04-20"));
        mData.add(new Article(ACHAT+"-"+"RIZ", QUANTITE+": "+"21 Tonnes", DATE+": 2020-04-20"));
        mData.add(new Article(ACHAT+"-"+"POMME DE TERRE", QUANTITE+": "+"17 Tonnes", DATE+": 2020-04-22"));
        mData.add(new Article(ACHAT+"-"+"HARICOT", QUANTITE+": "+"8 Tonnes", DATE+": 2020-04-21"));
        mData.add(new Article(ACHAT+"-"+"MANIOC", QUANTITE+": "+"12 Tonnes", DATE+": 2020-04-19"));
        mData.add(new Article(ACHAT+"-"+"SOJA", QUANTITE+": "+"11 Tonnes", DATE+": 2020-04-18"));
        mData.add(new Article(ACHAT+"-"+"SORGHO", QUANTITE+": "+"10.5 Tonnes", DATE+": 2020-04-17"));
        mData.add(new Article(ACHAT+"-"+"MIL", QUANTITE+": "+"22 Tonnes", DATE+": 2020-04-16"));
        mData.add(new Article(ACHAT+"-"+"TOMATE", QUANTITE+": "+"33 Tonnes", DATE+": 2020-04-15"));
        mData.add(new Article(ACHAT+"-"+"PIMENT", QUANTITE+": "+"5 Tonnes", DATE+": 2020-04-14"));


        adapter = new AnnonceAdapter(AchatFragment.this.getContext(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(AchatFragment.this.getContext()));

    }
}
