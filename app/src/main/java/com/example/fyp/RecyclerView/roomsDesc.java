package com.example.fyp.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;
import com.example.fyp.RecyclerView.Report.ApiReport;
import com.example.fyp.RecyclerView.Report.ReportRequest;
import com.example.fyp.RecyclerView.Report.ReportResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class roomsDesc extends AppCompatActivity {

    private TextView sup,identity, name,address, description, garage, net,email,pnum,Apost,Pdate,Uid;
    private ImageView imageView;
    Button button, cancleBtn;
    ImageView rImage;
    Button reportBtn;
    EditText ReportDesc;
    int RoomId;

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
        Uid = findViewById(R.id.RId);
        rImage = findViewById(R.id.report);
        cancleBtn = findViewById(R.id.CancleBtn);
        reportBtn = findViewById(R.id.ReportBtn);
        ReportDesc = findViewById(R.id.ReportDesc);


        Uid.setText(getIntent().getStringExtra("id"));
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

        RoomId = Integer.parseInt(getIntent().getStringExtra("id"));



        ImageView im = findViewById(R.id.backArrow);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(roomsDesc.this, HomeActivity.class));
            }
        });


        button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(roomsDesc.this,HomeActivity.class));
            }
        });

        rImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReportDesc.setVisibility(View.VISIBLE);
                reportBtn.setVisibility(View.VISIBLE);
                cancleBtn.setVisibility(View.VISIBLE);
                rImage.setVisibility(View.INVISIBLE);

            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReportDesc.setVisibility(View.INVISIBLE);
                reportBtn.setVisibility(View.INVISIBLE);
                cancleBtn.setVisibility(View.INVISIBLE);
                rImage.setVisibility(View.VISIBLE);

            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void kk(){
        jhj jhuh = hh;
    }

}