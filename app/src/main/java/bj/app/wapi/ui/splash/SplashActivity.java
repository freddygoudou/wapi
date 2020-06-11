package bj.app.wapi.ui.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.AudioBackgroundService;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.login.LoginActivity;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.ui.registerUserForm.RegisterUserFormActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.AudioCarrousel;
import entityBackend.User;
import storage.SharedPrefManager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    CircleImageView appIcon;
    ArrayList<AudioCarrousel> audioCarrousels;
    MediaPlayer player;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appIcon = findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

        startAudios(audioCarrousels);
    }

    public void startAudios(ArrayList<AudioCarrousel> audioCarrousels){

        player = MediaPlayer.create(getApplicationContext(), R.raw.welcome_to_wapi);
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
                player = null;
                startActivity(new Intent(SplashActivity.this, ChoixLangue.class));
            }
        });
    }
    /*@Override
    protected void onStart() {
        super.onStart();

        // Voir si l'utilisateur est dejà connecté ou pas pour savoir si on doit le redirigér vers le login ou dans l'application  en même temps
        // Ce listerner est responsable certainement du jeu d'activité entre le login et le RegisterUerActivity

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        if (mCurrentUser != null) {

            User user = SharedPrefManager.getmInstance(this).getUser();
            System.out.println("User is :"+ user.toString());
            if (user.getLangue() == null || user.getName() ==null){
                startActivity(new Intent(SplashActivity.this, RegisterUserFormActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }else {
                startActivity(new Intent(SplashActivity.this, ChoixLangue.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }
        else {
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

        */
    /*mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    */
    /*
        */
    /*if (SharedPrefManager.getmInstance(SplashActivity.this).getUser().getId().equals("NO_FOUND")){

                        //Toast.makeText(SplashActivity.this,"TO LOGIN", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    }else {
                        //dToast.makeText(SplashActivity.this,"CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }*/
    /*
        */
    /*

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
    /*
    }*/


}


