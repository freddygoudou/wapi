package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.StartBackgroundAudioService;
import bj.app.wapi.ui.main.MainActivity;
import storage.SharedPrefManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThreeImagesMenu extends AppCompatActivity {

    ImageView iv_e_learning, iv_mon_exploitation, iv_business;
    ArrayList<Integer> audios;
    MediaPlayer player;
    int my_position;

    /*@Override
    protected void onStart() {
        super.onStart();
        startAudio(loadRessources());
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_images_menu);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        iv_e_learning = findViewById(R.id.iv_e_learning);
        iv_mon_exploitation = findViewById(R.id.iv_mon_exploitation);
        iv_business = findViewById(R.id.iv_business);

        //Toast.makeText(this, "Your language  : "+ SharedPrefManager.getmInstance(this).getUser().toString(), Toast.LENGTH_LONG).show();


        iv_e_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                //stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
                startActivity(new Intent(ThreeImagesMenu.this, AudioVideoChoixFormation.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        iv_mon_exploitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                //stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
                startActivity(new Intent(ThreeImagesMenu.this, MainActivity.class)
                        .putExtra("from_wapi_mon_exploitation", "wapi_mon_exploitation")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        iv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                //stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
                startActivity(new Intent(ThreeImagesMenu.this, MainActivity.class)
                        .putExtra("from_wapi_business", "wapi_business")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        //startAudio(loadRessources());
        startAudios(loadRessources());
    }

    private void startAudios(ArrayList<Integer> loadRessources) {
        playAudio(loadRessources,0);
    }

    /*private void startAudio(ArrayList<Integer> audios) {
        stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
        startService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class)
                .putExtra("audios", audios));
    }*/



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.release();
        startAudios(loadRessources());
        /*startService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class)
                .putExtra("audios", loadRessources()));*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        player.release();
        //stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        player.release();
        //stopService(new Intent(ThreeImagesMenu.this, StartBackgroundAudioService.class));
    }

    public ArrayList<Integer> loadRessources(){
        if (SharedPrefManager.getmInstance(ThreeImagesMenu.this).getUser().getLangue().equals(getResources().getString(R.string.langueFrench))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_french_d_2_1);
            audios.add(R.raw.audio_french_d_2_2);
            audios.add(R.raw.audio_french_d_2_3);
            audios.add(R.raw.audio_french_d_2_4);
            audios.add(R.raw.audio_french_d_2_5);
            audios.add(R.raw.audio_french_d_2_6);

        }else if (SharedPrefManager.getmInstance(ThreeImagesMenu.this).getUser().getLangue().equals(getResources().getString(R.string.langueEnglish))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_english_d_2_1);
            audios.add(R.raw.audio_english_d_2_2);
            audios.add(R.raw.audio_english_d_2_3);
            audios.add(R.raw.audio_english_d_2_4);
            audios.add(R.raw.audio_english_d_2_5);
            audios.add(R.raw.audio_english_d_2_6);
        }else if (SharedPrefManager.getmInstance(ThreeImagesMenu.this).getUser().getLangue().equals(getResources().getString(R.string.langueBiali))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_biali_d_2_1);
            audios.add(R.raw.audio_biali_d_2_2);
            audios.add(R.raw.audio_biali_d_2_3);
            audios.add(R.raw.audio_biali_d_2_4);
            audios.add(R.raw.audio_biali_d_2_5);
            audios.add(R.raw.audio_biali_d_2_6);
        }else if (SharedPrefManager.getmInstance(ThreeImagesMenu.this).getUser().getLangue().equals(getResources().getString(R.string.langueBariba))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_biali_d_2_1);
            audios.add(R.raw.audio_biali_d_2_2);
            audios.add(R.raw.audio_biali_d_2_3);
            audios.add(R.raw.audio_biali_d_2_4);
            audios.add(R.raw.audio_biali_d_2_5);
            audios.add(R.raw.audio_biali_d_2_6);
        }
        System.out.println("My audio is : "+audios.toString());
        return  audios;
    }


    public void playAudio(ArrayList<Integer> audios, int position){
        my_position = position;
        player = MediaPlayer.create(ThreeImagesMenu.this, audios.get(my_position));
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                System.out.println("Audio for position : "+position+" is finish");
                player.release();
                my_position++;
                if (my_position < audios.size()){
                    playAudio(audios, my_position);
                }
            }
        });

    }

}
