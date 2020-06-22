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
import entityBackend.Carrousel;
import entityBackend.User;
import storage.SharedPrefManager;

public class StartBackgroundAudioService extends Service {

    MediaPlayer player = new MediaPlayer();
    ArrayList<Integer> audios;
    int my_position;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.hasExtra("audios")){
            audios = intent.getIntegerArrayListExtra("audios");
            playAudio(0);
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

    public void playAudio(int position){
        System.out.println("Audio for position : "+position+" start");
        /*if (player != null) {
            if (player.isPlaying())
                System.out.println("Playing Audio for position : "+position);
            else
                System.out.println("Not Playing Audio for position : "+position);
            //player.stop();
            //player.reset();
            player.release();
        }
*/
        my_position = position;
        player = MediaPlayer.create(StartBackgroundAudioService.this, audios.get(my_position));
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                System.out.println("Audio for position : "+position+" is finish");
                //player.stop();
                //player.reset();
                player.release();
                my_position++;
                if (my_position < audios.size()){
                    playAudio(my_position);
                }
            }
        });

    }
}
