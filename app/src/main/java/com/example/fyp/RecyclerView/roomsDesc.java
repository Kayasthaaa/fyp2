package com.example.fyp.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;

public class roomsDesc extends AppCompatActivity {

    private TextView sup,identity, name,address, description, garage, net,email,pnum,Apost,Pdate;
    private ImageView imageView;
    Button button;

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

        ImageView im = findViewById(R.id.backArrow);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(roomsDesc.this, HomeActivity.class));
            }
        });

        button = (Button) findViewById(R.id.aa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(roomsDesc.this, "Your Response Has been Recorded", Toast.LENGTH_LONG).show();
            }
        });


    }
}