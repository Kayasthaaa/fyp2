package com.example.fyp.Profiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
//import com.example.fyp.ImagePost.ImageRetrofit;
import com.example.fyp.R;
//import com.example.fyp.RetrofitRooms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HouseOwner extends Fragment {


    private static final int RESULT_OK = -1;
    CircleImageView circleImageView;
    ImageView imageView;
    private static final int PICK_Image=1;
    Button button;

    private Bitmap bitmap;

    //   String title,description,email,phone_number,location,price,internet,parking;

    EditText a,b,c,d,e,f;
    CheckBox g,h;
    ImageView i;
    boolean internet = false;
    boolean parking = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HouseOwner() {
        // Required empty public constructor
    }


    public static HouseOwner newInstance(String param1, String param2) {
        HouseOwner fragment = new HouseOwner();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity(). getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_house_owner, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



     /*   TextView textView = view.findViewById(R.id.search);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startActivity();));*//*
            }
        });*/

        imageView = view.findViewById(R.id.pick);

        a = view.findViewById(R.id.edtTitle);
        b = view.findViewById(R.id.edtdescription);
        c = view.findViewById(R.id.edtEmail);
        d = view.findViewById(R.id.edtphone);
        e = view.findViewById(R.id.edtLocation);
        f = view.findViewById(R.id.edtPrice);
        g = view.findViewById(R.id.rad1);
        h = view.findViewById(R.id.rad2);
        i = view.findViewById(R.id.pick);

        button = view.findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //   uploadImage();

              if (TextUtils.isEmpty(a.getText().toString()) || TextUtils.isEmpty(b.getText().toString())
                      || TextUtils.isEmpty(c.getText().toString()) || TextUtils.isEmpty(d.getText().toString()) || TextUtils.isEmpty(e.getText().toString())
                      || TextUtils.isEmpty(f.getText().toString())){

                  Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_LONG).show();
              }
              else{
                  rooms();

              }
            }
        });

        CircleImageView circleImageView = view.findViewById(R.id.add);
        circleImageView.setOnClickListener(new View.OnClickListener() {
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
            imageView.setImageURI(uri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }

  /*  private void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75,byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();

        String encodedImg  = Base64.encodeToString(imageInByte, Base64.DEFAULT);
*/

/*
        Call<ImgResponse> call = ImageRetrofit.getInstance().getApi().uploadImage(encodedImg);
        call.enqueue(new Callback<ImgResponse>() {
            @Override
            public void onResponse(Call<ImgResponse> call, Response<ImgResponse> response) {

            Toast.makeText(getActivity(), response.body().getPhoto1(), Toast.LENGTH_SHORT).show();

            if (response.isSuccessful()){

            }

            else{}

            }

            @Override
            public void onFailure(Call<ImgResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();



            }
        });*/

    //}



    private void rooms() {

        RoomPostRequest roomPostRequest = new RoomPostRequest();
        roomPostRequest.setTitle(a.getText().toString());
        roomPostRequest.setDescription(b.getText().toString());
        roomPostRequest.setEmail(c.getText().toString());
        roomPostRequest.setPhone_number(d.getText().toString());
        roomPostRequest.setLocation(e.getText().toString());
        roomPostRequest.setPrice(f.getText().toString());
        //roomPostRequest.setPhoto1(i.toString());
       //
        if (g.isChecked()){
                internet = true;
        }

        if (h.isChecked()){
            parking = true;
        }

        roomPostRequest.setInternet(internet);
        roomPostRequest.setParking(parking);


        SharedPreferences sp = getContext().getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = sp.getString("token","");
        Log.i("lalala", "Token: " + token);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = getActivity().getSharedPreferences("secret", Context.MODE_PRIVATE);
        String tokens = p.getString("token", "");
        params.put("Authorization", "Token " + token);


        Call<RoomPostResponse> RoomPostRsp = ApiPost.roomPost().postRooms(params, roomPostRequest);
        RoomPostRsp.enqueue(new Callback<RoomPostResponse>() {
            @Override
            public void onResponse(Call<RoomPostResponse> call, Response<RoomPostResponse> response) {
                if (response.isSuccessful()){


//                            Log.i("login", "Token: " + token);



                    Toast.makeText(getActivity(), "Sending", Toast.LENGTH_LONG).show();

    }
                else { Toast.makeText(getActivity(),"Failed to add rooms",Toast.LENGTH_LONG).show();}

}

            @Override
            public void onFailure(Call<RoomPostResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });{

    }

    }}






