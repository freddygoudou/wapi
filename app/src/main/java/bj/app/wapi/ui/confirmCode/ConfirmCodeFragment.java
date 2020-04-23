package bj.app.wapi.ui.confirmCode;

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

import com.google.android.material.textfield.TextInputLayout;

public class ConfirmCodeFragment extends Fragment {

    String code;
    Button btnSendCodeAgain, btnInscription;
    TextInputLayout confirmCodeTIL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        confirmCodeTIL = view.findViewById(R.id.tILConfirmCode);

        btnInscription = view.findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                code = confirmCodeTIL.getEditText().getText().toString();

                if (verifyCode(code)){
                    navController.navigate(R.id.action_navigation_confirm_code_to_navigation_register_user_form);
                }
            }
        });

        btnSendCodeAgain = view.findViewById(R.id.btnSendCodeAgain);
        btnSendCodeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CALL SERVICE AND SEND CODE AGAIN
            }
        });

    }

    private boolean verifyCode(String code) {

        //CALL SERVICE FOR INSCRIPTION AND GO TO MAINACTIVITY
        return true;
    }
}
