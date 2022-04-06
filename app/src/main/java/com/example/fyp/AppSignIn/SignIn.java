package com.example.fyp.AppSignIn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fyp.AppLogin.MainActivity;
import com.example.fyp.R;
import com.example.fyp.UserProfiles.profileForm;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    Button button;
  //  private  static  String JSON_URL = "http://10.0.2.2:8000/api/signup";
    EditText u, p;
   TextView lgn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        p = (EditText) findViewById(R.id.editEmail);
        u = (EditText) findViewById(R.id.editFullName);

        button = (Button) findViewById(R.id.BtnSign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignUp();

              //  startActivity(new Intent(second.this, MainActivity.class));

                @SuppressLint("HandlerLeak") Handler h = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {

                        startActivity(new Intent(SignIn.this, profileForm.class));
                        Animatoo.animateCard(SignIn.this);
                    }
                };

                h.sendEmptyMessageDelayed(0, 1500); // 1500 is time in miliseconds
            }
        });

        lgn = (TextView) findViewById(R.id.lgn);
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, MainActivity.class));
                Animatoo.animateSlideDown(SignIn.this);
            }
        });


    }

    private void userSignUp(){

        String username = u.getText().toString().trim();
        String password = p.getText().toString().trim();



        Log.d("response", "user: " + username);

        Log.d("response", "pass: " + password);
        if (username.isEmpty()){
            u.setError("Email is required");
            u.requestFocus();
            return;
        }

        if (password.isEmpty()){
            p.setError("Password is required");
            p.requestFocus();
            return;
        }


        Call<ResponseBody> call = RetrofitSign
                 .getInstance()
                  .getSignUp()
                 .createUser(username,password );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                  //  String s = response.body().string();
                    if(response.body() != null ) {
                        String s = response.body().string();
                    }
                    Toast.makeText(SignIn.this,"Welcome, Your Account Has been Created",Toast.LENGTH_SHORT).show();

//                    Toast.makeText(second.this,s,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignIn.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    }


