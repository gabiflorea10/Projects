package com.example.rehearsalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText nameSignUp, emailSignUp, passwordSignUp, bbidSignUp;
    Button btnSignUp;
    TextView tvSignUp;
    ProgressBar progressBar;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();

        nameSignUp = findViewById(R.id.nameSignUp);
        emailSignUp = findViewById(R.id.emailSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        btnSignUp = findViewById(R.id.buttonSignUp);
        tvSignUp = findViewById(R.id.textViewSignUp);
        progressBar = findViewById(R.id.progressBarSignUp);
        progressBar.setVisibility(View.GONE);
        bbidSignUp = findViewById(R.id.bbidSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameText = nameSignUp.getText().toString();
                final String emailText  = emailSignUp.getText().toString();
                String passwordText = passwordSignUp.getText().toString();
                final String bbidText = bbidSignUp.getText().toString();

                if(nameText.equals("")){
                    nameSignUp.setError("Please enter name");
                    nameSignUp.requestFocus();
                }
                else if(emailText.equals("")){
                    emailSignUp.setError("Please enter email");
                    emailSignUp.requestFocus();
                }
                else if (passwordText.equals("")){
                    passwordSignUp.setError("Please enter password");
                    passwordSignUp.requestFocus();
                }
                else if (bbidText.equals("")){
                    bbidSignUp.setError("Please enter brass band identifier");
                    bbidSignUp.requestFocus();
                }
                else if (nameText.equals("") && emailText.equals("") && passwordText.equals("") && bbidText.equals("")){
                    Toast.makeText(SignupActivity.this, "Fields are empty!", Toast.LENGTH_LONG).show();
                }
                else if (!(nameText.equals("") && emailText.equals("") && passwordText.equals("") && bbidText.equals(""))){
                    progressBar.setVisibility(View.VISIBLE);
                    mFirebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "SignUp unsuccessful!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                User user = new User();
                                user.setName(nameText);
                                user.setEmail(emailText);
                                user.setType("user");
                                user.setBbid(bbidText);

                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignupActivity.this, "SignUp successful!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else  {
                    Toast.makeText(SignupActivity.this, "Error occurred!", Toast.LENGTH_LONG).show();
                }

            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}