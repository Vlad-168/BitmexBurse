package com.example.bitmexburse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GraphicsActivity extends AppCompatActivity {
    String[] data = {"XBT", "XBTU19", "XBTZ19", "XBT7D_U105"};

    ArrayList<String> xAxisData = new ArrayList<>();
    ArrayList<Entry> yAxisData = new ArrayList<>();
    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    String databit="";
    String singleParsedbit="";
    String dataParsedbit="";

    ArrayList<String> xAxisData19 = new ArrayList<>();
    ArrayList<Entry> yAxisData19 = new ArrayList<>();
    ArrayList<ILineDataSet> lineDataSets19 = new ArrayList<>();

    String databit19="";
    String singleParsedbit19="";
    String dataParsedbit19="";

    ArrayList<String> xAxisDataz19 = new ArrayList<>();
    ArrayList<Entry> yAxisDataz19 = new ArrayList<>();
    ArrayList<ILineDataSet> lineDataSetsz19 = new ArrayList<>();

    String databitz19="";
    String singleParsedbitz19="";
    String dataParsedbitz19="";

    ArrayList<String> xAxisData105 = new ArrayList<>();
    ArrayList<Entry> yAxisData105 = new ArrayList<>();
    ArrayList<ILineDataSet> lineDataSets105 = new ArrayList<>();

    String databit105="";
    String singleParsedbit105="";
    String dataParsedbit105="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_graphics);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final LineChart linechart = findViewById(R.id.linechart);
        //Valutes
        //XBT
        OkHttpClient client = new OkHttpClient();

        String url ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTUSD&depth=10";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    GraphicsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            databit = databit + myResponse;

                            try {
                                JSONArray ja = new JSONArray(databit);
                                for (int i=0;i<ja.length()/2;i++) {
                                    JSONObject jo = (JSONObject) ja.get(i);
                                    singleParsedbit = jo.get("price")+"";
                                    dataParsedbit = dataParsedbit + singleParsedbit;
                                    //Toast.makeText(GraphicsActivity.this, ""+singleParsedbit, Toast.LENGTH_SHORT).show();
                                    Float result  = Float.valueOf(singleParsedbit);
                                    //Toast.makeText(GraphicsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                    yAxisData.add(new Entry(result, i+1));
                                    String index = String.valueOf(i+1);
                                    xAxisData.add(index);



                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
        databit="";
        dataParsedbit="";
        singleParsedbit="";
        Toast.makeText(this, "XBT LOADED", Toast.LENGTH_SHORT).show();
        //XBTU19
        String urlxbtu19 ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTU19&depth=10";
        Request request19 = new Request.Builder()
                .url(urlxbtu19)
                .build();
        client.newCall(request19).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    GraphicsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            databit19 = databit19 + myResponse;

                            try {
                                JSONArray ja = new JSONArray(databit19);
                                for (int i=0;i<ja.length()/2;i++) {
                                    JSONObject jo = (JSONObject) ja.get(i);
                                    singleParsedbit19 = jo.get("price")+"";
                                    dataParsedbit19 = dataParsedbit19 + singleParsedbit19;
                                    //Toast.makeText(GraphicsActivity.this, ""+singleParsedbit, Toast.LENGTH_SHORT).show();
                                    Float result  = Float.valueOf(singleParsedbit19);
                                    //Toast.makeText(GraphicsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                    yAxisData19.add(new Entry(result, i+1));
                                    String index = String.valueOf(i+1);
                                    xAxisData19.add(index);



                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
        databit19="";
        dataParsedbit19="";
        singleParsedbit19="";
        Toast.makeText(this, "XBTU19 LOADED", Toast.LENGTH_SHORT).show();
        //
        String urlz ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTZ19&depth=10";
        Request requestz = new Request.Builder()
                .url(urlz)
                .build();
        client.newCall(requestz).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    GraphicsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            databitz19 = databitz19 + myResponse;

                            try {
                                JSONArray ja = new JSONArray(databitz19);
                                for (int i=0;i<ja.length()/2;i++) {
                                    JSONObject jo = (JSONObject) ja.get(i);
                                    singleParsedbitz19 = jo.get("price")+"";
                                    dataParsedbitz19 = dataParsedbitz19 + singleParsedbitz19;
                                    //Toast.makeText(GraphicsActivity.this, ""+singleParsedbit, Toast.LENGTH_SHORT).show();
                                    Float result  = Float.valueOf(singleParsedbitz19);
                                    //Toast.makeText(GraphicsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                    yAxisDataz19.add(new Entry(result, i+1));
                                    String index = String.valueOf(i+1);
                                    xAxisDataz19.add(index);



                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
        databitz19="";
        dataParsedbitz19="";
        singleParsedbitz19="";
        Toast.makeText(this, "XBTZ19 LOADED", Toast.LENGTH_SHORT).show();
        //
        String url105 ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBT7D_U105&depth=10";
        Request request105 = new Request.Builder()
                .url(url105)
                .build();
        client.newCall(request105).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    GraphicsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            databit105 = databit105 + myResponse;

                            try {
                                JSONArray ja = new JSONArray(databit105);
                                for (int i=0;i<ja.length()/2;i++) {
                                    JSONObject jo = (JSONObject) ja.get(i);
                                    singleParsedbit105 = jo.get("price")+"";
                                    dataParsedbit105 = dataParsedbit105 + singleParsedbit105;
                                    //Toast.makeText(GraphicsActivity.this, ""+singleParsedbit, Toast.LENGTH_SHORT).show();
                                    Float result  = Float.valueOf(singleParsedbit105);
                                    //Toast.makeText(GraphicsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                    yAxisData105.add(new Entry(result, i+1));
                                    String index = String.valueOf(i+1);
                                    xAxisData105.add(index);



                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
        databit105="";
        dataParsedbit105="";
        singleParsedbit105="";
        Toast.makeText(this, "XB7D_U105 LOADED", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Success! All info loaded!", Toast.LENGTH_SHORT).show();
        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (position == 0){
                    //
                    //https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTUSD&depth=30
                    String[] xaxes = new String[xAxisData.size()];

                    for (int i=0;i<xAxisData.size();i++){
                        xaxes[i] = xAxisData.get(i).toString();
                    }

                    LineDataSet lineDataSet = new LineDataSet(yAxisData, "XBT");
                    lineDataSet.setDrawCircles(true);
                    lineDataSet.setColor(Color.GREEN);

                    lineDataSets.add(lineDataSet);

                    linechart.setData(new LineData(xaxes, lineDataSets));

                    linechart.setVisibleXRangeMaximum(12000f);
                    linechart.setTouchEnabled(true);
                    linechart.setDragEnabled(true);






                }

                if (position == 1){//XBTU19
                    String[] xaxes = new String[xAxisData19.size()];

                    for (int i=0;i<xAxisData19.size();i++){
                        xaxes[i] = xAxisData19.get(i).toString();
                    }

                    LineDataSet lineDataSet = new LineDataSet(yAxisData19, "XBTU19");
                    lineDataSet.setDrawCircles(true);
                    lineDataSet.setColor(Color.RED);

                    lineDataSets19.add(lineDataSet);

                    linechart.setData(new LineData(xaxes, lineDataSets19));

                    linechart.setVisibleXRangeMaximum(13000f);
                    linechart.setTouchEnabled(true);
                    linechart.setDragEnabled(true);




                }
                //XBTZ19
                if (position == 2){
                    String[] xaxes = new String[xAxisDataz19.size()];

                    for (int i=0;i<xAxisDataz19.size();i++){
                        xaxes[i] = xAxisDataz19.get(i).toString();
                    }

                    LineDataSet lineDataSet = new LineDataSet(yAxisDataz19, "XBTZ19");
                    lineDataSet.setDrawCircles(true);
                    lineDataSet.setColor(Color.BLUE);

                    lineDataSetsz19.add(lineDataSet);

                    linechart.setData(new LineData(xaxes, lineDataSetsz19));

                    linechart.setVisibleXRangeMaximum(14000f);
                    linechart.setTouchEnabled(true);
                    linechart.setDragEnabled(true);


                }
                //XBT7D_U105
                if (position == 3){
                    String[] xaxes = new String[xAxisData105.size()];

                    for (int i=0;i<xAxisData105.size();i++){
                        xaxes[i] = xAxisData105.get(i).toString();
                    }

                    LineDataSet lineDataSet = new LineDataSet(yAxisData105, "XBT7D_U105");
                    lineDataSet.setDrawCircles(true);
                    lineDataSet.setColor(Color.YELLOW);

                    lineDataSets105.add(lineDataSet);

                    linechart.setData(new LineData(xaxes, lineDataSets105));

                    linechart.setVisibleXRangeMaximum(1f);
                    linechart.setTouchEnabled(true);
                    linechart.setDragEnabled(true);

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
