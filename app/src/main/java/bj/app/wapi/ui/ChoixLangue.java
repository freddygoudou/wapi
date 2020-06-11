package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;
import entity.AudioCarrousel;
import entityBackend.User;
import storage.SharedPrefManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChoixLangue extends AppCompatActivity implements View.OnClickListener {

    ArrayList<AudioCarrousel> audioCarrousels;
    TextView tv_french ,tv_english ,tv_bariba ,tv_baili ,tv_more ,tv_gourmantche ,tv_haoussa ,tv_zerma ,tv_fongbe ,tv_mina;
    List<TextView> textViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_langue);

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
        tv_more.setOnClickListener(this);
        tv_gourmantche.setOnClickListener(this);
        tv_haoussa.setOnClickListener(this);
        tv_zerma.setOnClickListener(this);
        tv_fongbe.setOnClickListener(this);
        tv_mina.setOnClickListener(this);

        textViewList = new ArrayList<>();
        textViewList.add(tv_zerma);
        textViewList.add(tv_more);
        textViewList.add(tv_haoussa);
        textViewList.add(tv_gourmantche);
        textViewList.add(tv_french);
        textViewList.add(tv_fongbe);
        textViewList.add(tv_english);
        textViewList.add(tv_baili);


        audioCarrousels = new ArrayList<>();
        audioCarrousels.add(new AudioCarrousel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",R.raw.si_tu_souhaite_je_accompagne_ta_langue,"","Mung bean 2"));
        audioCarrousels.add(new AudioCarrousel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",R.raw.all_langues, "","Mung bean 3"));

        startAudios(audioCarrousels);

        animateLangue(textViewList);
        /*try {
            animateLangue(textViewList);
            //waitSomeTime(14000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void startAudios(ArrayList<AudioCarrousel> audioCarrousels){
        stopService(new Intent(ChoixLangue.this, AudioBackgroundService.class));
        startService(new Intent(ChoixLangue.this, AudioBackgroundService.class)
                .putExtra("audiosFormation", audioCarrousels));
    }

    public void animateLangue(List<TextView> textViewList) /*throws InterruptedException */{
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shakeanimation);
        //for (int i=0; i<textViewList.size(); i++){
          //  textViewList.get(i).setAnimation(shake);
            /*waitSomeTime(1000);*/
            textViewList.get(0).setAnimation(shake);
        //}
    }

    public synchronized void waitSomeTime(int time) throws InterruptedException {
            wait(time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
        startService(new Intent(ChoixLangue.this, AudioBackgroundService.class)
                .putExtra("audiosFormation", audioCarrousels));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
    }

    @Override
    public void onClick(View view) {

        User user = SharedPrefManager.getmInstance(this).getUser();
        if (view.getId() == R.id.tv_french){
            user.setLangue(getResources().getString(R.string.french));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_english){
            user.setLangue(getResources().getString(R.string.anglais));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_bariba){
            user.setLangue(getResources().getString(R.string.langueBariba));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_baili){
            user.setLangue(getResources().getString(R.string.langueBiali));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_more){
            user.setLangue(getResources().getString(R.string.langueMore));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_gourmantche){
            user.setLangue(getResources().getString(R.string.langueGourmantche));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_haoussa){
            user.setLangue(getResources().getString(R.string.langueHaoussa));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_zerma){
            user.setLangue(getResources().getString(R.string.langueZerma));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_fongbe){
            user.setLangue(getResources().getString(R.string.langueFon));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_mina){
            user.setLangue(getResources().getString(R.string.langueMina));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }
        stopService(new Intent(ChoixLangue.this, AudioBackgroundService.class));
        startActivity(new Intent(ChoixLangue.this, ThreeImagesMenu.class));

    }
}
