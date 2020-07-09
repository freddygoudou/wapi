package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.R;
import entity.AudioAndTextview;
import entityBackend.Farmer;
import entityBackend.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import storage.SharedPrefManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ChoixLangue extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> audios;
    TextView tv_french ,tv_english ,tv_bariba ,tv_baili ,tv_more ,tv_gourmantche ,tv_haoussa ,tv_zerma ,tv_fongbe ,tv_mina;
    List<TextView> textViewList;
    Farmer farmer;
    ProgressDialog mProgressDialog;
    MediaPlayer player;
    ArrayList<AudioAndTextview> audioAndTextviews;
    int my_position;
    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_langue);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        shake = AnimationUtils.loadAnimation(this, R.anim.shakeanimation);

        mProgressDialog = new ProgressDialog(this);

        /*if (getIntent().hasExtra("farmer")){
            farmer = getIntent().getParcelableExtra("farmer");
        }*/

        tv_french = findViewById(R.id.tv_french);
        tv_english = findViewById(R.id.tv_english);
        tv_bariba = findViewById(R.id.tv_bariba);
        tv_baili = findViewById(R.id.tv_baili);
        tv_more = findViewById(R.id.tv_more);
        tv_gourmantche = findViewById(R.id.tv_gourmantche);
        tv_haoussa = findViewById(R.id.tv_haoussa);
        tv_zerma = findViewById(R.id.tv_zerma);
        tv_fongbe = findViewById(R.id.tv_fongbe);
        tv_mina = findViewById(R.id.tv_mina);

        tv_french.setOnClickListener(this);
        tv_english.setOnClickListener(this);
        tv_bariba.setOnClickListener(this);
        tv_baili.setOnClickListener(this);
        /*tv_more.setOnClickListener(this);
        tv_gourmantche.setOnClickListener(this);
        tv_haoussa.setOnClickListener(this);
        tv_zerma.setOnClickListener(this);
        tv_fongbe.setOnClickListener(this);
        tv_mina.setOnClickListener(this);*/

        textViewList = new ArrayList<>();
        textViewList.add(tv_zerma);
        textViewList.add(tv_more);
        textViewList.add(tv_haoussa);
        textViewList.add(tv_gourmantche);
        textViewList.add(tv_french);
        textViewList.add(tv_fongbe);
        textViewList.add(tv_english);
        textViewList.add(tv_baili);


        audios = new ArrayList<>();
        audios.add( R.raw.audio_french_d_1_2);
        audios.add(R.raw.all_langues);


        //animateLangue(textViewList);

        playAudio(loadRessources(), 0);
    }

    /*public void startAudios(ArrayList<AudioAndTextview> audioAndTextviews, int position){

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shakeanimation);
        player = MediaPlayer.create(ChoixLangue.this, R.raw.french);
        player.start();

        player.stop();
        player.release();
        tv_french.setAnimation(shake);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player = MediaPlayer.create(ChoixLangue.this, R.raw.english);
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        player.stop();
                        player.release();
                        tv_english.setAnimation(shake);
                        player = MediaPlayer.create(ChoixLangue.this, R.raw.biali);
                        player.start();
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                player.stop();
                                player.release();
                                tv_baili.setAnimation(shake);
                            }
                        });
                    }
                });
            }
        },5000);

        *//*stopService(new Intent(ChoixLangue.this, StartBackgroundAudioService.class));
        startService(new Intent(ChoixLangue.this, StartBackgroundAudioService.class)
                .putExtra("audios", audios));*//*
    }*/

    public void playAudio(ArrayList<AudioAndTextview> audioAndTextviews, int position){

        my_position = position;
        player = MediaPlayer.create(ChoixLangue.this, audioAndTextviews.get(my_position).getAudio());
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
                audioAndTextviews.get(my_position).getTextView().startAnimation(shake);
                //Toast.makeText(ChoixLangue.this, audioAndTextviews.get(my_position).getTextView().getText(), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        my_position++;
                        if (my_position < audioAndTextviews.size()){
                            playAudio(audioAndTextviews, my_position);
                        }
                    }
                },1500);
            }
        });
    }







    /*public void animateLangue(List<TextView> textViewList) *//*throws InterruptedException *//*{
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shakeanimation);
        //for (int i=0; i<textViewList.size(); i++){
          //  textViewList.get(i).setAnimation(shake);
            *//*waitSomeTime(1000);*//*
            textViewList.get(0).setAnimation(shake);
        //}
    }*/

    public ArrayList<AudioAndTextview> loadRessources(){

        audioAndTextviews = new ArrayList<>();
        audioAndTextviews.add(new AudioAndTextview(tv_french, R.raw.french));
        audioAndTextviews.add(new AudioAndTextview(tv_english, R.raw.english));
        audioAndTextviews.add(new AudioAndTextview(tv_baili, R.raw.biali));
        audioAndTextviews.add(new AudioAndTextview(tv_bariba, R.raw.bariba));
        audioAndTextviews.add(new AudioAndTextview(tv_more, R.raw.more));
        audioAndTextviews.add(new AudioAndTextview(tv_gourmantche, R.raw.gourmantche));
        audioAndTextviews.add(new AudioAndTextview(tv_haoussa, R.raw.haoussa));
        audioAndTextviews.add(new AudioAndTextview(tv_zerma, R.raw.zerma));
        audioAndTextviews.add(new AudioAndTextview(tv_fongbe, R.raw.fongbe));

        return audioAndTextviews;
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (player != null){
            try {
                player.stop();
                player.release();
            } catch(Exception e){
                Log.d("Audio stopped", e.toString());
            }
        }
        playAudio(loadRessources(),0);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (player != null){
            try {
                player.stop();
                player.release();
            } catch(Exception e){
                Log.d("Audio stopped", e.toString());
            }
        }

        //stopService(new Intent(ChoixLangue.this, StartBackgroundAudioService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            try {
                player.stop();
                player.release();
            } catch(Exception e){
                Log.d("Audio stopped", e.toString());
            }
        }
        //stopService(new Intent(ChoixLangue.this, StartBackgroundAudioService.class));
    }

    @Override
    public void onClick(View view) {
        Farmer farmer = new Farmer("", FirebaseAuth.getInstance().getUid(), SharedPrefManager.getmInstance(this).getUser().getName(), FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(), "",  new ArrayList<>(), new ArrayList<>(), null, null);
        User user = SharedPrefManager.getmInstance(this).getUser();
        if (view.getId() == R.id.tv_french){
            farmer.setLangue(getResources().getString(R.string.langueFrench));
            user.setLangue(getResources().getString(R.string.langueFrench));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_english){
            farmer.setLangue(getResources().getString(R.string.langueEnglish));
            user.setLangue(getResources().getString(R.string.langueEnglish));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_bariba){
            farmer.setLangue(getResources().getString(R.string.langueBariba));
            user.setLangue(getResources().getString(R.string.langueBariba));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_baili){
            farmer.setLangue(getResources().getString(R.string.langueBiali));
            user.setLangue(getResources().getString(R.string.langueBiali));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_more){
            farmer.setLangue(getResources().getString(R.string.langueMore));
            user.setLangue(getResources().getString(R.string.langueMore));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_gourmantche){
            farmer.setLangue(getResources().getString(R.string.langueGourmantche));
            user.setLangue(getResources().getString(R.string.langueGourmantche));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_haoussa){
            farmer.setLangue(getResources().getString(R.string.langueHaoussa));
            user.setLangue(getResources().getString(R.string.langueHaoussa));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_zerma){
            farmer.setLangue(getResources().getString(R.string.langueZerma));
            user.setLangue(getResources().getString(R.string.langueZerma));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_fongbe){
            farmer.setLangue(getResources().getString(R.string.langueFon));
            user.setLangue(getResources().getString(R.string.langueFon));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_mina){
            farmer.setLangue(getResources().getString(R.string.langueMina));
            user.setLangue(getResources().getString(R.string.langueMina));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }
        createFarmerWithApi(farmer);
    }

    private void createFarmerWithApi(Farmer farmer){
        Call<Farmer> call = RetrofitClient
                .getmInstance()
                .getApi()
                .createUser(farmer);
        call.enqueue(new Callback<Farmer>() {
            @Override
            public void onResponse(Call<Farmer> call, Response<Farmer> response) {
                try {
                    Farmer farmerReturned = null;
                    if (response.code() == 200){
                        farmerReturned = response.body();
                        if (farmerReturned != null){
                            //stopService(new Intent(ChoixLangue.this, AudioBackgroundService.class));
                            player.release();
                            startActivity(new Intent(ChoixLangue.this, ThreeImagesMenu.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        }
                    }else {
                        Toast.makeText(ChoixLangue.this, "Response code is :"+response.code()+"\n"+" S_Response message "+response.message(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Farmer> call, Throwable t) {
                Toast.makeText(ChoixLangue.this, "Error message "+t.getMessage(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        });
    }

}
