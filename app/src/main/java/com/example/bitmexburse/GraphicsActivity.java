package com.example.bitmexburse;

import android.graphics.Color;
import android.os.Bundle;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphicsActivity extends AppCompatActivity {
    String[] data = {"EUR", "USD", "RUB"};
    LineChartView lineChartView;
    String[] axisData = {"1","2","3","4","5","6","7","8","9","10","11"};
    List<Float> yAxisData = new ArrayList<>();
    String databit="";
    String singleParsedbit="";
    String dataParsedbit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lineChartView = findViewById(R.id.chart);

        final List yAxisValues = new ArrayList();
        final List axisValues = new ArrayList();

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
                if (position == 0){//EUR
                    //
                    //https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTUSD&depth=30
                    OkHttpClient client = new OkHttpClient();

                    String url ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTUSD&depth=5";
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
                                            for (int i=0;i<ja.length();i++) {
                                                JSONObject jo = (JSONObject) ja.get(i);
                                                singleParsedbit = jo.get("price")+"";
                                                dataParsedbit = dataParsedbit + singleParsedbit;
                                                //Toast.makeText(GraphicsActivity.this, ""+singleParsedbit, Toast.LENGTH_SHORT).show();
                                                Float result  = Float.valueOf(singleParsedbit);
                                                Toast.makeText(GraphicsActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                                yAxisData.add(result);



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

                    Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

                    for (int i = 0; i < axisData.length; i++) {
                        axisValues.add(i, new AxisValue(i).setLabel(String.valueOf(axisData[i])));
                    }

                    for (int i = 0; i < yAxisData.size(); i++) {
                        yAxisValues.add(new PointValue(i, yAxisData.get(i)));


                    }

                    List lines = new ArrayList();
                    lines.add(line);

                    LineChartData data = new LineChartData();
                    data.setLines(lines);

                    Axis axis = new Axis();
                    axis.setValues(axisValues);
                    axis.setTextSize(16);
                    axis.setTextColor(Color.parseColor("#03A9F4"));
                    data.setAxisXBottom(axis);

                    Axis yAxis = new Axis();
                    yAxis.setName("Sales in millions");
                    yAxis.setTextColor(Color.parseColor("#03A9F4"));
                    yAxis.setTextSize(16);
                    data.setAxisYLeft(yAxis);

                    lineChartView.setLineChartData(data);
                    Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
                    viewport.top = 2000;
                    lineChartView.setMaximumViewport(viewport);
                    lineChartView.setCurrentViewport(viewport);

                } else if (position == 1){//USD

                } else if (position == 2){//RUB

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
