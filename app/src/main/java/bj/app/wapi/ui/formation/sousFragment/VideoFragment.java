package bj.app.wapi.ui.formation.sousFragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import adapter.VideoAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import database.DatabaseHelper;
import entityBackend.Video;
import entity.Videooo;


public class VideoFragment extends Fragment {

    RecyclerView recyclerView;
    VideoAdapter adapter;
    ArrayList<Videooo> mData;
    View root;
    DatabaseHelper databaseHelper;
    ArrayList<Video> ressourceArrayList = new ArrayList<>();

    //String test1="https://firebasestorage.googleapis.com/v0/b/wegoofirebase.appspot.com/o/images%2Fuser_profile%2FG8OOdW2PsffLjNuJ0iXGnywr2v43.jpg?alt=media&token=00339afd-4b57-4a5e-8e7c-5562b4ee68dd";
    //String text2="https://firebasestorage.googleapis.com/v0/b/wegoofirebase.appspot.com/o/images%2Fuser_profile%2F1obC08IjG7dj9SqPa8QNYBvGRek2.jpg?alt=media&token=5de96950-83a3-4c71-821f-6110f58a3875";

    //String test1="https://i.postimg.cc/qMwgc348/Logo.png";
    //String text2="https://upload.wikimedia.org/wikipedia/commons/2/2d/Snake_River_%285mb%29.jpg";

    String test1="https://i.postimg.cc/qMwgc348/Logo.png";
    String text2="https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg";

    String test4 ="https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_video, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadVideos();
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadVideos();

        recyclerView = view.findViewById(R.id.rv_video);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(VideoFragment.this.getContext()));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void loadVideos(){

        ressourceArrayList = new ArrayList<>();

        if (isNetworkConnected()){
            //GET FROM API
            /*mData = new ArrayList<>();
            mData.add(new Videooo("CAJOUX", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("RIZ", "Le meilleur d Afrique", "image url", test4));  //android.resource://" + "bj.app.wapi/" + R.raw.ecole : FOR LOCAL VIDEO
            mData.add(new Videooo("TOMATE", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("PIMENT", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("CAROTTE", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("SOJA", "Le meilleur d Afrique", "image url", test4));*/
            ressourceArrayList.add(new Video("CAJOUX", "","Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video("RIZ", test4,"Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video("TOMATE", test4,"Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video("PIMENT", test4,"Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video("CAROTTE", test4,"Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video("SOJA", test4,"Le meilleur d Afrique", text2));

            adapter = new VideoAdapter(VideoFragment.this.getContext(), ressourceArrayList, true);
            adapter.notifyDataSetChanged();
        }else {

            databaseHelper = new DatabaseHelper(getActivity());
            //GET FROM LOCAL DB
            /*mData = new ArrayList<>();
            mData.add(new Videooo("CAJOUX", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("RIZ", "Le meilleur d Afrique", "image url", test4));  //android.resource://" + "bj.app.wapi/" + R.raw.ecole : FOR LOCAL VIDEO
            mData.add(new Videooo("TOMATE", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("PIMENT", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("CAROTTE", "Le meilleur d Afrique", "image url", test4));
            mData.add(new Videooo("SOJA", "Le meilleur d Afrique", "image url", test4));*/
            ressourceArrayList = databaseHelper.getAllVideos();

            /*ressourceArrayList.add(new Video(1,"CAJOUX", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4;https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", test1));
            ressourceArrayList.add(new Video(1,"RIZ", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video(1,"TOMATE", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", test1));
            ressourceArrayList.add(new Video(1,"PIMENT", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", text2));
            ressourceArrayList.add(new Video(1,"CAROTTE", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", test1));
            ressourceArrayList.add(new Video(1,"SOJA", "https://www.radiantmediaplayer.com/media/bbb-360p.mp4","Le meilleur d Afrique", text2));*/

            adapter = new VideoAdapter(VideoFragment.this.getContext(), ressourceArrayList, false);
            adapter.notifyDataSetChanged();
        }
        System.out.println("V LIST IS : "+ressourceArrayList.toString());

    }

}
