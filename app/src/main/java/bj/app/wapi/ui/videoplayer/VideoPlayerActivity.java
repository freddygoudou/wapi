package bj.app.wapi.ui.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.DetailsFormation;
import bj.app.wapi.ui.main.MainActivity;
import database.DatabaseHelper;
import entity.FileAndExtention;
import entity.Video;
import entity.Videooo;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class VideoPlayerActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    public static final String VIDEO_FORMAT_MP4 = ".mp4";
    public static final String IMAGE_FORMAT_PNG = ".png";
    public static final String IMAGE_FORMAT_JPG = ".jpg";
    public static final String IMAGE_FORMAT_JPEG = ".jpeg";
    public static final int PERMISSION_STORAGE_CODE = 1000;

    Video video, videoToSave;
    PlayerView playerView;
    SimpleExoPlayer exoPlayer;
    Button download;
    DatabaseHelper databaseHelper;
    ArrayList<FileAndExtention> list, videosList;
    String imageLocalPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        databaseHelper = new DatabaseHelper(this);

        System.out.println("DB VIDEO  "+databaseHelper.getAllVideos());

        if(getIntent().hasExtra("video")){
            video = getIntent().getParcelableExtra("video");
            videoToSave = new Video(video.getName(),"",video.getDescription(),"");
            initExoPlayer(video);
        }

        download = findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videosList = new ArrayList<>();

                list = downloadFileVideo(video, prepareDownloadVideo(video));

                //LOAD AUDIO FILES AND IMAGES FILES
                for (int i=0; i<list.size(); i++){
                    if (list.get(i).getExtention().equals(VIDEO_FORMAT_MP4)){
                        videosList.add(list.get(i));
                    }else {
                        imageLocalPath = list.get(i).getLocation();
                    }
                }

                //SET IMAGE CAPTION FOR VIDEO TO SAVE
                videoToSave.setCaptionPath(imageLocalPath);

                // SET VIDEO PATHS FOR IMAGE TO SAVE
                for (int i=0; i<videosList.size(); i++){
                    if (i != videosList.size()-1){
                        videoToSave.setVideosPaths(videoToSave.getVideosPaths()+videosList.get(i).getLocation()+";");
                    }else {
                        videoToSave.setVideosPaths(videoToSave.getVideosPaths()+videosList.get(i).getLocation());
                    }
                }

                System.out.println("DOWNLOADED ALL FILES LIST : "+list.toString());
                System.out.println("DOWNLOADED VIDEO FILES LIST : "+videosList.toString());
                System.out.println("DOWNLOADED CAPTION FILES  : "+imageLocalPath);
                System.out.println("VIDEO TO SAVE INFO : "+videoToSave.toString());

                databaseHelper.saveOneVideo(videoToSave);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    downloadFileVideo(video, prepareDownloadVideo(video));
                }else {
                    Toast.makeText(VideoPlayerActivity.this, "Permission d'accès au stockage externe indispensable pour le téléchargement ...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void initExoPlayer(Video video) {

        playerView = findViewById(R.id.exoplayerView);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(exoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"wapi_video_playing"));

        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(prepareDownload(video).get(0))); //GET(i) en fonction de la langue
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    public ArrayList<String> prepareDownload(Video video){
        ArrayList<String> filesToDownloadList = new ArrayList<>();
        StringTokenizer videoTokenizer;
        videoTokenizer = new StringTokenizer(video.getVideosPaths(), ";");

        while (videoTokenizer.hasMoreTokens()){
            filesToDownloadList.add(videoTokenizer.nextToken());
        }
        filesToDownloadList.add(video.getCaptionPath());
        return filesToDownloadList;
    }

    private String getFileNameFrom(String ressources_url) {
        return ressources_url.substring(ressources_url.lastIndexOf('/') + 1);
        //String fileName = ressources_url.substring(ressources_url.lastIndexOf('/') + 1);
        //return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    public ArrayList<FileAndExtention> downloadFileVideo(Video mVideo, ArrayList<String> arrayList){

        ArrayList<FileAndExtention> fileAndExtentionArrayList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);

            }else {
                fileAndExtentionArrayList = startDownloadingVideo(mVideo, arrayList);
            }
        }else {
            fileAndExtentionArrayList = startDownloadingVideo(mVideo, arrayList);
        }

        //SET FILES EXTENTIONS
        for (int i=0; i<fileAndExtentionArrayList.size(); i++){
            if(fileAndExtentionArrayList.get(i).getLocation().contains(VIDEO_FORMAT_MP4)){

                fileAndExtentionArrayList.get(i).setExtention(VIDEO_FORMAT_MP4);

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

    public ArrayList<String> prepareDownloadVideo(Video video){
        ArrayList<String> filesToDownloadList;
        filesToDownloadList  = new ArrayList<>();

        StringTokenizer videoTokenizer;
        videoTokenizer = new StringTokenizer(video.getVideosPaths(), ";");

        while (videoTokenizer.hasMoreTokens()){
            filesToDownloadList.add(videoTokenizer.nextToken());
        }

        //AJOUTER CAPTION FILES À LA LISTE DE TÉLÉCHARGEMENT
        filesToDownloadList.add(video.getCaptionPath());

        return filesToDownloadList;
    }

    private ArrayList<FileAndExtention> startDownloadingVideo(Video mVideo, ArrayList<String> arrayList) {

        String folder = createVideoFolder(mVideo);
        String fileName;
        ArrayList<FileAndExtention> localFilesLocationsList = new ArrayList<>();

        for (int i=0; i<arrayList.size(); i++){

            fileName = getFileNameFrom(arrayList.get(i));

            localFilesLocationsList.add(new FileAndExtention(folder+"/"+fileName,""));

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayList.get(i)));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle(mVideo.getName());
            request.setDescription(mVideo.getDescription());
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(folder, fileName);
            DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            manager.enqueue(request);
        }
        return localFilesLocationsList;
    }

    public String createVideoFolder(Video video) {
        String path = Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Video/"+video.getName();
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
}
