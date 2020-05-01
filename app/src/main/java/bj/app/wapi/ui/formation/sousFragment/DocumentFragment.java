package bj.app.wapi.ui.formation.sousFragment;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import adapter.AnnonceAdapter;
import adapter.DocumentAdapter;
import adapter.SliderAdapter;
import adapter.VideoAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import bj.app.wapi.R;
import bj.app.wapi.ui.annonce.sousFragment.AchatFragment;
import database.DatabaseHelper;
import entity.Article;
import entity.Document;
import entity.Ressource;
import entity.SlideItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment {


    RecyclerView recyclerView;
    DocumentAdapter adapter;
    ArrayList<Document> mData = new ArrayList<>();;
    CarouselView carouselView;
    ArrayList<SlideItem> slideItemList;
    DatabaseHelper databaseHelper;
    ArrayList<Ressource> ressourceArrayList = new ArrayList<>();;


    public DocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_document, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseHelper = new DatabaseHelper(getActivity());
        ressourceArrayList = databaseHelper.getAllCaroussels();
    }

    //CHARGER LA LISTE ICI SUIVANT LA CONNEXION INTERNET
    @Override
    public void onStart() {
        super.onStart();
        databaseHelper = new DatabaseHelper(getActivity());
        ressourceArrayList = databaseHelper.getAllCaroussels();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getActivity());

        if (isNetworkConnected()){
            //GET RESSOURCES FROM API
            slideItemList = new ArrayList<>();
            slideItemList.add(new SlideItem(R.drawable.voiture1, "https://singemp3.com/telechargement-mp3/7192721/quand-je-taime"));
            slideItemList.add(new SlideItem(R.drawable.voiture2, "https://singemp3.com/telechargement-mp3/6598721/titanic"));
            slideItemList.add(new SlideItem(R.drawable.voiture3, "https://singemp3.com/telechargement-mp3/16591169/rossignol-singuila"));

            //RecycleerView
            recyclerView = view.findViewById(R.id.rv_document);
            mData.add(new Document("CAJOUX", "Le meilleur d'Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("RIZ", "Le meilleur d'Afrique", R.drawable.wapibaobabpoudre));
            mData.add(new Document("TOMATE", "Le meilleur d'Afrique", R.drawable.wapitransdetarium));
            mData.add(new Document("PIMENT", "Le meilleur d'Afrique", R.drawable.wapihuilebaobab));
            mData.add(new Document("CAROTTE", "Le meilleur d'Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("SOJA", "Le meilleur d'Afrique", R.drawable.wapihuilebaobab));
        }else {
            //GET RESSOURCES FROM LOCAL DB
            slideItemList = new ArrayList<>();
            slideItemList.add(new SlideItem(R.drawable.voiture1, "https://singemp3.com/telechargement-mp3/7192721/quand-je-taime"));
            slideItemList.add(new SlideItem(R.drawable.voiture2, "https://singemp3.com/telechargement-mp3/6598721/titanic"));
            slideItemList.add(new SlideItem(R.drawable.voiture3, "https://singemp3.com/telechargement-mp3/16591169/rossignol-singuila"));

            //RecycleerView
            recyclerView = view.findViewById(R.id.rv_document);
            mData.add(new Document("CAJOUX", "Le meilleur d'Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("RIZ", "Le meilleur d'Afrique", R.drawable.wapibaobabpoudre));
            mData.add(new Document("TOMATE", "Le meilleur d'Afrique", R.drawable.wapitransdetarium));
            mData.add(new Document("PIMENT", "Le meilleur d'Afrique", R.drawable.wapihuilebaobab));
            mData.add(new Document("CAROTTE", "Le meilleur d'Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("SOJA", "Le meilleur d'Afrique", R.drawable.wapihuilebaobab));
        }


        adapter = new DocumentAdapter(DocumentFragment.this.getContext(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(DocumentFragment.this.getContext()));

    }



    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(slideItemList.get(position).getImage());
        }
    };


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
