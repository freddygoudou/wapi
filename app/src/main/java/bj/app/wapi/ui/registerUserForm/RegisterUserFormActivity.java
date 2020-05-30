package bj.app.wapi.ui.registerUserForm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.R;
import entityBackend.User;
import storage.SharedPrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RegisterUserFormActivity extends AppCompatActivity {

    Button btnInscription;
    TextView tvhaveAccount;
    TextInputLayout nameTIL, emailTIL, tILPassword, tILPasswordAgain;
    String name, email, langue, passwordAgain;
    Spinner spinner;
    ArrayList<String> langueList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_form);


        spinner = findViewById(R.id.spLangue);
        nameTIL = findViewById(R.id.tILName);
        emailTIL = findViewById(R.id.tILEmail);

        langueList = new ArrayList<>();
        langueList.add(getResources().getString(R.string.langueBariba));
        langueList.add(getResources().getString(R.string.langueBaili));
        langueList.add(getResources().getString(R.string.langueGourmantche));
        langueList.add(getResources().getString(R.string.langueMore));
        langueList.add(getResources().getString(R.string.langueDjerma));
        langueList.add(getResources().getString(R.string.langueHaoussa));

        spinner.setAdapter(new ArrayAdapter<String>(RegisterUserFormActivity.this, android.R.layout.simple_list_item_1, langueList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                langue = langueList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnInscription = findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameTIL.getEditText().getText().toString().trim();
                email = emailTIL.getEditText().getText().toString().trim();
                //password = tILPassword.getEditText().getText().toString();
                //passwordAgain = tILPasswordAgain.getEditText().getText().toString();

                if (langue != null){
                    register(name, email, langue.trim());
                }else {
                    Toast.makeText(RegisterUserFormActivity.this,"Vous devez absolument sélectionner un langue pour continuer!", Toast.LENGTH_LONG).show();
                }


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



    private  void register(String name, String email, String langue){

        User user = SharedPrefManager.getmInstance(RegisterUserFormActivity.this).getUser();
        user.setName(name);
        user.setLangue(langue);
        SharedPrefManager.getmInstance(RegisterUserFormActivity.this).clear();
        SharedPrefManager.getmInstance(RegisterUserFormActivity.this).saveUser(user);

        //CALL SERVICE FOR INSCRIPTION AND GO TO MAINACTIVITY
        startActivity(new Intent(RegisterUserFormActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }


}
