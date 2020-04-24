package bj.app.wapi.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class LoginFragment extends Fragment {

    TextView tvhaveNotAccount;
    Button btnConnexion;
    String phoneNumber;
    TextInputLayout phoneNumberTIL, passwordTIL;
    CountryCodePicker ccp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        ccp = view.findViewById(R.id.ccp);
        phoneNumberTIL = view.findViewById(R.id.tILPhoneNumber);
        passwordTIL = view.findViewById(R.id.tILPassword);

        tvhaveNotAccount = view.findViewById(R.id.tvhaveNotAccount);
        tvhaveNotAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_login_to_navigation_register);
            }
        });

        btnConnexion = view.findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber, password;
                phoneNumber = phoneNumberTIL.getEditText().getText().toString();
                password = passwordTIL.getEditText().getText().toString();

                login(navController,phoneNumber, password);
            }
        });


    }

    private void login(NavController navController, String phoneNumber, String password) {

        if (true){ //SI IDENTIFIANT CORRECTS
            getActivity().startActivity(new Intent(getActivity(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }else {

        }
    }
}
