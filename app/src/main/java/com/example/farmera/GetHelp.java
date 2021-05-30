package com.example.farmera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class GetHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);

        TextView view = (TextView)findViewById(R.id.text);
        String fText = "This is our attempt at making life a little easier for farmers across India."+"<div>The app provides an easy-to-use platform featuring a list of resources that can be constantly updated.</div>"+
                "<div>Some of the features hosted are:<br><br><ul><li>Enter your location to access various information such as weather, crops best suited to your area and a list of pesticides.</li><li>The payment feature allows you to make transactions by redirecting you to any UPI app downloaded on your phone.</li></ul></div>"
                +"<div>Reach out to us to leave feedback!<br><br>devika.leena@gmail.com<br>praatibhsurana@gmail.com<br>abhiamonkar@gmail.com</div>";
        view.setText(Html.fromHtml(fText));
    }
}