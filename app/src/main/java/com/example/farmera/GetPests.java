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

public class GetPests extends AppCompatActivity {
    Workbook workbook2;
    AsyncHttpClient asyncHttpClient2;
    List<String> pestn, pestg, p2017, p2018, p2019;
    RecyclerView recyclerView2;
    Adapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pests);

        recyclerView2 = findViewById(R.id.recyclerView2);


        String URL = "https://github.com/praatibhsurana/Farmera-v1.1.1/blob/farmera-v1/pestdata%20(1).xls?raw=true";
        //String apiURL = "https://bikashthapa01.github.io/excel-reader-android-app/";
        pestn = new ArrayList<>();
        pestg = new ArrayList<>();
        p2017 = new ArrayList<>();
        p2018 = new ArrayList<>();
        p2019 = new ArrayList<>();

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

        asyncHttpClient2 = new AsyncHttpClient();
        asyncHttpClient2.get(URL, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(GetPests.this, "Unable to fetch data. Check your internet.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null){
                    //text.setText("Success, DO something with the file.");


                    try {
                        workbook2 = Workbook.getWorkbook(file);
                        Sheet sheet = workbook2.getSheet(0);
                        //Cell[] row = sheet.getRow(1);
                        //text.setText(row[0].getContents());
                        for(int i = 1; i < sheet.getRows(); i++){
                            Cell[] row = sheet.getRow(i);
                            pestn.add(row[0].getContents());
                            pestg.add( row[1].getContents());
                            p2017.add(row[2].getContents());
                            p2018.add(row[3].getContents());
                            p2019.add(row[4].getContents());

                        }

                        showData2();


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showData2() {
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new Adapter2(this, pestn,  pestg, p2017, p2018, p2019);
        recyclerView2.setAdapter(adapter2);
    }
}