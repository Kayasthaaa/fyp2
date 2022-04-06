package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class roomsDesc extends AppCompatActivity {

    private TextView sup,identity, name,address, description, garage, net,email,pnum,Apost,Pdate;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_desc);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.img);
        sup = findViewById(R.id.roomID);
        identity = findViewById(R.id.Idt);
        name = findViewById(R.id.Name);
        address = findViewById(R.id.Add);
        description = findViewById(R.id.descR);
        garage = findViewById(R.id.ParkR);
        net = findViewById(R.id.InternetR);
        email = findViewById(R.id.EmailR);
        pnum = findViewById(R.id.NumR);
        Apost = findViewById(R.id.nameR);
        Pdate = findViewById(R.id.PostR);


        identity.setText(getIntent().getStringExtra("title"));
        address.setText(getIntent().getStringExtra("location"));
        name.setText(getIntent().getStringExtra("price"));
        description.setText(getIntent().getStringExtra("description"));
        garage.setText(getIntent().getStringExtra("parking"));
        net.setText(getIntent().getStringExtra("internet"));
        email.setText(getIntent().getStringExtra("email"));
        pnum.setText(getIntent().getStringExtra("phone_number"));
        Apost.setText(getIntent().getStringExtra("poster"));
        Pdate.setText(getIntent().getStringExtra("created"));



    }
}