package bj.app.wapi.ui.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class RegisterFragment extends Fragment {

    TextView tvhaveAccount;
    Button btnRecevoirCodeConnexion;
    String phoneNumber;
    TextInputLayout phoneNumberTIL;
    CountryCodePicker ccp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        ccp = view.findViewById(R.id.ccp);
        phoneNumberTIL = view.findViewById(R.id.tILPhoneNumber);

        tvhaveAccount = view.findViewById(R.id.tvhaveAccount);
        tvhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack(); //To Login
            }
        });

        btnRecevoirCodeConnexion = view.findViewById(R.id.btnRecevoirCodeConnexion);
        btnRecevoirCodeConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sendCodeConnexion(phoneNumberTIL)){
                    navController.navigate(R.id.action_navigation_register_to_navigation_confirm_code);
                }

            }
        });

    }

    public boolean sendCodeConnexion(TextInputLayout textInputLayout){
        ccp.getFullNumberWithPlus();
        String completePhoneNumber = ccp.getSelectedCountryCodeWithPlus()+ textInputLayout.getEditText().getText().toString().trim();

        //CALL SERVICE AND SEND CODE

        return true;
    }
}
