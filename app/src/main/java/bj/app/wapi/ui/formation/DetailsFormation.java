package bj.app.wapi.ui.formation;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.CarousselBackgroundAudioService;
import entity.SlideItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class DetailsFormation extends AppCompatActivity {

    CarouselView carouselView;
    ArrayList<SlideItem> slideItemList;
    TextView tvNomFormationn, tvDescriptionFormation;
    String videoDescription, videoTitle;



    @Override
    protected void onStop() {
        super.onStop();
        //Arrèter l'audio
        stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carouselView.setCurrentItem(0, true);
        carouselView.playCarousel();
        stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
        startService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_formation);

        if (getIntent().hasExtra("videoTitle") && getIntent().hasExtra("videoDescription")){
            videoTitle = getIntent().getStringExtra("videoTitle");
            videoDescription = getIntent().getStringExtra("videoDescription");
        }

        slideItemList = new ArrayList<>();
        slideItemList.add(new SlideItem(R.drawable.voiture1, "https://singemp3.com/telechargement-mp3/7192721/quand-je-taime"));
        slideItemList.add(new SlideItem(R.drawable.voiture2, "https://singemp3.com/telechargement-mp3/6598721/titanic"));
        slideItemList.add(new SlideItem(R.drawable.voiture3, "https://singemp3.com/telechargement-mp3/16591169/rossignol-singuila"));

        tvDescriptionFormation = findViewById(R.id.tvDescriptionFormation);
        tvNomFormationn = findViewById(R.id.tvNomFormation);

        tvNomFormationn.setText(videoTitle);
        tvDescriptionFormation.setText(videoDescription);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(slideItemList.size());
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(10000);
        carouselView.setCurrentItem(0, true);
        carouselView.playCarousel();

        //Démarer l'audio
        stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
        startService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(slideItemList.get(position).getImage());
        }
    };
}
