package bj.app.wapi.ui.formation.sousFragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import adapter.AnnonceAdapter;
import adapter.VideoAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.annonce.sousFragment.AchatFragment;
import database.DatabaseHelper;
import entity.Article;
import entity.Ressource;
import entity.Video;


public class VideoFragment extends Fragment {

    RecyclerView recyclerView;
    VideoAdapter adapter;
    ArrayList<Video> mData;
    View root;
    DatabaseHelper databaseHelper;
    ArrayList<Ressource> ressourceArrayList = new ArrayList<>();;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);

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
            //GET FROM API
            mData = new ArrayList<>();
            mData.add(new Video("CAJOUX", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("RIZ", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));  //android.resource://" + "bj.app.wapi/" + R.raw.ecole : FOR LOCAL VIDEO
            mData.add(new Video("TOMATE", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("PIMENT", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("CAROTTE", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("SOJA", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));

        }else {
            //GET FROM LOCAL DB
            mData = new ArrayList<>();
            mData.add(new Video("CAJOUX", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("RIZ", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));  //android.resource://" + "bj.app.wapi/" + R.raw.ecole : FOR LOCAL VIDEO
            mData.add(new Video("TOMATE", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("PIMENT", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("CAROTTE", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));
            mData.add(new Video("SOJA", "Le meilleur d'Afrique", "image url", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4"));

        }

        recyclerView = view.findViewById(R.id.rv_video);
        adapter = new VideoAdapter(getActivity(), VideoFragment.this.getContext(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(VideoFragment.this.getContext()));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
