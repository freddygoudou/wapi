package bj.app.wapi.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth fbAuth;
    ProgressBar progressBar;
    TextView tvhaveNotAccount;
    Button btnConnexion;
    TextInputLayout phoneNumberTIL, nameTIL;
    CountryCodePicker ccp;
    String completePhoneNumber, name;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        ccp = findViewById(R.id.ccp);
        phoneNumberTIL = findViewById(R.id.tILPhoneNumber);
        nameTIL = findViewById(R.id.nameTIL);

        mAuth = FirebaseAuth.getInstance();

        // Langue d'envoie du message d'authentification
        mAuth.useAppLanguage();


        //password = passwordTIL.getEditText().getText().toString();

        //sendVerificationCodeToUser(completePhoneNumber);

        btnConnexion = findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ccp.getFullNumberWithPlus();
                completePhoneNumber = ccp.getSelectedCountryCodeWithPlus()+ phoneNumberTIL.getEditText().getText().toString().trim();
                name = nameTIL.getEditText().getText().toString();

                //Toast.makeText(LoginActivity.this,"PHONE NUMBER IS "+completePhoneNumber, Toast.LENGTH_LONG).show();

                if(completePhoneNumber.isEmpty() || completePhoneNumber.length() < 11){
                    phoneNumberTIL.setError("Numero invalide");
                    phoneNumberTIL.requestFocus();
                    return;
                }else if(name.isEmpty()){
                    nameTIL.setError(getString(R.string.name_empty));
                    nameTIL.requestFocus();
                }else{

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
                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
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
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this, task.getException() .getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
