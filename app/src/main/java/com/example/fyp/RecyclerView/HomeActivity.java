package com.example.fyp.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Favourites;
import com.example.fyp.Profiles.Profile;
import com.example.fyp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    List<Lists> lst;

    private  static  String JSON_URL = "http://10.0.2.2:8000/api/rooms";
    Adapter adapter;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        AutoCompleteTextView editText = findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        recyclerView = findViewById(R.id.recy);
        lst = new ArrayList<>();
        extractList ();

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.favourites:
                        startActivity(new Intent(getApplicationContext(), Favourites.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }

    private  void filter(String text){
        ArrayList<Lists> filteredList = new ArrayList<>();

        for (Lists item : lst) {
            if (item.getAddress().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

        }
        adapter.filterList(filteredList);
    }

    private void extractList() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Log.d("tags", "error: " + jsonObject);

                        Lists lists = new Lists();

                        lists.setName(jsonObject.getString("title").toString());
                        lists.setAddress(jsonObject.getString("location").toString());
                        lists.setPrc(jsonObject.getString("price").toString());
                        lists.setDes(jsonObject.getString("description").toString());
                        lists.setGarage(jsonObject.getString("parking").toString());
                        lists.setNet(jsonObject.getString("internet").toString());
                        lists.setMail(jsonObject.getString("email").toString());
                        lists.setPnumber(jsonObject.getString("phone_number").toString());
                        lists.setTle(jsonObject.getString("poster").toString());
                        lists.setCrt(jsonObject.getString("created").toString());


                        lst.add(lists);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                 recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), lst, new Adapter.ItemClickListsner() {
                    @Override
                    public void onItemClick(Lists lst) {
                      //  startActivity(new Intent(HomeActivity.this,roomsDesc.class));
                        Intent intent = new Intent(HomeActivity.this, roomsDesc.class);
                        intent.putExtra("title",lst.getName());
                        intent.putExtra("price",lst.getPrc());
                        intent.putExtra("location",lst.getAddress());
                        intent.putExtra("description",lst.getDes());
                        intent.putExtra("parking",lst.getGarage());
                        intent.putExtra("internet",lst.getNet());
                        intent.putExtra("email",lst.getMail());
                        intent.putExtra("phone_number",lst.getPnumber());
                        intent.putExtra("poster",lst.getTle());
                        intent.putExtra("created",lst.getCrt());

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        HomeActivity.this.startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);

            }

           /* private void send(String message){
                Toast.makeText(this, message,Toast.LENGTH_LONG).show();
            }*/

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



        queue.add(jsonArrayRequest);


    }

}