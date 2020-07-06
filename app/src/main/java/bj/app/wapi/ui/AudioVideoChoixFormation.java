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

import java.util.ArrayList;

public class AudioVideoChoixFormation extends AppCompatActivity {

    ImageView iv_formatio_video, iv_formatio_audio;
    ArrayList<Integer> audios;
    MediaPlayer player;
    int my_position;

    /*@Override
    protected void onStart() {
        super.onStart();
        //startAudio(loadRessources());
        startAudios(loadRessources());
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video_choix_formation);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //startAudios(loadRessources());

        iv_formatio_audio = findViewById(R.id.iv_formatio_audio);
        iv_formatio_video = findViewById(R.id.iv_formatio_video);

        iv_formatio_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                //stopService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class));
                startActivity(new Intent(AudioVideoChoixFormation.this, CvaActivity.class)
                        /*.putExtra("from_wapi_e_learning", "wapi_e_learning")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)*/);
            }
        });

        iv_formatio_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                //stopService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class));
                startActivity(new Intent(AudioVideoChoixFormation.this, TestVideoFromYoutube.class));
                //startActivity(new Intent(AudioVideoChoixFormation.this, TestGrid.class));
            }
        });


    }

    /*private void startAudio(ArrayList<Integer> audios) {
        stopService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class));
        startService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class)
                .putExtra("audios", audios));
    }*/

    public ArrayList<Integer> loadRessources(){
        if (SharedPrefManager.getmInstance(AudioVideoChoixFormation.this).getUser().getLangue().equals(getResources().getString(R.string.langueFrench))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_french_d_3_1);
            audios.add(R.raw.audio_french_d_3_2);
            audios.add(R.raw.audio_french_d_3_3);

        }else if (SharedPrefManager.getmInstance(AudioVideoChoixFormation.this).getUser().getLangue().equals(getResources().getString(R.string.langueEnglish))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_english_d_3_1);
            audios.add(R.raw.audio_english_d_3_2);
            audios.add(R.raw.audio_english_d_3_3);
        }else if (SharedPrefManager.getmInstance(AudioVideoChoixFormation.this).getUser().getLangue().equals(getResources().getString(R.string.langueBiali))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_biali_d_3_1);
            audios.add(R.raw.audio_biali_d_3_2);
            audios.add(R.raw.audio_biali_d_3_3);
        }else if (SharedPrefManager.getmInstance(AudioVideoChoixFormation.this).getUser().getLangue().equals(getResources().getString(R.string.langueBariba))){
            audios = new ArrayList<>();
            audios.add( R.raw.audio_bariba_d_3_1);
            audios.add(R.raw.audio_bariba_d_3_2);
            audios.add(R.raw.audio_bariba_d_3_3);
        }
        //System.out.println("My audio is : "+audios.toString());
        return  audios;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (player != null){
            player.release();
        }
        startAudios(loadRessources());
        //startAudio(loadRessources());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null){
            player.release();
        }
        //stopService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (player != null) {
            player.release();
        }

        //stopService(new Intent(AudioVideoChoixFormation.this, StartBackgroundAudioService.class));
    }

    public void playAudio(ArrayList<Integer> audios, int position){
        my_position = position;
        player = MediaPlayer.create(AudioVideoChoixFormation.this, audios.get(my_position));
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                System.out.println("Audio for position : "+position+" is finish");
                player.reset();
                player.release();
                my_position++;
                if (my_position < audios.size()){
                    playAudio(audios, my_position);
                }
            }
        });

    }

    public void startAudios(ArrayList<Integer> audios){

            playAudio(audios,0);

        /*if (langue.equals(getResources().getString(R.string.langueFrench))){
            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_french_d_3_1);
            player.setLooping(false);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.release();
                    //player.stop();
                    player = MediaPlayer.create(getApplicationContext(), R.raw.audio_french_d_3_2);
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            player.release();
                            //player.stop();
                            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_french_d_3_3);
                            player.start();
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    player.release();
                                    //player.stop();
                                }
                            });
                        }
                    });
                }
            });
        }else if (langue.equals(getResources().getString(R.string.langueEnglish))){
            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_english_d_3_1);
            player.setLooping(false);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.release();
                    player = MediaPlayer.create(getApplicationContext(), R.raw.audio_english_d_3_2);
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            player.release();
                            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_english_d_3_3);
                            player.start();
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    player.release();
                                }
                            });
                        }
                    });
                }
            });
        }else if (langue.equals(getResources().getString(R.string.langueBiali))){
            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_biali_d_3_1);
            player.setLooping(false);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.release();
                    player = MediaPlayer.create(getApplicationContext(), R.raw.audio_biali_d_3_2);
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            player.release();
                            player = MediaPlayer.create(getApplicationContext(), R.raw.audio_biali_d_3_3);
                            player.start();
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    player.release();
                                }
                            });
                        }
                    });
                }
            });
        }*/

    }


}
