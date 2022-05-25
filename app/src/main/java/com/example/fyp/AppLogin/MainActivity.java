package com.example.fyp.AppLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fyp.AppSignIn.SignIn;
import com.example.fyp.RecyclerView.HomeActivity;
import com.example.fyp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText name,pass;
    TextView textView;
    ImageButton imageButton;
    public static String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        name = findViewById(R.id.EditEmail);
        pass = findViewById(R.id.EditPassword);


        textView = (TextView) findViewById(R.id.SignUp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignIn.class));
                Animatoo.animateSlideUp(MainActivity.this);


            }
        });

        imageButton = (ImageButton) findViewById(R.id.nextButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())) {


                    Toast.makeText(MainActivity.this, "Username or Password is Empty", Toast.LENGTH_LONG).show();
                }

                else {
                    Log();
                }


            }
        });

    }

    public void Log(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(name.getText().toString());
        loginRequest.setPassword(pass.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.login().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {



                            String token = response.body().getToken().toString();



                            SharedPreferences sharedPreferences = getSharedPreferences("secret", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("token", token);
                            edit.commit();

                            Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            Animatoo.animateSlideLeft(MainActivity.this);


                        }
                    },500);
                }
                else {

                    Toast.makeText(MainActivity.this,"Login Failed, Please try Again",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        })

        ;
    }
    { //no semicolon or coma

    }
    }



