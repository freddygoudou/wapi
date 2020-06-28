package bj.app.wapi.ui;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import entity.AudioCarrousel;

public class AudioBackgroundService extends Service {

    MediaPlayer player;
    ArrayList<AudioCarrousel> audioCarrousels;
    ArrayList<String> audios;
    boolean connexionState;
    int my_position;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.hasExtra("audiosFormation") && intent.hasExtra("connexionState")){
            connexionState = intent.getBooleanExtra("connexionState", false);
            System.out.println("connexionState is : "+connexionState);
            audios = new ArrayList<>();
            audioCarrousels = new ArrayList<>();
            audioCarrousels = intent.getParcelableArrayListExtra("audiosFormation");
            for (int i=0; i<audioCarrousels.size(); i++){
                if (!connexionState)
                    audios.add(audioCarrousels.get(i).getBaseUri());
                else
                    audios.add(audioCarrousels.get(i).getUrl());
            }
            System.out.println("Audio is : "+audios.toString());
            //Toast.makeText(AudioBackgroundService.this,"Extra founf :"+connexionState, Toast.LENGTH_LONG).show();
            playAudio(0);
        }else {
            //Toast.makeText(AudioBackgroundService.this,"Extra not find", Toast.LENGTH_LONG).show();
        }

        return START_STICKY;
    }

    /*@Override
    public void onCreate() {
        super.onCreate();

        */
    /*audios = new ArrayList<>();
        audios.add(R.raw.audio_french_d_1_1);
        audios.add(R.raw.audio_french_d_1_2);
        audios.add(R.raw.grace_a_cette_application);
        audios.add(R.raw.tu_sauras_comment_bien_gerer_tes_champs_tes_recoltes_et_ton_elevage_comme_grand_patron);
        audios.add(R.raw.tu_pourras_trouver_ou_acheter);
        audios.add(R.raw.si_tu_souhaite_faire_former_femme_apprend);
        audios.add(R.raw.si_tu_souhaite_savoir_comment_bien_gerer_tes_champs);
        audios.add(R.raw.si_tu_souhaite_acheter_maque_champs_vendre_produit);*/
    /*

    }*/
    /*@Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (player != null)
            player.stop();
            player.start();
    }*/

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
    }

    public void playAudio(int position){
        System.out.println("Audio for position : "+position+" start");

        //Toast.makeText(AudioBackgroundService.this, "connexion sate :"+connexionState, Toast.LENGTH_SHORT).show();
        my_position = position;
        if (connexionState) {
            System.out.println("Inside true");
            player = MediaPlayer.create(AudioBackgroundService.this, Uri.parse(audios.get(my_position)));
        }
        else {
            System.out.println("Inside false");
            System.out.println("En position : "+position+" audio value is :"+audios.get(my_position));
            player = MediaPlayer.create(AudioBackgroundService.this, Uri.parse(String.valueOf(Environment.getExternalStoragePublicDirectory(audios.get(my_position)))));
        }
        System.out.println("Audio Url : "+Uri.parse(String.valueOf(Environment.getExternalStoragePublicDirectory(audios.get(my_position)))));
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                System.out.println("Audio for position : "+position+" is finish");
                player.stop();
                player.release();
                player = null;
                my_position++;
                if (my_position < audios.size()){
                    playAudio(my_position);
                }
            }
        });

    }
}
