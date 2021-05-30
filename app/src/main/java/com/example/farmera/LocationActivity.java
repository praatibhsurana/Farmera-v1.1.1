package com.example.farmera;

// This is the page that opens when the "Weather" option is selected.

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class LocationActivity extends AppCompatActivity {

    //public EditText loc;
    public Button btn;
    public TextView txt;

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "f4ea9af1cbc315f81e003eeedab054cb";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //loc = (EditText) findViewById(R.id.location);
        btn = (Button) findViewById(R.id.mapbtn);
        txt = (TextView) findViewById(R.id.textView3);
    }

    public void getWeather(android.view.View view) {

        String location = getIntent().getExtras().getString("Location");
        String tempUrl = "";
        String city = location;

        if (city.equals("")) {
            System.out.println("error");
        }
        else {
            tempUrl = url + "?q=" + city + "&appid=" + appid;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d("response", response);
                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
                        output += "Current weather of " + cityName + " (" + countryName + ")"+"\n"
                                + "\n Temperature: " + df.format(temp) + " degrees Celsius"
                                + "\n Humidity: " + humidity + " %"
                                + "\n Wind Speed: " + wind + " m/s"
                                + "\n Cloudiness: " + clouds + " %"
                                + "\n Pressure: " + pressure + " hPa";
                        txt.setText(output);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Unable to fetch data", Toast.LENGTH_SHORT).show();
                }
            });
//            {
//                @Override
//                protected Map<String, String> getParams()throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
////                params.put("name", name);
////                params.put("email", email);
////                params.put("password", password);
//                return params;
//            }
//            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }


    }

}