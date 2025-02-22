package bj.app.wapi.ui.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.StartBackgroundAudioService;
import database.DatabaseHelper;
import entityBackend.Carrousel;
import entity.FileAndExtention;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

NOMENCLATURE DES FICHIERS

LES IMAGES : image_"nomde l'image"
LES AUDIONS : audio_"la langue de l'audio"

 */

public class DetailsFormation extends AppCompatActivity {

    public static final String AUDIO = "AUDIO";
    public static final String IMAGE= "IMAGE";
    public static final String AUDIO_FORMAT_MP3 = ".mp3";
    public static final String IMAGE_FORMAT_PNG = ".png";
    public static final String IMAGE_FORMAT_JPG = ".jpg";
    public static final String IMAGE_FORMAT_JPEG = ".jpeg";
    public static final int PERMISSION_STORAGE_CODE = 1000;

    CarouselView carouselView;
    ArrayList<String> slideItemList;
    TextView tvNomFormationn, tvDescriptionFormation;
    Button download;
    Carrousel carrousel, carrouselToSave;
    DatabaseHelper databaseHelper;
    boolean connexionState;
    File file;
    Uri uri;

    @Override
    protected void onStop() {
        super.onStop();
        //Arrèter l'audio
        stopService(new Intent(DetailsFormation.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(DetailsFormation.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carouselView.setCurrentItem(0, true);
        carouselView.playCarousel();
        stopService(new Intent(DetailsFormation.this, StartBackgroundAudioService.class));
        startService(new Intent(DetailsFormation.this, StartBackgroundAudioService.class)
                .putExtra("caroussel", carrousel)
                .putExtra("connexionState", connexionState));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopService(new Intent(DetailsFormation.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals_formation);

       /* databaseHelper = new DatabaseHelper(this);

        if (getIntent().hasExtra("caroussel") && getIntent().hasExtra("connexionState")){
            carrousel = getIntent().getParcelableExtra("caroussel");
            carrouselToSave = new Carrousel(carrousel.getName(), carrousel.getDescription(),"","");
            connexionState = getIntent().getBooleanExtra("connexionState", false);
            System.out.println("CAKAKKA :"+ carrousel.toString());

            tvDescriptionFormation = findViewById(R.id.tvDescriptionFormation);
            tvNomFormationn = findViewById(R.id.tvNomFormation);
            tvNomFormationn.setText(carrousel.getName());
            tvDescriptionFormation.setText(carrousel.getDescription());

            loadCarousselImage();

            carouselView = findViewById(R.id.carouselView);
            carouselView.setPageCount(slideItemList.size());
            carouselView.setImageListener(imageListener);
            carouselView.setSlideInterval(10000);
            carouselView.setCurrentItem(0, true);
            carouselView.playCarousel();

            //Démarer l'audio
            stopService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class));
            startService(new Intent(DetailsFormation.this, CarousselBackgroundAudioService.class)
                    .putExtra("caroussel", carrousel)
                    .putExtra("connexionState", connexionState));


            download = findViewById(R.id.download);
            if(!connexionState){
                download.setEnabled(false);
                download.setVisibility(View.INVISIBLE);
            }else{
                download.setEnabled(true);
                download.setVisibility(View.VISIBLE);
            }
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ArrayList<FileAndExtention> list, audiosList, imagesList;

                    list = downloadFileCaroussel(carrousel, prepareDownloadCaroussel(carrousel));
                    audiosList = new ArrayList<>();
                    imagesList = new ArrayList<>();

                    //LOAD AUDIO FILES AND IMAGES FILES
                    for (int i=0; i<list.size(); i++){
                        if (list.get(i).getExtention().equals(AUDIO_FORMAT_MP3)){
                            audiosList.add(list.get(i));
                        }else {
                            imagesList.add(list.get(i));
                        }
                    }

                    // SET AUDIOS PATHS
                    for (int i=0; i<audiosList.size(); i++){
                        if (i != audiosList.size()-1){
                            carrouselToSave.setAudiosPaths(carrouselToSave.getAudiosPaths()+audiosList.get(i).getLocation()+";");
                        }else {
                            carrouselToSave.setAudiosPaths(carrouselToSave.getAudiosPaths()+audiosList.get(i).getLocation());
                        }
                    }

                    // SET IMAGES PATHS
                    for (int i=0; i<imagesList.size(); i++){
                        if (i != imagesList.size()-1){
                            carrouselToSave.setImagesPaths(carrouselToSave.getImagesPaths()+imagesList.get(i).getLocation()+";");
                        }else {
                            carrouselToSave.setImagesPaths(carrouselToSave.getImagesPaths()+imagesList.get(i).getLocation());
                        }
                    }

                    System.out.println("DOWNLOADED CAROUSSEL FILES LIST : "+list.toString());
                    System.out.println("AUDIO FILES LIST : "+audiosList.toString());
                    System.out.println("IMAGES FILES LIST : "+imagesList.toString());
                    System.out.println("CAROUSSEL TO SAVE : "+ carrouselToSave.toString());

                    //SAVE CAROUSSEL
                    databaseHelper.saveOneCaroussel(carrouselToSave);

                }
            });

        }*/
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //downloadFileCaroussel(carrousel, prepareDownloadCaroussel(carrousel));
                }else {
                    Toast.makeText(DetailsFormation.this, "Permission d'accès au stockage externe indispensable pour le téléchargement ...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            if(connexionState)
                Picasso.get().load(slideItemList.get(position)).into(imageView);
            else {
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), ajustFilePath(slideItemList.get(position)));
                uri = Uri.fromFile(file);
                Picasso.get().load(file).into(imageView);
            }
        }
    };

    public ArrayList<FileAndExtention> downloadFileCaroussel(Carrousel mCarrousel, ArrayList<String> arrayList){

        ArrayList<FileAndExtention> fileAndExtentionArrayList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);

            }else {
                fileAndExtentionArrayList = startDownloadingCaroussel(mCarrousel, arrayList);
            }
        }else {
            fileAndExtentionArrayList = startDownloadingCaroussel(mCarrousel, arrayList);
        }

