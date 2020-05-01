package bj.app.wapi.ui.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.CarousselBackgroundAudioService;
import entity.SlideItem;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.core.Context;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class DetailsFormation extends AppCompatActivity {

    CarouselView carouselView;
    ArrayList<SlideItem> slideItemList;
    TextView tvNomFormationn, tvDescriptionFormation;
    String videoDescription, videoTitle;
    Button download, open;

    public static final int PERMISSION_STORAGE_CODE = 1000;



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

        open = findViewById(R.id.open);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloadFile();
            }
        });

        download = findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
            }
        });


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

    private void openloadFile() {

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(slideItemList.get(position).getImage());
        }
    };

    public void downloadFile(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);

            }else {
                startDownloading();
            }
        }else {
            startDownloading();
        }
    }

    private void startDownloading() {

        String uri = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.welivesecurity.com%2Ffr%2F2018%2F03%2F13%2Fretour-hacking-team-espiongiciels%2F&psig=AOvVaw32K5ToFkqQZLJiJ8PT6zyu&ust=1588335020903000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJDX-JePkOkCFQAAAAAdAAAAABAD"; //https://www.radiantmediaplayer.com/media/bbb-360p.mp4";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file ....");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "wapi_"+System.currentTimeMillis());

        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownloading();
                }else {
                    Toast.makeText(DetailsFormation.this, "Accordez la permission pour commencer le téléchargement ...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
