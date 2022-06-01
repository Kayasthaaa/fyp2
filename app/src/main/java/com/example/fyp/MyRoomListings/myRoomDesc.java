package com.example.fyp.MyRoomListings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.PatchForRooms.ApiPatch;
import com.example.fyp.PatchForRooms.Apidelete;
import com.example.fyp.PatchForRooms.RoomPatchRequest;
import com.example.fyp.PatchForRooms.RoomPatchResponse;
import com.example.fyp.R;
import com.example.fyp.RecyclerView.HomeActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myRoomDesc extends AppCompatActivity {

    private TextView identity, price,address, garage, net,poster,mail,Num,Pdate, rId;
    private ImageView imageView;
    private AutoCompleteTextView description;

    int Id;

    EditText rooms,Edtprice,Edtaddress,EdtDesc,Edtmail,Edtnum;
    Button edtBtn, doneBtn, deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_desc);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.imgage);
        identity = findViewById(R.id.ERooms);
        price = findViewById(R.id.EPrice);
        address = findViewById(R.id.EAddress);
        description = (AutoCompleteTextView) findViewById(R.id.EDesc);
        garage = findViewById(R.id.EPark);
        net = findViewById(R.id.EInternet);
        poster = findViewById(R.id.EName);
        mail = findViewById(R.id.EMail);
        Num = findViewById(R.id.RNum);
        Pdate = findViewById(R.id.EPost);
        rId = findViewById(R.id.GetIdRoom);


        identity.setText(getIntent().getStringExtra("title"));
        address.setText(getIntent().getStringExtra("location"));
        price.setText(getIntent().getStringExtra("price"));
        description.setText(getIntent().getStringExtra("description"));
        garage.setText(getIntent().getStringExtra("parking"));
        net.setText(getIntent().getStringExtra("internet"));
        poster.setText(getIntent().getStringExtra("poster"));
        mail.setText(getIntent().getStringExtra("email"));
        Num.setText(getIntent().getStringExtra("phone_number"));
        Pdate.setText(getIntent().getStringExtra("created"));
        rId.setText(getIntent().getStringExtra("id"));

        Id = Integer.parseInt(getIntent().getStringExtra("id"));

        ImageView im = findViewById(R.id.backArrow);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myRoomDesc.this, HomeActivity.class));
            }
        });


        rooms = findViewById(R.id.EditRooms);
        Edtprice = findViewById(R.id.EditPrice);
        Edtaddress = findViewById(R.id.EditAddress);
        EdtDesc = findViewById(R.id.EditDescripton);
        Edtmail = findViewById(R.id.EditMail);
        Edtnum = findViewById(R.id.EditNum);


        edtBtn = findViewById(R.id.back);
        edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                identity.setVisibility(View.GONE);
                identity.setVisibility(View.INVISIBLE);

                price.setVisibility(View.GONE);
                price.setVisibility(View.INVISIBLE);

                address.setVisibility(View.GONE);
                address.setVisibility(View.INVISIBLE);


                mail.setVisibility(View.GONE);
                mail.setVisibility(View.INVISIBLE);

                Num.setVisibility(View.GONE);
                Num.setVisibility(View.INVISIBLE);


                description.setVisibility(View.GONE);
                description.setVisibility(View.INVISIBLE);

                edtBtn.setVisibility(View.GONE);
                edtBtn.setVisibility(View.INVISIBLE);



                rooms.setVisibility(View.VISIBLE);
                Edtprice.setVisibility(View.VISIBLE);
                Edtaddress.setVisibility(View.VISIBLE);
                EdtDesc.setVisibility(View.VISIBLE);
                Edtmail.setVisibility(View.VISIBLE);
                Edtnum.setVisibility(View.VISIBLE);
                doneBtn.setVisibility(View.VISIBLE);





            }
        });

        deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });


        doneBtn = findViewById(R.id.EditDone);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rooms.setVisibility(View.INVISIBLE);
                Edtprice.setVisibility(View.INVISIBLE);
                Edtaddress.setVisibility(View.INVISIBLE);
                EdtDesc.setVisibility(View.INVISIBLE);
                Edtmail.setVisibility(View.INVISIBLE);
                Edtnum.setVisibility(View.INVISIBLE);
                doneBtn.setVisibility(View.INVISIBLE);


                identity.setVisibility(View.VISIBLE);

                price.setVisibility(View.VISIBLE);

                address.setVisibility(View.VISIBLE);

                mail.setVisibility(View.VISIBLE);

                Num.setVisibility(View.VISIBLE);

                description.setVisibility(View.VISIBLE);

                edtBtn.setVisibility(View.VISIBLE);


                Patchrooms();

            }
        });

    }

    private void Patchrooms(){

        RoomPatchRequest roomPatchRequest = new RoomPatchRequest();
        roomPatchRequest.setTitle(rooms.getText().toString());
        roomPatchRequest.setPrice(Edtprice.getText().toString());
        roomPatchRequest.setLocation(Edtaddress.getText().toString());
        roomPatchRequest.setDescription(EdtDesc.getText().toString());
        roomPatchRequest.setEmail(Edtmail.getText().toString());
        roomPatchRequest.setPhone_number(Edtnum.getText().toString());


        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = myRoomDesc.this.getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);

        Call<RoomPatchResponse> roomPatchResponseCall = ApiPatch.roomPatch().patchRooms(params,Id,roomPatchRequest);
        roomPatchResponseCall.enqueue(new Callback<RoomPatchResponse>() {
            @Override
            public void onResponse(Call<RoomPatchResponse> call, Response<RoomPatchResponse> response) {
                if (response.isSuccessful()){

                    Toast.makeText(myRoomDesc.this, "Your room details were updated", Toast.LENGTH_LONG).show();

                }

                else { Toast.makeText(myRoomDesc.this,"Failed to update rooms",Toast.LENGTH_LONG).show();}


            }

            @Override
            public void onFailure(Call<RoomPatchResponse> call, Throwable t) {

                Toast.makeText(myRoomDesc.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void Delete(){


        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = myRoomDesc.this.getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);


        Call<RoomPatchResponse> patchResponseCall = Apidelete.roomdelete().deleteRoom(params,Id);
        patchResponseCall.enqueue(new Callback<RoomPatchResponse>() {
            @Override
            public void onResponse(Call<RoomPatchResponse> call, Response<RoomPatchResponse> response) {
                if (response.isSuccessful()){

                    Toast.makeText(myRoomDesc.this, "Your room details were updated", Toast.LENGTH_LONG).show();

                }

                else { Toast.makeText(myRoomDesc.this,"Failed to update rooms",Toast.LENGTH_LONG).show();}



            }

            @Override
            public void onFailure(Call<RoomPatchResponse> call, Throwable t) {

                Toast.makeText(myRoomDesc.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }

}