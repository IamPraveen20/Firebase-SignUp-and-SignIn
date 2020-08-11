package com.example.userregisterandlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText email_txt,password_txt,confirm_txt;
    Button btn_register;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        email_txt = findViewById(R.id.txtemail);
        password_txt = findViewById(R.id.txtpassword);
        confirm_txt = findViewById(R.id.txtconfirm);
        btn_register = findViewById(R.id.btnregister);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_txt.getText().toString();
                String password = password_txt.getText().toString();
                String confirm = confirm_txt.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter Email ID",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(confirm)){
                    Toast.makeText(getApplicationContext(),"Please enter confirm password",Toast.LENGTH_SHORT).show();
                }

                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Your password too short",Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirm)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }




            }
        });
    }


}
