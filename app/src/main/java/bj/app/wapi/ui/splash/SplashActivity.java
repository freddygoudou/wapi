package bj.app.wapi.ui.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.login.LoginActivity;
import bj.app.wapi.ui.main.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.User;
import storage.SharedPrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    CircleImageView appIcon;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appIcon = findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Voir si l'utilisateur est dejà connecté ou pas pour savoir si on doit le redirigér vers le login ou dans l'application  en même temps

        // Ce listerner est responsable certainement du jeu d'activité entre le login et le RegisterUerActivity
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser != null) {

            startActivity(new Intent(SplashActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    /*if (SharedPrefManager.getmInstance(SplashActivity.this).getUser().getId().equals("NO_FOUND")){

                        //Toast.makeText(SplashActivity.this,"TO LOGIN", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    }else {
                        //dToast.makeText(SplashActivity.this,"CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }*/
        }
        else {

            //Toast.makeText(SplashActivity.this,"NON CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();

            Thread timer = new Thread(){

                public void  run(){
                    try{
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            };
            timer.start();
        }
        /*mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    *//*if (SharedPrefManager.getmInstance(SplashActivity.this).getUser().getId().equals("NO_FOUND")){

                        //Toast.makeText(SplashActivity.this,"TO LOGIN", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    }else {
                        //dToast.makeText(SplashActivity.this,"CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }*//*

                }
                else {

                    //Toast.makeText(SplashActivity.this,"NON CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();

                    Thread timer = new Thread(){

                        public void  run(){
                            try{
                                sleep(3000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            finally {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        }
                    };
                    timer.start();
                }
            }
        });*/
    }


}


