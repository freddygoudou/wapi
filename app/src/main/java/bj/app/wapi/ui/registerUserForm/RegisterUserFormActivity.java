package bj.app.wapi.ui.registerUserForm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterUserFormActivity extends AppCompatActivity {

    Button btnInscription;
    TextView tvhaveAccount;
    TextInputLayout nameTIL, emailTIL, tILPassword, tILPasswordAgain;
    String name, email, password, passwordAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_form);


        nameTIL = findViewById(R.id.tILName);
        emailTIL = findViewById(R.id.tILEmail);
        //tILPassword = view.findViewById(R.id.tILPassword);
        //tILPasswordAgain = view.findViewById(R.id.tILPasswordAgain);


        btnInscription = findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameTIL.getEditText().getText().toString();
                email = emailTIL.getEditText().getText().toString();
                //password = tILPassword.getEditText().getText().toString();
                //passwordAgain = tILPasswordAgain.getEditText().getText().toString();

                register(name, email);
            }
        });

        /*tvhaveAccount = view.findViewById(R.id.tvhaveAccount);
        tvhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navigation_register_user_form_to_navigation_login);
            }
        });*/

    }



    private  void register(String name, String email){

        //CALL SERVICE FOR INSCRIPTION AND GO TO MAINACTIVITY
        startActivity(new Intent(RegisterUserFormActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }


}
