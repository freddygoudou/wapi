package bj.app.wapi.ui.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.CarousselBackgroundAudioService;
import database.DatabaseHelper;
import entity.Caroussel;
import entity.SlideItem;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*

NOMENCLATURE DES FICHIERS

LES IMAGES : image_"nomde l'image"
LES AUDIONS : audio_"la langue de l'audio"

 */

public class DetailsFormation extends AppCompatActivity {

    CarouselView carouselView;
    ArrayList<String> slideItemList;
    TextView tvNomFormationn, tvDescriptionFormation;
    String videoDescription, videoTitle;
    Button download, open;
    Caroussel caroussel, carousselToSave;
    DatabaseHelper databaseHelper;
    public static final String AUDIO = "AUDIO";
    public static final String IMAGE= "IMAGE";
    public static final String AUDIO_FORMAT_MP3 = "mp4";
    public static final String IMAGE_FORMAT_PNG = "png";
    public static final String IMAGE_FORMAT_JPG = "jpg";
    public static final String IMAGE_FORMAT_JPEG = "jpeg";
    public String RESSOURCES_URL = "", TYPE_RESSOURCES_LOCAL_URI = "";
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

        databaseHelper = new DatabaseHelper(this);

        System.out.println("DB CAROUSSEL  "+databaseHelper.getAllCaroussels());

        if (getIntent().hasExtra("caroussel")){
            caroussel = getIntent().getParcelableExtra("caroussel");
            carousselToSave = new Caroussel(caroussel.getName(),caroussel.getDescription(),"","");
        }

        download = findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("audiosFilesToDownlodList : "+prepareDownload(caroussel).get(AUDIO));
                System.out.println("imagesFilesToDownlodList : "+prepareDownload(caroussel).get(IMAGE));

                for (int i=0; i<prepareDownload(caroussel).get(AUDIO).size(); i++){

                    String uriInLocal = downloadFile(prepareDownload(caroussel).get(AUDIO).get(i));
                    System.out.println(" Pour i = "+i+" on a uriInLocal = "+uriInLocal);

                    if (uriInLocal.length()>0){ // POURQUOI LA VALEUR TARDE À ÊTRE RETOURNÉE ?
                        if (i != prepareDownload(caroussel).get(AUDIO).size()-1){
                            carousselToSave.setAudiosPaths(carousselToSave.getAudiosPaths() + uriInLocal+";");
                        }else {
                            carousselToSave.setAudiosPaths(carousselToSave.getAudiosPaths() + uriInLocal);
                        }
                    }
                }

                for (int j=0; j<prepareDownload(caroussel).get(IMAGE).size(); j++){

                    String uriInLocal = downloadFile(prepareDownload(caroussel).get(IMAGE).get(j));
                    System.out.println(" Pour j = "+j+" on a uriInLocal = "+uriInLocal);

                    if (uriInLocal.length()>0){ // POURQUOI LA VALEUR TARDE À ÊTRE RETOURNÉE ?
                        if (j != prepareDownload(caroussel).get(IMAGE).size()-1){
                            carousselToSave.setImagesPaths(carousselToSave.getImagesPaths() + uriInLocal+";");
                        }else {
                            carousselToSave.setImagesPaths(carousselToSave.getImagesPaths() + uriInLocal);
                        }
                    }
                }

                databaseHelper.saveOneCaroussel(carousselToSave);
            }
        });

        tvDescriptionFormation = findViewById(R.id.tvDescriptionFormation);
        tvNomFormationn = findViewById(R.id.tvNomFormation);
        tvNomFormationn.setText(caroussel.getName());
        tvDescriptionFormation.setText(caroussel.getDescription());

        loadCarousselImage();

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

    public void loadCarousselImage(){
        slideItemList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(caroussel.getImagesPaths(), ";");
        while (stringTokenizer.hasMoreTokens()){
            slideItemList.add(stringTokenizer.nextToken());
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(slideItemList.get(position)).into(imageView);
        }
    };

    public String downloadFile(String uri){

        RESSOURCES_URL = uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);

            }else {
                TYPE_RESSOURCES_LOCAL_URI = startDownloading();
            }
        }else {
            TYPE_RESSOURCES_LOCAL_URI = startDownloading();
        }
        return TYPE_RESSOURCES_LOCAL_URI;
    }

    public HashMap<String, ArrayList<String>> prepareDownload(Caroussel caroussel){
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> filesToDownloadList, audiosFilesToDownlodList, imagesFilesToDownlodList;
        filesToDownloadList  = new ArrayList<>();
        audiosFilesToDownlodList = new ArrayList<>();
        imagesFilesToDownlodList = new ArrayList<>();

        StringTokenizer carousselTokenizer;
        carousselTokenizer = new StringTokenizer(caroussel.getImagesPaths(), ";");

        while (carousselTokenizer.hasMoreTokens()){
            filesToDownloadList.add(carousselTokenizer.nextToken());
        }

        carousselTokenizer = new StringTokenizer(caroussel.getAudiosPaths(), ";");
        while (carousselTokenizer.hasMoreTokens()){
            filesToDownloadList.add(carousselTokenizer.nextToken());
        }

        for (int i=0; i<filesToDownloadList.size(); i++){
            if (filesToDownloadList.get(i).contains(AUDIO_FORMAT_MP3)){
                audiosFilesToDownlodList.add(filesToDownloadList.get(i));
            }else if (filesToDownloadList.get(i).contains(IMAGE_FORMAT_JPG) || filesToDownloadList.get(i).contains(IMAGE_FORMAT_JPEG) || filesToDownloadList.get(i).contains(IMAGE_FORMAT_PNG)){
                imagesFilesToDownlodList.add(filesToDownloadList.get(i));
            }
        }

        hashMap.put(AUDIO, audiosFilesToDownlodList);
        hashMap.put(IMAGE, imagesFilesToDownlodList);
        //System.out.println("audiosFilesToDownlodList : "+audiosFilesToDownlodList.toString());
        //System.out.println("imagesFilesToDownlodList : "+imagesFilesToDownlodList.toString());
        return hashMap;
    }

    private String startDownloading() {

        String fileName = getFileNameFrom(RESSOURCES_URL);
        String folder = createCarousselFolder(caroussel);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(RESSOURCES_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(caroussel.getName());
        request.setDescription(caroussel.getDescription());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(folder, fileName);

        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);

        return folder+"/"+fileName;
    }

    private String getFileNameFrom(String ressources_url) {
        return ressources_url.substring(ressources_url.lastIndexOf('/') + 1);
        //String fileName = ressources_url.substring(ressources_url.lastIndexOf('/') + 1);
        //return fileName.substring(0, fileName.lastIndexOf('.'));
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

    public String createCarousselFolder(Caroussel caroussel) {
        String path = Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/"+caroussel.getName();
        File dir = new File(path);
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            // do something\
            Log.d("Folder", "Already Created");
        }
        return path;
    }



    //https://www.quora.com/How-do-I-create-a-folder-in-internal-and-external-storage-programmatically-in-an-Android-app
}

