package bj.app.wapi.ui.formation.sousFragment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.Nullable;
import bj.app.wapi.R;
import entityBackend.Caroussel;
import entityBackend.User;
import storage.SharedPrefManager;

public class CarousselBackgroundAudioService extends Service {

    MediaPlayer player = new MediaPlayer();
    User user;
    String langue;
    Caroussel caroussel;
    String pathToPlay;
    ArrayList<String> arrayList;
    static final String AUDIO_FORMAT_MP3 = ".mp3";
    Uri uri;
    File file;
    boolean connexionState;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.hasExtra("caroussel") && intent.hasExtra("connexionState")){
            caroussel = intent.getParcelableExtra("caroussel");
            connexionState = intent.getBooleanExtra("connexionState", false);
            arrayList = prepareAudioPaths(caroussel.getAudiosPaths());

            if (arrayList == null)
                arrayList = new ArrayList<>();

            System.out.println("COKOKO :"+caroussel.toString());
            System.out.println("COKOKO BOOLEAN :"+connexionState);
            System.out.println("COKOKO ARRAYLIST  :"+arrayList.toString());


            String url = "http://www.stephaniequinn.com/Music/Commercial%20DEMO%20-%2009.mp3";


            user = SharedPrefManager.getmInstance(this).getUser();
            langue = user.getLangue();



            //C 'EST ICI QU'IL FAILLE VOIR SELON LA LANGUE LEQUEL DES AUDIO JOUER

            if (!connexionState){
                System.out.println("COKOKOPATH TO PLAY  FOR VALUE  2 :"+getAppropriateAjustedPathToplay(arrayList));
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), getAppropriateAjustedPathToplay(arrayList));
                uri = Uri.fromFile(file);
            }else {
                System.out.println("COKOKOPATH IS NOW :"+arrayList.get(0)+" AND ALSO :"+arrayList);
                uri = Uri.parse(getAppropriateAjustedPathToplay(arrayList));
            }

            //player = MediaPlayer.create(this, uri);
            player = MediaPlayer.create(this, Uri.parse("android.resource://" + "bj.app.wapi/" + R.raw.ecole));
            //player = MediaPlayer.create(this, Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
            player.setLooping(false);
            player.start();

        }

        return START_STICKY; //START_STICKY recrée le service mais START_NOT_STICKY non
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if(player!=null){
            player.stop();
        }
    }

    @Override
    public void onDestroy() {
        if(player!=null){
            player.stop();
        }
    }

    public ArrayList<String> prepareAudioPaths(String path){
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(path, ";");
        while (stringTokenizer.hasMoreElements())
            list.add(stringTokenizer.nextToken());
        return list;
    }

    public String getAppropriateAjustedPathToplay(ArrayList<String> arrayList){
        String pathToPlayAjusted = "";
        for (int i=0;i<arrayList.size();i++){

            //System.out.println("COKOKO FOR VALUE :"+arrayList.get(i));
            if (arrayList.get(i).endsWith(/*langue+*/AUDIO_FORMAT_MP3)){
                //REALLY : arrayList.get(i).endsWith(langue+AUDIO_FORMAT_MP3)
                //System.out.println("COKOKO FOR VALUE MATCHED :"+arrayList.get(i));
                pathToPlayAjusted = ajustFilePath(arrayList.get(i));
                //System.out.println("COKOKOPATH TO PLAY  FOR VALUE :"+pathToPlay);
                return pathToPlayAjusted;
            }
        }
        return null;
    }

    public String ajustFilePath(String path){
        return path.substring(8); //Download
    }
}
