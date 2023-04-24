package com.zybooks.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_getJoke, btn_getWeatherByID, btn_getWeatherByName;
    TextView txt_setup, txt_punchline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning values to each control in the layout
        btn_getJoke = findViewById(R.id.btn_getJoke);
        txt_setup = findViewById(R.id.txt_Setup);
        txt_punchline = findViewById(R.id.txt_punchline);



        // click listeners for each button
        btn_getJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"You clicked me.",Toast.LENGTH_LONG).show();
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                //String url = "https://api.api-ninjas.com/v1/quotes?category=happiness";
                String url = "https://official-joke-api.appspot.com/random_joke";


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                               //JSONObject setup = response.getString("setup");
                                String setup = "";
                                String punchline = "";
                                try {
                                    setup = response.getString("setup");
                                    punchline = response.getString("punchline");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                //textView.setText("Response: " + response.toString());
                                txt_setup.setText(response.toString());
                                txt_setup.setText(setup);
                                txt_punchline.setText(punchline);
                                //Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_LONG).show();

                            }
                        });
                queue.add(jsonObjectRequest);
                // Access the RequestQueue through your singleton class.
                //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);



//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                //textView.setText("Response is: " + response.substring(0,500));
//                                Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //textView.setText("That didn't work!");
//                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
//                    }
//                });

// Add the request to the RequestQueue.
                //queue.add(stringRequest);
            }
        });


    }
}