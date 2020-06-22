package bj.app.wapi.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import api.RetrofitClient;
import bj.app.wapi.R;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.ThreeImagesMenu;
import bj.app.wapi.ui.main.MainActivity;
import bj.app.wapi.ui.registerUserForm.RegisterUserFormActivity;
import bj.app.wapi.ui.splash.SplashActivity;
import entityBackend.Farmer;
import entityBackend.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import storage.SharedPrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    ProgressBar progressBar;
    TextView tvhaveNotAccount;
    Button btnConnexion;
    TextInputLayout phoneNumberTIL, nameTIL;
    CountryCodePicker ccp;
    String completePhoneNumber, name;
    FirebaseAuth mAuth;
    DatabaseReference mUserDatabase;
    FirebaseUser mCurrentUser;
    Farmer farmer;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        mAuth = FirebaseAuth.getInstance();

        mAuth.setLanguageCode("FR");

        progressBar = findViewById(R.id.progressBar);

        ccp = findViewById(R.id.ccp);
        phoneNumberTIL = findViewById(R.id.tILPhoneNumber);

        btnConnexion = findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ccp.getFullNumberWithPlus();
                completePhoneNumber = ccp.getSelectedCountryCodeWithPlus()+ phoneNumberTIL.getEditText().getText().toString().trim();

                if(completePhoneNumber.isEmpty() || completePhoneNumber.length() < 11){
                    phoneNumberTIL.setError("Numero invalide");
                    phoneNumberTIL.requestFocus();
                    return;
                }else{
                    /*startActivity(new Intent(LoginActivity.this, ConfirmCodeActivity.class)
                        .putExtra("phoneNumber", completePhoneNumber));*/
                    sendVerificationCodeToUser(completePhoneNumber);
                }
            }
        });
    }

    private void sendVerificationCodeToUser(String completePhoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                completePhoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
           
            String code = credential.getSmsCode();
            if (code != null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
            Log.d("PHONE AUTH", "onVerificationCompleted:" + credential);

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.w("PHONE AUTH", "onVerificationFailed", e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                //Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(LoginActivity.this,"Une error est survenue. Demandez de l'aide à l'administrateur!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            Log.d("PHONE AUTH", "onCodeSent:" + verificationId);

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;
            btnConnexion.setEnabled(false);
        }
    };

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, codeByUser);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, R.string.welcome_to_wapi, Toast.LENGTH_LONG).show();

                            User user = new User(mAuth.getCurrentUser().getUid(),null, completePhoneNumber,null);

                            SharedPrefManager.getmInstance(LoginActivity.this).saveUser(user);

                            mUserDatabase.child(mAuth.getCurrentUser().getUid()).child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.getValue() == null){

                                        mUserDatabase.child(mAuth.getCurrentUser().getUid()).child("phone").setValue(completePhoneNumber).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                startActivity(new Intent(LoginActivity.this, RegisterUserFormActivity.class));
                                            }
                                        });

                                    }else {
                                        String phone = dataSnapshot.getValue().toString();
                                        if(phone.equals(completePhoneNumber)){

                                            //SI FARMER EXISTE  CONTINUER SI NON RegisterUserFormActivity
                                            Call<Farmer> call = RetrofitClient
                                                    .getmInstance()
                                                    .getApi()
                                                    .readOneFarmer(FirebaseAuth.getInstance().getUid());
                                            call.enqueue(new Callback<Farmer>() {
                                                @Override
                                                public void onResponse(Call<Farmer> call, Response<Farmer> response) {
                                                    if (response.code() == 200){
                                                        farmer = response.body();
                                                        if (farmer != null){
                                                            User user = new User(FirebaseAuth.getInstance().getUid(), farmer.getName(), farmer.getPhoneNumber(),farmer.getLangue());
                                                            SharedPrefManager.getmInstance(LoginActivity.this).saveUser(user);
                                                            startActivity(new Intent(LoginActivity.this, ThreeImagesMenu.class));
                                                        }else {
                                                            startActivity(new Intent(LoginActivity.this, RegisterUserFormActivity.class));
                                                        }
                                                    }else {
                                                        System.out.println("Farmer n'existe pas  : "+response.message());
                                                        startActivity(new Intent(LoginActivity.this, RegisterUserFormActivity.class));
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Farmer> call, Throwable t) {
                                                    System.out.println("Une erreur  .... à corriger : "+t.getMessage());
                                                }
                                            });
                                            //startActivity(new Intent(LoginActivity.this, ChoixLangue.class));
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this, task.getException() .getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Voir si l'utilisateur est dejà connecté ou pas pour savoir si on doit le redirigér vers le login ou dans l'application  en même temps
        // Ce listerner est responsable certainement du jeu d'activité entre le login et le RegisterUerActivity

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        if (mCurrentUser != null) {

            User user = SharedPrefManager.getmInstance(this).getUser();
            System.out.println("User is :"+ user.toString());
            if (user.getName() == null){
                startActivity(new Intent(LoginActivity.this, RegisterUserFormActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }else {
                startActivity(new Intent(LoginActivity.this, SplashActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }
        else {
            /*Thread timer = new Thread(){

                public void  run(){
                    try{
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            };
            timer.start();*/
        }
    }

}
