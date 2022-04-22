package com.example.fyp.UserProfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fyp.R;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profileForm extends AppCompatActivity {

    private static final int RESULT_OK = -1;
    CircleImageView circleImageView,circleImageView2;
    private static final int PICK_Image=10;

    EditText Uname,Add,Num;
    RadioButton r1,r2;
    Button btn;


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
        r1 = findViewById(R.id.rd1);
        r2 = findViewById(R.id.rd2);

        btn = findViewById(R.id.Submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(Uname.getText().toString()) || TextUtils.isEmpty(Add.getText().toString()) || TextUtils.isEmpty(Num.getText())){

                    Toast.makeText(profileForm.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(profileForm.this, "We will be posting data to server!", Toast.LENGTH_LONG).show();
                    User();
                }
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

private void User(){

        UserProfRquest userProfRquest = new UserProfRquest();
        userProfRquest.setUsername(Uname.getText().toString());
        userProfRquest.setLocation(Add.getText().toString());
        userProfRquest.setPhone_number(Num.getText().toString());
        userProfRquest.setMale(r1.getText().toString());
        userProfRquest.setFemale(r2.getText().toString());

    Log.d("MyLog", "User() method!");

    /*Call<UserProfResponse> userProfResponseCall = UserApi.userPost().postProfile(userProfRquest);
    userProfResponseCall.enqueue(new Callback<UserProfResponse>() {
        @Override
        public void onResponse(Call<UserProfResponse> call, Response<UserProfResponse> response) {
            if (response.isSuccessful()){
                Toast.makeText(profileForm.this, "Sending", Toast.LENGTH_LONG).show();
            }

            else { Toast.makeText(profileForm.this,"Failed to add rooms",Toast.LENGTH_LONG).show();}

        }

        @Override
        public void onFailure(Call<UserProfResponse> call, Throwable t) {

            Toast.makeText(profileForm.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

        }
    });*/

}





}
