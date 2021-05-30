package com.example.farmera;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class GetCrops extends AppCompatActivity {
    Workbook workbook;
    AsyncHttpClient asyncHttpClient;
    List<String> staten, staten1, distn, cyear, season, crop, area, prod;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_crops);

        recyclerView = findViewById(R.id.recyclerView);


        String URL = "https://github.com/praatibhsurana/Farmera/blob/main/crop_production.xls?raw=true";


        //String apiURL = "https://bikashthapa01.github.io/excel-reader-android-app/";
        staten = new ArrayList<>();
        distn = new ArrayList<>();
        cyear = new ArrayList<>();
        season = new ArrayList<>();
        crop = new ArrayList<>();
        area = new ArrayList<>();
        prod = new ArrayList<>();

//        // checking if the excel file has new content
//
//        try {
//            URL mURL = new URL(apiURL);
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) mURL.openConnection();
//            InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
//            // getting network os exception error
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(URL, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(GetCrops.this, "Unable to fetch data. Check your internet.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                String location = getIntent().getExtras().getString("Location");
                String city = location;
                Toast.makeText(GetCrops.this, city, Toast.LENGTH_SHORT).show();
                if(file != null){
                    //text.setText("Success, DO something with the file.");


                    try {
                        workbook = Workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        //Cell[] row = sheet.getRow(1);
                        //text.setText(row[0].getContents());
                        for(int i = 1;i< sheet.getRows(); i++) {
                            Cell[] row = sheet.getRow(i);
                            if(row[0].getContents().toLowerCase().contains(city.toLowerCase())||row[1].getContents().toLowerCase().contains(city.toLowerCase()))
                            {
                                staten.add(row[0].getContents());
                                distn.add(row[1].getContents());
                                cyear.add(row[2].getContents());
                                season.add(row[3].getContents());
                                crop.add(row[4].getContents());
                                area.add(row[5].getContents());
                                prod.add(row[6].getContents());
                            }

                        }



                        showData();


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, staten,  distn, cyear, season, crop, area, prod);
        recyclerView.setAdapter(adapter);
    }
}