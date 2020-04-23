package bj.app.wapi.ui.splash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashFragment extends Fragment {

    CircleImageView appIcon;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        appIcon = view.findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(getActivity(),R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

        goToAppropriateScreen(navController);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void goToAppropriateScreen(final NavController navController){

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

                        navController.navigate(R.id.action_navigation_splash_to_navigation_login);
                    }else { //SI CONNECTÉ
                        navController.navigate(R.id.action_navigation_splash_to_navigation_main);
                    }

                }
            }
        };
        timer.start();
    }


}


