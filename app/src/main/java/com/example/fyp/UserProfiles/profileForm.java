package com.example.fyp.UserProfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.fyp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileForm extends AppCompatActivity {

    private static final int RESULT_OK = -1;
    CircleImageView circleImageView,circleImageView2;
    private static final int PICK_Image=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        circleImageView = findViewById(R.id.profilePic);

        circleImageView2 = findViewById(R.id.addProfile);
        circleImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_Image);


                //     startActivityForResult(Intent.createChooser(intent,"Select"),PICK_Image);

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
}}