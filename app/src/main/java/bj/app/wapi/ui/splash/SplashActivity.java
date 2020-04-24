package bj.app.wapi.ui.splash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.ui.wapi.Wapi;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashActivity extends AppCompatActivity {

    CircleImageView appIcon;
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_splash, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        appIcon = view.findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(getActivity(),R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

        goToAppropriateScreen(navController);
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appIcon = findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

        goToAppropriateScreen();

    }

    public void goToAppropriateScreen(){

        Thread timer = new Thread(){

            public void  run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {

                    // Voir si l'utilisateur est dejà connecté ou pas pour savoir si on doit le redirigér vers le login ou dans l'application  en même temps

                    if(true) { //SI NON CONNECTÉ
                        startActivity(new Intent(SplashActivity.this, Wapi.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }else { //SI CONNECTÉ
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }

                }
            }
        };
        timer.start();
    }


}


