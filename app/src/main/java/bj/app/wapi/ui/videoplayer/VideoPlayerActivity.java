package bj.app.wapi.ui.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.DetailsFormation;
import bj.app.wapi.ui.main.MainActivity;
import database.DatabaseHelper;
import entity.Caroussel;
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
import java.util.StringTokenizer;

public class VideoPlayerActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    Video video, videoToSave;
    VideoView videoView;
    ProgressDialog dialog;
    SimpleExoPlayerView exoPlayerView;
    PlayerView playerView;
    SimpleExoPlayer exoPlayer;
    Button download;
    DatabaseHelper databaseHelper;
    public String RESSOURCES_URL = "", TYPE_RESSOURCES_LOCAL_URI = "";
    public static final String VIDEO_FORMAT_MP4 = "mp4";
    public static final String IMAGE_FORMAT_PNG = "png";
    public static final String IMAGE_FORMAT_JPG = "jpg";
    public static final String IMAGE_FORMAT_JPEG = "jpeg";

    public static final int PERMISSION_STORAGE_CODE = 1000;

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

                videoToSave.setCaptionPath(downloadFile(video.getCaptionPath()));

                for (int i=0; i<prepareDownload(video).size(); i++){

                    String uriInLocal = "";

                    uriInLocal = downloadFile(prepareDownload(video).get(i));

                    if (prepareDownload(video).get(i).contains(VIDEO_FORMAT_MP4)){
                        if (i != prepareDownload(video).size()-1){
                            videoToSave.setVideosPaths(videoToSave.getVideosPaths() + uriInLocal+";");
                        }else {
                            videoToSave.setVideosPaths(videoToSave.getVideosPaths() + uriInLocal);
                        }
                    }
                }
                databaseHelper.saveOneVideo(videoToSave);

            }
        });
    }

    private void initExoPlayer(Video video) {

        playerView = findViewById(R.id.exoplayerView);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(exoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"wapi_video_playing"));

        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(prepareDownload(video).get(0)));
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }

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

    private String startDownloading() {

        String fileName = getFileNameFrom(RESSOURCES_URL);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(RESSOURCES_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file ....");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(createVideoFolder(video), fileName);

        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);

        return createVideoFolder(video)+"/"+fileName;
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
                    Toast.makeText(VideoPlayerActivity.this, "Accordez la permission pour commencer le téléchargement ...", Toast.LENGTH_LONG).show();
                }
            }
        }
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
