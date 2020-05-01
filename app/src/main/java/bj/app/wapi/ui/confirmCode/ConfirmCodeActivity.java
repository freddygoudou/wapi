package bj.app.wapi.ui.confirmCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import bj.app.wapi.R;
import bj.app.wapi.ui.login.LoginActivity;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class ConfirmCodeActivity extends AppCompatActivity {

    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth fbAuth;
    FirebaseAuth mAuth;
    String code, phoneNumber;
    Button btnSendCodeAgain, btnInscription;
    TextInputLayout confirmCodeTIL;
    ProgressBar progressBar;
    DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confirm_code);

        if (getIntent().hasExtra("phoneNumber")){
            phoneNumber = getIntent().getStringExtra("phoneNumber");
            sendVerificationCodeToUser(phoneNumber);
        }

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        mAuth = FirebaseAuth.getInstance();

        // Langue d'envoie du message d'authentification
        mAuth.setLanguageCode("FR");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        confirmCodeTIL = findViewById(R.id.tILConfirmCode);

        btnInscription = findViewById(R.id.btnInscription);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                code = confirmCodeTIL.getEditText().getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    confirmCodeTIL.setError("Entrer un code valide");
                    confirmCodeTIL.requestFocus();
                    return;
                }
                //verifying the code entered manually
                verifyCode(code);
            }
        });

        /*btnSendCodeAgain = findViewById(R.id.btnSendCodeAgain);
        btnSendCodeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CALL SERVICE AND SEND CODE AGAIN
            }
        });*/
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
                Toast.makeText(ConfirmCodeActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(ConfirmCodeActivity.this,"Une error est survenue. Demandez de l'aide à l'administrateur!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            Log.d("PHONE AUTH", "onCodeSent:" + verificationId);

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;
            btnInscription.setEnabled(false);
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

                            startActivity(new Intent(ConfirmCodeActivity.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                            /*User user = new User(mAuth.getCurrentUser().getUid(),"", completePhoneNumber);
                            SharedPrefManager.getmInstance(LoginActivity.this)
                                    .saveUser(new User(mAuth.getCurrentUser().getUid(),
                                            SharedPrefManager.getmInstance(LoginActivity.this).getUser().getName()
                                            , completePhoneNumber));
*/
                            /*if (!SharedPrefManager.getmInstance(LoginActivity.this).getUser().getId().equals("")){

                                User user = new User(mAuth.getCurrentUser().getUid(),"", completePhoneNumber);
                                SharedPrefManager.getmInstance(LoginActivity.this)
                                        .saveUser(new User(mAuth.getCurrentUser().getUid(),
                                                SharedPrefManager.getmInstance(LoginActivity.this).getUser().getName()
                                                , completePhoneNumber));

                            }else {
                                User user = new User(mAuth.getCurrentUser().getUid(),name, completePhoneNumber);
                                SharedPrefManager.getmInstance(LoginActivity.this)
                                        .saveUser(user);
                            }*/
                            /*mUserDatabase.child(mAuth.getCurrentUser().getUid()).child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //Toast.makeText(LoginActivity.this, "PHONE DATASNAPSHOT .. "+ dataSnapshot, Toast.LENGTH_LONG).show();

                                    System.out.println("PHONE DATASNAPSHOT .. "+ dataSnapshot);

                                    if (dataSnapshot.getValue() == null){

                                        mUserDatabase.child(mAuth.getCurrentUser().getUid()).child("phone").setValue(completePhoneNumber).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //Toast.makeText(LoginActivity.this, "PONE NOT  EXIST .. "+ mAuth.getCurrentUser().getUid(), Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(LoginActivity.this, RegisterUserFormActivity.class));
                                            }
                                        });

                                    }else {

                                        String phone = dataSnapshot.getValue().toString();

                                        //Toast.makeText(LoginActivity.this, "PONE EXIST .. "+ phone, Toast.LENGTH_LONG).show();
                                        if(phone.equals(completePhoneNumber)){

                                            //Toast.makeText(LoginActivity.this, "MAIN .. "+ mAuth.getCurrentUser().getUid(), Toast.LENGTH_LONG).show();

                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });*/

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(ConfirmCodeActivity.this, task.getException() .getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
