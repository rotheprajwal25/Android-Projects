package com.example.prajwal.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
//api.openweathermap.org/data/2.5/weather?q=Paris&appid=5551f94e21153d70234d16c07a8a5065
    private Button button;
    private EditText cityName;
    private TextView result;
    private RequestQueue requestQueue;
    String url = "https://api.openweathermap.org/data/2.5/weather?q=";
    String API = "&appid=5551f94e21153d70234d16c07a8a5065";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        cityName = findViewById(R.id.getCity);
        result = findViewById(R.id.result);
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(cityName.getText().toString()!=null){
                    String my_URL = url + cityName.getText().toString() + API;


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, my_URL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("weather");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String condition = jsonObject.getString("description");

                                JSONObject object = response.getJSONObject("main");
                                Double temp = object.getDouble("temp");

                                result.setText("Weather: " + condition + "\n" + "Temperature: " + temp.toString());


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    requestQueue.add(jsonObjectRequest);}
                    else
                        result.setText("Enter City Name First!!");
                }

        });
    }
}
