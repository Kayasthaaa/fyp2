package com.example.fyp.UserProfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fyp.AppSignIn.SignIn;
import com.example.fyp.Profiles.userProfileDisplay.ProfilePatch.ProfileDisplay;
import com.example.fyp.R;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profileForm extends AppCompatActivity {

    private static final int RESULT_OK = -1;
    CircleImageView circleImageView,circleImageView2;
    private static final int PICK_Image=10;

    EditText Uname,Add,Num,Gen,age;
    //RadioButton r1,r2;
    Button btn;
   // RadioGroup Rg;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Map<String, String> Params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Uname = findViewById(R.id.UserEdit);
        Add = findViewById(R.id.AddressEdit);
        Num = findViewById(R.id.NumEdit);
        Gen = findViewById(R.id.EdtGender);
        age = findViewById(R.id.EdtAge);
       /* r1 = findViewById(R.id.rd1);
        r2 = findViewById(R.id.rd2);
        Rg = findViewById(R.id.rg);*/



        btn = findViewById(R.id.Submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profile();

                /*if (TextUtils.isEmpty(Uname.getText().toString()) || TextUtils.isEmpty(Add.getText().toString()) || TextUtils.isEmpty(Num.getText())){

                    Toast.makeText(profileForm.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(profileForm.this, "We will be posting data to server!", Toast.LENGTH_LONG).show();
                    User();
                }*/
            }
        });

    }

    public void profile(){

        UserProfRquest userProfRquest = new UserProfRquest();
        userProfRquest.setFullname(Uname.getText().toString());
        userProfRquest.setLocation(Add.getText().toString());
        userProfRquest.setPhone_number(Num.getText().toString());
        userProfRquest.setGender(Gen.getText().toString());
        userProfRquest.setAge(age.getText().toString());

       // userProfRquest.setGender(r1.getText().toString());
       // userProfRquest.setGender(r2.getText().toString());



        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = profileForm.this.getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);

        Call<UserProfResponse> userProfResponseCall = UserApi.userPost().userProfile(params,userProfRquest);
        userProfResponseCall.enqueue(new Callback<UserProfResponse>() {
            @Override
            public void onResponse(Call<UserProfResponse> call, Response<UserProfResponse> response) {
                if (response.isSuccessful()){



                    Toast.makeText(profileForm.this,"Welcome",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(profileForm.this, ProfileDisplay.class));
                  //  Animatoo.animateSlideLeft(MainActivity.this);

                }

                else {
                    Toast.makeText(profileForm.this,"Login Failed, Please try Again",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<UserProfResponse> call, Throwable t) {

                Toast.makeText(profileForm.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if (requestCode == PICK_Image && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();
            circleImageView.setImageURI(uri);

    }

}






}
