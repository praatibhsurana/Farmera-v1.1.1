package com.example.farmera;

// This class is the screen that loads with all the options that the farmer can choose such as crops/pests/weather etc.
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FindDataMain extends AppCompatActivity {

    public Button weatherBtn;
    public Button cropsBtn;
    public Button pestsBtn;
    public Button resetLocationBtn;
    public Button helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_data_main);

        weatherBtn = (Button) findViewById(R.id.weatherBtn);
        cropsBtn = (Button) findViewById(R.id.cropsBtn);
        pestsBtn = (Button) findViewById(R.id.pestsBtn);
        resetLocationBtn = (Button) findViewById(R.id.resetLocBtn);
        helpBtn = (Button) findViewById(R.id.helpBtn);

    }

    public void getWeatherData(android.view.View view) {
        String enteredLocation = getIntent().getExtras().getString("Location");
        Intent intent = new Intent(FindDataMain.this, LocationActivity.class);
        intent.putExtra("Location", enteredLocation);
        startActivity(intent);
    }

    public void getCrops(android.view.View view) {
        String enteredLocation = getIntent().getExtras().getString("Location");
        Intent intent = new Intent(FindDataMain.this, GetCrops.class);
        intent.putExtra("Location", enteredLocation);
        startActivity(intent);
    }

    public void getPests(android.view.View view) {
        Intent intent = new Intent(FindDataMain.this, GetPests.class);
        startActivity(intent);
    }

    public void getResetLoc(android.view.View view) {
        Intent intent = new Intent(FindDataMain.this, FarmerMain.class);
        startActivity(intent);
    }

    public void getHelp(android.view.View view) {
        Intent intent = new Intent(FindDataMain.this, GetHelp.class);
        startActivity(intent);
    }

    public void getPayment(android.view.View view) {
        Intent intent = new Intent(FindDataMain.this, PayActivity.class);
        startActivity(intent);
    }
}