        //SET FILES EXTENTIONS
        for (int i=0; i<fileAndExtentionArrayList.size(); i++){
            if(fileAndExtentionArrayList.get(i).getLocation().contains(AUDIO_FORMAT_MP3)){

                fileAndExtentionArrayList.get(i).setExtention(AUDIO_FORMAT_MP3);

            }else if(fileAndExtentionArrayList.get(i).getLocation().contains(IMAGE_FORMAT_JPG)){

                fileAndExtentionArrayList.get(i).setExtention(IMAGE_FORMAT_JPG);
            }
            else if(fileAndExtentionArrayList.get(i).getLocation().contains(IMAGE_FORMAT_JPEG)){

                fileAndExtentionArrayList.get(i).setExtention(IMAGE_FORMAT_JPEG);
            }
            else if(fileAndExtentionArrayList.get(i).getLocation().contains(IMAGE_FORMAT_PNG)){

                fileAndExtentionArrayList.get(i).setExtention(IMAGE_FORMAT_PNG);
            }
        }

        return fileAndExtentionArrayList;
    }

    /*public ArrayList<String> prepareDownloadCaroussel(Carrousel carrousel){
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> filesToDownloadList;
        filesToDownloadList  = new ArrayList<>();

        StringTokenizer carousselTokenizer;
        carousselTokenizer = new StringTokenizer(carrousel.getImagesPaths(), ";");

        while (carousselTokenizer.hasMoreTokens()){
            filesToDownloadList.add(carousselTokenizer.nextToken());
        }

        //carousselTokenizer = new StringTokenizer(carrousel.getAudiosPaths(), ";");
        while (carousselTokenizer.hasMoreTokens()){
            filesToDownloadList.add(carousselTokenizer.nextToken());
        }
        return filesToDownloadList;
    }*/

    private ArrayList<FileAndExtention> startDownloadingCaroussel(Carrousel mCarrousel, ArrayList<String> arrayList) {

        String folder = createCarousselFolder(mCarrousel);
        String fileName;
        ArrayList<FileAndExtention> localFilesLocationsList = new ArrayList<>();

        for (int i=0; i<arrayList.size(); i++){

            fileName = getFileNameFrom(arrayList.get(i));

            localFilesLocationsList.add(new FileAndExtention(folder+"/"+fileName,""));

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayList.get(i)));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle(mCarrousel.getName());
            request.setDescription(mCarrousel.getDescription());
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(folder, fileName);
            DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            manager.enqueue(request);
        }
        return localFilesLocationsList;
    }

    /*public void loadCarousselImage(){
        slideItemList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(carrousel.getImagesPaths(), ";");
        while (stringTokenizer.hasMoreTokens()){
            slideItemList.add(stringTokenizer.nextToken());
        }
    }*/

    public String ajustFilePath(String path){
        return path.substring(8); //Download
    }

    private String getFileNameFrom(String ressources_url) {
        return ressources_url.substring(ressources_url.lastIndexOf('/') + 1);
    }

    private String getFileExtention(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(fileName, ".");
        while (stringTokenizer.hasMoreTokens()){
            list.add(stringTokenizer.nextToken());
        }
        return list.get(list.size()-1);
    }

    public String getMimeType(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    public String createCarousselFolder(Carrousel carrousel) {
        String path = Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/"+ carrousel.getName();
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

