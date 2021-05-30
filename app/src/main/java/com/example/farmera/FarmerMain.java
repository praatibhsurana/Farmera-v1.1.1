package com.example.farmera;

// This is the screen that opens immediately after the farmer is successfully authenticated into the app.
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FarmerMain extends AppCompatActivity {

    public Button findBtn;
    public EditText loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);

        findBtn = (Button) findViewById(R.id.findDataBtn);
        loc = (EditText) findViewById(R.id.locationText);
    }

    public void findData(android.view.View view) {

        String location = String.valueOf(loc.getText());
        if (location.length() == 0) {
            Toast.makeText(FarmerMain.this, "Enter location", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(FarmerMain.this, FindDataMain.class);
            intent.putExtra("Location", location);
            startActivity(intent);
        }



    }
}