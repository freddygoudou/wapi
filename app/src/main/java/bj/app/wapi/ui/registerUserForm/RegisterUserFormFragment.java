package bj.app.wapi.ui.registerUserForm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterUserFormFragment extends Fragment {

    Button btnInscription;
    TextView tvhaveAccount;
    TextInputLayout nameTIL, emailTIL, tILPassword, tILPasswordAgain;
    String name, email, password, passwordAgain;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_user_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        nameTIL = view.findViewById(R.id.tILName);
        emailTIL = view.findViewById(R.id.tILEmail);
        tILPassword = view.findViewById(R.id.tILPassword);
        tILPasswordAgain = view.findViewById(R.id.tILPasswordAgain);


        btnInscription = view.findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameTIL.getEditText().getText().toString();
                email = emailTIL.getEditText().getText().toString();
                password = tILPassword.getEditText().getText().toString();
                passwordAgain = tILPasswordAgain.getEditText().getText().toString();

                if(validerUserInfos(name, email, password, passwordAgain)){

                    register(navController, name, email, password);
                }

            }
        });

        tvhaveAccount = view.findViewById(R.id.tvhaveAccount);
        tvhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_register_user_form_to_navigation_login);
            }
        });


    }

    private boolean validerUserInfos(String name, String email, String password, String passwordAgain) {

        return true;
    }

    private  void register(NavController navController, String name, String email, String password){



        //CALL SERVICE FOR INSCRIPTION AND GO TO MAINACTIVITY
        navController.navigate(R.id.action_navigation_register_user_form_to_navigation_main);
    }


}
