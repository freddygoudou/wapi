package bj.app.wapi.ui.registerUserForm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.splash.SplashActivity;
import entityBackend.Farmer;
import entityBackend.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import storage.SharedPrefManager;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

public class RegisterUserFormActivity extends AppCompatActivity {

    Button btnInscription;
    TextView tvhaveAccount;
    TextInputLayout nameTIL, emailTIL, tILPassword, tILPasswordAgain;
    String name, email, langue, passwordAgain;
    //Spinner spinner;
    ArrayList<String> langueList;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_form);


        mProgressDialog = new ProgressDialog(this);
       // spinner = findViewById(R.id.spLangue);
        nameTIL = findViewById(R.id.tILName);

        langueList = new ArrayList<>();
        langueList.add(getResources().getString(R.string.langueBariba));
        langueList.add(getResources().getString(R.string.langueBiali));
        langueList.add(getResources().getString(R.string.langueGourmantche));
        langueList.add(getResources().getString(R.string.langueMore));
        langueList.add(getResources().getString(R.string.langueDjerma));
        langueList.add(getResources().getString(R.string.langueHaoussa));

        /*spinner.setAdapter(new ArrayAdapter<String>(RegisterUserFormActivity.this, android.R.layout.simple_list_item_1, langueList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                langue = langueList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });*/

        btnInscription = findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameTIL.getEditText().getText().toString().trim();

                if(name.length() == 0){
                    Toast.makeText(RegisterUserFormActivity.this,"Veillez renseigner votre nom !", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User(FirebaseAuth.getInstance().getUid(),name, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(), "");
                    SharedPrefManager.getmInstance(RegisterUserFormActivity.this).saveUser(user);
                    startActivity(new Intent(RegisterUserFormActivity.this, ChoixLangue.class));
                    updateUser(name, "");
                }
            }
        });
    }

    void updateUser(String name, String langue){
        User user = SharedPrefManager.getmInstance(RegisterUserFormActivity.this).getUser();
        user.setName(name);
        user.setLangue(langue);
        user.setFirebasUid(FirebaseAuth.getInstance().getUid());
        User userForCall = new User(user.getFirebasUid(), user.getName(), user.getPhoneNumber(), user.getLangue());
        SharedPrefManager.getmInstance(this).saveUser(userForCall);
        Farmer farmer = new Farmer("",FirebaseAuth.getInstance().getUid(), name, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(), "",  new ArrayList<>(), new ArrayList<>(), new Date(), new Date());
        System.out.println("Voici farmer : "+farmer.toString());
        startActivity(new Intent(RegisterUserFormActivity.this, SplashActivity.class)
                .putExtra("farmer", farmer));
    }
}
