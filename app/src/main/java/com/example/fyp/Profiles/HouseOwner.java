package com.example.fyp.Profiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.FileUtils;
import android.provider.MediaStore;
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

//import com.example.fyp.ImagePost.ImageRetrofit;
import com.example.fyp.R;
//import com.example.fyp.RetrofitRooms;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    Uri uri;

    private Bitmap bitmap;

    //   String title,description,email,phone_number,location,price,internet,parking;

    EditText a,b,c,d,e,f;
    CheckBox g,h;
    ImageView i;
    boolean internet = false;
    boolean parking = false;

    String filePath = "";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private String mParam1;
    private String mParam2;
    private Object Part;

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


                rooms();

             /* if (TextUtils.isEmpty(a.getText().toString()) || TextUtils.isEmpty(b.getText().toString())
                      || TextUtils.isEmpty(c.getText().toString()) || TextUtils.isEmpty(d.getText().toString()) || TextUtils.isEmpty(e.getText().toString())
                      || TextUtils.isEmpty(f.getText().toString())){

                  Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_LONG).show();
              }
              else{
                 // rooms();

              }*/
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

                Log.d("Image path:","Here we are!");

            }
        });

    }

    public static String encodeToBase64(Bitmap image) {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if (requestCode == PICK_Image && resultCode == RESULT_OK && data != null){


            uri = data.getData();
            Log.d("Image path: ", uri.toString());
            imageView.setImageURI(uri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }

    @NonNull
    private RequestBody createPartFromString (String param) {
        return RequestBody.create(MultipartBody.FORM,param);
    }

  /*  @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri){


        File file1 = FileUtils.getFile(getActivity(), fileUri);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(fileUri)),
                file1
        );
        return MultipartBody.Part.createFormData(partName, file1.getName(), requestBody1);
    }*/




    private void rooms() {

        RoomPostRequest roomPostRequest = new RoomPostRequest();
        roomPostRequest.setTitle(a.getText().toString());
        roomPostRequest.setDescription(b.getText().toString());
        roomPostRequest.setEmail(c.getText().toString());
        roomPostRequest.setPhone_number(d.getText().toString());
        roomPostRequest.setLocation(e.getText().toString());
        roomPostRequest.setPrice(f.getText().toString());
        roomPostRequest.setPhoto1(i.toString());


        InputStream imageStream = null;
        try {
            imageStream = getActivity().getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
        String encodedImageData = encodeToBase64(yourSelectedImage);
        roomPostRequest.setPhoto1(encodedImageData);

        Log.d("Image path", encodedImageData);

        roomPostRequest.setPhoto1(encodedImageData);


       //
        if (g.isChecked()){
                internet = true;
        }

        if (h.isChecked()){
            parking = true;
        }

        roomPostRequest.setInternet(internet);
        roomPostRequest.setParking(parking);



        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = getActivity().getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);

/*

        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part =  MultipartBody.Part.createFormData("newimage", file.getName(), requestBody);

        RequestBody imagedata = RequestBody.create(MediaType.parse("text/plain"),"New");

        Retrofit retrofit = ApiPost.getRetrofit();
        RoomPost roomPost  = retrofit.create(RoomPost.class);*/
     //   Call cll = roomPost.postRooms(part, imagedata);

/*

       Map<String, RequestBody> partMap = new HashMap<>();
        partMap.put("title",createPartFromString(a.getText().toString()));
        partMap.put("description",createPartFromString(b.getText().toString()));
        partMap.put("email",createPartFromString(c.getText().toString()));
        partMap.put("phone_number",createPartFromString(d.getText().toString()));
        partMap.put("location",createPartFromString(e.getText().toString()));
        partMap.put("price",createPartFromString(f.getText().toString()));
        partMap.put("internet",createPartFromString(g.getText().toString()));
        partMap.put("parking",createPartFromString(h.getText().toString()));
       // partMap.put("photo1",createPartFromString(i.getText().toString()));


*/


       Call<RoomPostResponse> RoomPostRsp = ApiPost.roomPost().postRooms(params, /*part, imagedata,*/ roomPostRequest);
     //   Call<RoomPostResponse> RoomPostRsp = ApiPost.roomPost().postRooms(partMap,);
        RoomPostRsp.enqueue(new Callback<RoomPostResponse>() {
            @Override
            public void onResponse(Call<RoomPostResponse> call, Response<RoomPostResponse> response) {
               // Log.i("name", "pst: " + roomPostRequest);
                if (response.isSuccessful()){


                    Toast.makeText(getContext(), "Sending", Toast.LENGTH_LONG).show();

    }
                else { Toast.makeText(getActivity(),"Failed to add rooms",Toast.LENGTH_LONG).show();}

}

            @Override
            public void onFailure(Call<RoomPostResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }



    }






