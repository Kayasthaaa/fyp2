package com.example.fyp.Profiles.userProfileDisplay.ProfilePatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fyp.AppLogin.MainActivity;
import com.example.fyp.AppSignIn.SignIn;
import com.example.fyp.Favourites;
import com.example.fyp.MyRoomListings.MyListings;
import com.example.fyp.Profiles.userProfileDisplay.PlaceHolderApi;
import com.example.fyp.Profiles.userProfileDisplay.profileDetails;
import com.example.fyp.R;
import com.example.fyp.UserProfiles.profileForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileDisplay extends Fragment{

    TextView name, age, address, gender, num, uId;
    Button btn, btn2;
    EditText eName, eAge, eAdd, eGend, eNum;

    int RoomId;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String url="http://10.0.2.2:8000/profile/";
   // String url="https://jsonplaceholder.typicode.com/";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDisplay() {
        // Required empty public constructor
    }

    public static ProfileDisplay newInstance(String param1, String param2) {
        ProfileDisplay fragment = new ProfileDisplay();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_display, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        CardView cardView = view.findViewById(R.id.left);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Favourites.class);
                getActivity().startActivity(intent);
            }
        });

        CardView card = view.findViewById(R.id.right);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyListings.class);
                getActivity().startActivity(intent);
            }
        });


        name = view.findViewById(R.id.proName);
        age = view.findViewById(R.id.Age);
        address = view.findViewById(R.id.Address);
        gender = view.findViewById(R.id.Gend);
        num = view.findViewById(R.id.num);
        uId = view.findViewById(R.id.userId);
        btn = view.findViewById(R.id.done);
        btn2 = view.findViewById(R.id.post);


        eName = view.findViewById(R.id.edtName);
        eAge = view.findViewById(R.id.EdtAge);
        eAdd = view.findViewById(R.id.EdtAddress);
        eGend = view.findViewById(R.id.EGend);
        eNum = view.findViewById(R.id.Enum);



        ImageView imageView = view.findViewById(R.id.edt);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name.setVisibility(View.GONE);
                name.setVisibility(View.INVISIBLE);

                age.setVisibility(View.GONE);
                age.setVisibility(View.INVISIBLE);

                address.setVisibility(View.GONE);
                address.setVisibility(View.INVISIBLE);

                gender.setVisibility(View.GONE);
                gender.setVisibility(View.INVISIBLE);

                num.setVisibility(View.GONE);
                num.setVisibility(View.INVISIBLE);

                btn.setVisibility(View.GONE);
                btn.setVisibility(View.INVISIBLE);


                eName.setVisibility(View.VISIBLE);
                eAge.setVisibility(View.VISIBLE);
                eAdd.setVisibility(View.VISIBLE);
                eGend.setVisibility(View.VISIBLE);
                eNum.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);


            }
        });

        Button btn2 = view.findViewById(R.id.post);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name.setVisibility(View.VISIBLE);
                age.setVisibility(View.VISIBLE);
                address.setVisibility(View.VISIBLE);
                gender.setVisibility(View.VISIBLE);
                num.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);

                eName.setVisibility(View.INVISIBLE);
                eAge.setVisibility(View.INVISIBLE);
                eAdd.setVisibility(View.INVISIBLE);
                eGend.setVisibility(View.INVISIBLE);
                eNum.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);

                PatchProfiles();

            }
        });


        Button button = view.findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences p = getActivity().getSharedPreferences("secret", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = p.edit();
                editor.clear();
                editor.apply();

                Toast.makeText(getContext(), "Your add was posted successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        name.setText("");
        age.setText("");
        address.setText("");
        gender.setText("");
        num.setText("");
        uId.setText("");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = getActivity().getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);


        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
        Call<List<profileDetails>> call=placeHolderApi.getDetails(params);
        call.enqueue(new Callback<List<profileDetails>>() {
            @Override
            public void onResponse(Call<List<profileDetails>> call, Response<List<profileDetails>> response) {
                List<profileDetails> data=response.body();
                for (int i=0; i<data.size();i++){
                    name.append(""+data.get(i).getFullname());

                    age.append(""+data.get(i).getAge());

                    address.append("Address: "+data.get(i).getLocation());

                    gender.append("Gender: "+data.get(i).getGender());

                    num.append("Number: "+data.get(i).getPhone_number());

                    uId.append("Id: "+data.get(i).getId());

                    RoomId = Integer.parseInt(data.get(i).getId());}


                if(name.getText().toString().isEmpty())
                {
                    startActivity(new Intent(getActivity(),profileForm.class));
                    Animatoo.animateSlideDown(getActivity());
                }

            }




            @Override
            public void onFailure(Call<List<profileDetails>> call, Throwable t) {


                Log.e("kk","lal"+t);
                Toast.makeText(getContext(),"Error Cause : "+t,Toast.LENGTH_LONG).show();
            }
        });


    }



    private void PatchProfiles(){


        ProfilePatchRequest patchRequest = new ProfilePatchRequest();
        patchRequest.setFullname(eName.getText().toString());
        patchRequest.setAge(eAge.getText().toString());
        patchRequest.setLocation(eAdd.getText().toString());
        patchRequest.setGender(eGend.getText().toString());
        patchRequest.setPhone_number(eNum.getText().toString());


        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        SharedPreferences p = getActivity().getSharedPreferences("secret", Context.MODE_PRIVATE);
        String token = p.getString("token", "");
        params.put("Authorization", "Token " + token);

        Call<ProfilePatchResponse> patchResponseCall = ApiProfilePatch.profilePatch().ProfilePatch(params,RoomId,patchRequest);
        patchResponseCall.enqueue(new Callback<ProfilePatchResponse>() {
            @Override
            public void onResponse(Call<ProfilePatchResponse> call, Response<ProfilePatchResponse> response) {

                if (response.isSuccessful()){

                    Toast.makeText(getActivity(), "Your profile details were updated", Toast.LENGTH_LONG).show();

                }

                else { Toast.makeText(getActivity(),"Failed to update profile",Toast.LENGTH_LONG).show();}


            }

            @Override
            public void onFailure(Call<ProfilePatchResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();


            }
        });


    }

    }



