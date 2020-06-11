package bj.app.wapi.ui;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.Nullable;
import bj.app.wapi.R;
import entity.AudioCarrousel;
import entity.ImageCarrousel;
import entityBackend.Caroussel;
import entityBackend.User;
import storage.SharedPrefManager;

public class AudioBackgroundService extends Service {

    MediaPlayer player;
    ArrayList<AudioCarrousel> audioCarrousels;
    ArrayList<Integer> audios;
    int my_position;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra("audiosFormation")){
            audios = new ArrayList<>();
            audioCarrousels = new ArrayList<>();
            audioCarrousels = intent.getParcelableArrayListExtra("audiosFormation");

            for (int i=0; i<audioCarrousels.size(); i++){
                //audios.add(audioCarrousels.get(i).getAudio());
                //audios.add(audioCarrousels.get(i).getUrl());
                audios.add(audioCarrousels.get(i).getAudio());
            }

            System.out.println("Audio is : "+audios.toString());

            // JOUER MAINTENANTLA LISTE DES AUDIOS DEPUIS LE PREMIER EN POSITION 0
            playAudio(0);
        }else {
            Toast.makeText(AudioBackgroundService.this,"Extra not find", Toast.LENGTH_LONG).show();
        }

        return START_STICKY;
    }

    /*@Override
    public void onCreate() {
        super.onCreate();

        */
    /*audios = new ArrayList<>();
        audios.add(R.raw.welcome_to_wapi);
        audios.add(R.raw.si_tu_souhaite_je_accompagne_ta_langue);
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
        if (player != null) {
            if (player.isPlaying())
                System.out.println("Playing Audio for position : "+position);
            else
                System.out.println("Not Playing Audio for position : "+position);
            player.stop();
            player.release();
            player = null;
        }

        my_position = position;
        player = MediaPlayer.create(getApplicationContext(), audios.get(my_position));
        //player = MediaPlayer.create(getApplicationContext(), Uri.parse(audios.get(my_position)));
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
