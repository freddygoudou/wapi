package bj.app.wapi.ui.registerUserForm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.splash.SplashActivity;
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
                    //createUserWithApi(name, "");
                }
            }
        });
    }

    private void createUserWithApi(String name, String langue){

        User user = SharedPrefManager.getmInstance(RegisterUserFormActivity.this).getUser();
        user.setName(name);
        user.setLangue(langue);
        user.setFirebasUid(FirebaseAuth.getInstance().getUid());
        User userForCall = new User(user.getFirebasUid(), user.getName(), user.getPhoneNumber(), user.getLangue());


        Call<User> call = RetrofitClient
                .getmInstance()
                .getApi()
                .createUser(userForCall);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    User userReturned = null;
                    if (response.code() == 200){
                        userReturned = response.body();
                        if (userReturned != null){
                            SharedPrefManager.getmInstance(RegisterUserFormActivity.this).clear();
                            SharedPrefManager.getmInstance(RegisterUserFormActivity.this).saveUserWithId(userReturned);

                            //CALL SERVICE FOR INSCRIPTION AND GO TO MAINACTIVITY
                            startActivity(new Intent(RegisterUserFormActivity.this, SplashActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        }
                    }else {
                        Toast.makeText(RegisterUserFormActivity.this, "Response code is :"+response.code()+"\n"+" S_Response message "+response.message(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterUserFormActivity.this, "Error message "+t.getMessage(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        });
    }


}
