package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.ui.registerUserForm.RegisterUserFormActivity;
import bj.app.wapi.ui.splash.SplashActivity;
import entity.AudioCarrousel;
import entityBackend.Farmer;
import entityBackend.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import storage.SharedPrefManager;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChoixLangue extends AppCompatActivity implements View.OnClickListener {

    ArrayList<AudioCarrousel> audioCarrousels;
    TextView tv_french ,tv_english ,tv_bariba ,tv_baili ,tv_more ,tv_gourmantche ,tv_haoussa ,tv_zerma ,tv_fongbe ,tv_mina;
    List<TextView> textViewList;
    Farmer farmer;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_langue);

        mProgressDialog = new ProgressDialog(this);

        if (getIntent().hasExtra("farmer")){
            farmer = getIntent().getParcelableExtra("farmer");
        }


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
        audioCarrousels.add(new AudioCarrousel("","", 1, R.raw.si_tu_souhaite_je_accompagne_ta_langue));
        audioCarrousels.add(new AudioCarrousel("","",2,R.raw.all_langues));

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
            farmer.setLangue(getResources().getString(R.string.french));
            user.setLangue(getResources().getString(R.string.french));
            SharedPrefManager.getmInstance(this).saveUser(user);
        }else if (view.getId() == R.id.tv_english){
            farmer.setLangue(getResources().getString(R.string.anglais));
            user.setLangue(getResources().getString(R.string.anglais));
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
                            stopService(new Intent(ChoixLangue.this, AudioBackgroundService.class));
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
