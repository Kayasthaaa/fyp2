package com.example.fyp.MyRoomListings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.R;
import com.example.fyp.RecyclerView.Adapter;
import com.example.fyp.RecyclerView.HomeActivity;
import com.example.fyp.RecyclerView.Lists;
import com.example.fyp.RecyclerView.roomsDesc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListings extends AppCompatActivity {

    RecyclerView recyclerView2;
    List<roomLists> list;
    private  static  String JSON_URL = "http://10.0.2.2:8000/api/myrooms";
    roomAdapter roomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView2 = findViewById(R.id.listRecy);
        list = new ArrayList<>();
        myLists();



    }

    private void myLists(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                        try {
                            JSONObject responseJSONObject = response.getJSONObject(i);

                            roomLists listings = new roomLists();

                            listings.setPoster(responseJSONObject.getString("title").toString());
                            listings.setLocation(responseJSONObject.getString("location").toString());
                            listings.setPrice(responseJSONObject.getString("price").toString());
                            listings.setDescription(responseJSONObject.getString("description").toString());
                            listings.setParking(responseJSONObject.getString("parking").toString());
                            listings.setInternet(responseJSONObject.getString("internet").toString());
                            listings.setEmail(responseJSONObject.getString("email").toString());
                            listings.setPhone_number(responseJSONObject.getString("phone_number").toString());
                            listings.setTitle(responseJSONObject.getString("poster").toString());
                            listings.setCreated(responseJSONObject.getString("created").toString());

                            list.add(listings);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


        }     recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                roomAdapter = new roomAdapter(getApplicationContext(), list, new roomAdapter.OnClickListener() {
                    @Override
                    public void onClick(roomLists list) {
                        Intent intent = new Intent(MyListings.this, myRoomDesc.class);
                        intent.putExtra("title", list.getPoster());
                        intent.putExtra("price", list.getPrice());
                        intent.putExtra("location", list.getLocation());
                        intent.putExtra("description", list.getDescription());
                        intent.putExtra("parking", list.getParking());
                        intent.putExtra("internet", list.getInternet());
                        intent.putExtra("email", list.getEmail());
                        intent.putExtra("phone_number", list.getPhone_number());
                        intent.putExtra("poster",list.getTitle());
                        intent.putExtra("created",list.getCreated());


                    }
                }
               );
                recyclerView2.setAdapter(roomAdapter);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        }){ //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                SharedPreferences p = getSharedPreferences("secret", MODE_PRIVATE);
                String token = p.getString("token", "");
                params.put("Authorization", "Token " + token);
                return params;
            }
        };


        requestQueue.add(arrayRequest);


    }}