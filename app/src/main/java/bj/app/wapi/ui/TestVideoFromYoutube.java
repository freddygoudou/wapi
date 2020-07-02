package bj.app.wapi.ui;

import adapter.VideoAdapter;
import adapter.VideoAdapterYoutube;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.VideoFragment;
import entity.VideoYoutube;
import entityBackend.Video;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class TestVideoFromYoutube extends AppCompatActivity {

    ArrayList<VideoYoutube> ressourceArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    VideoAdapterYoutube adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video_from_youtube);


        ressourceArrayList.add(new VideoYoutube("", "", "qS01oRF1U1k", "","")); //
        ressourceArrayList.add(new VideoYoutube("", "", "KZDJmFSTnVw", "",""));
        ressourceArrayList.add(new VideoYoutube("", "", "KTKnE2aY4qI", "",""));
        ressourceArrayList.add(new VideoYoutube("", "", "KG3__qwQEkg", "",""));
        ressourceArrayList.add(new VideoYoutube("", "", "wJa4YB_PFVo", "",""));

        recyclerView = findViewById(R.id.rv_youtube_video);
        adapter = new VideoAdapterYoutube(TestVideoFromYoutube.this, ressourceArrayList, true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TestVideoFromYoutube.this));

       /* ytbvidList.add(YtbVid(id:'1', title:'Engrais Organiques', resume:'Vous apprendrez comment produire des engrais organiques', link:'https://www.youtube.com/watch?v=qS01oRF1U1k'))
      .add(YtbVid(id:'1', title:'Plantules de Baobab', resume:'Vous apprendrez comment faire et suivre les plantules de Baobab', link:'https://www.youtube.com/watch?v=KZDJmFSTnVw'))
      .add(YtbVid(id:'1', title:'Mung Bean', resume:'Préparation à bas de Mung Bean', link:'https://www.youtube.com/watch?v=KTKnE2aY4qI'))
      .add(YtbVid(id:'1', title:'Feuilles de Baobab', resume:'DIfférentes techniques de séchage des feuiles de Baobab', link:'https://www.youtube.com/watch?v=KG3__qwQEkg'))
      .add(YtbVid(id:'1', title:'Baobab', resume:'Utilité des produits dérivés de Baobab', link:'https://www.youtube.com/watch?v=wJa4YB_PFVo&t=42s'));
        */
    }

}
