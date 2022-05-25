package com.example.fyp.MyRoomListings;

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
import com.example.fyp.RecyclerView.HomeActivity;

public class myRoomDesc extends AppCompatActivity {

    private TextView identity, price,address, description, garage, net,poster,mail,Num,Pdate;
    private ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_desc);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.imgage);
        identity = findViewById(R.id.ERooms);
        price = findViewById(R.id.EPrice);
        address = findViewById(R.id.EAddress);
        description = findViewById(R.id.EDesc);
        garage = findViewById(R.id.EPark);
        net = findViewById(R.id.EInternet);
        poster = findViewById(R.id.EName);
        mail = findViewById(R.id.EMail);
        Num = findViewById(R.id.RNum);
        Pdate = findViewById(R.id.EPost);


        identity.setText(getIntent().getStringExtra("title"));
        address.setText(getIntent().getStringExtra("location"));
        price.setText(getIntent().getStringExtra("price"));
        description.setText(getIntent().getStringExtra("description"));
        garage.setText(getIntent().getStringExtra("parking"));
        net.setText(getIntent().getStringExtra("internet"));
        poster.setText(getIntent().getStringExtra("email"));
        mail.setText(getIntent().getStringExtra("phone_number"));
        Num.setText(getIntent().getStringExtra("poster"));
        Pdate.setText(getIntent().getStringExtra("created"));

        ImageView im = findViewById(R.id.backArrow);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myRoomDesc.this, HomeActivity.class));
            }
        });

        button = (Button) findViewById(R.id.aa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myRoomDesc.this, "Your Response Has been Recorded", Toast.LENGTH_LONG).show();
            }
        });


    }
}