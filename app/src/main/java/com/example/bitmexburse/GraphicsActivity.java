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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GraphicsActivity extends AppCompatActivity {
    String[] data = {"EUR", "USD", "RUB"};
    LineChartView lineChartView;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

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
                    Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

                    for (int i = 0; i < axisData.length; i++) {
                        axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
                    }

                    for (int i = 0; i < yAxisData.length; i++) {
                        yAxisValues.add(new PointValue(i, yAxisData[i]));
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
                    viewport.top = 110;
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
