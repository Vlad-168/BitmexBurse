package com.example.bitmexburse;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Html;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TimerTask timerTask;

    //xbt const
    String databit = "";
    String dataParsedbit = "";
    String singleParsedbit = "";
    //usd const
    String datausd = "";
    String dataParsedusd = "";
    String singleParsedusd = "";
    //eur const
    String dataeur = "";
    String dataParsedeur = "";
    String singleParsedeur = "";
    //xbtu19 const
    String databit19="";
    String dataParsedbit19="";
    String singleParsedbit19="";
    //xbtz19
    String databitz19="";
    String dataParsedbitz19="";
    String singleParsedbitz19="";
    //xbt7_u105
    String databit105="";
    String dataParsedbit105="";
    String singleParsedbit105="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView xbt = findViewById(R.id.xbt);
        final TextView xbtu19 = findViewById(R.id.xbtu19);
        final TextView xbtz19 = findViewById(R.id.xbtz19);
        final TextView xbt7_u105 = findViewById(R.id.xbt7_u105);
        //My code
        TextView name_of_burse = findViewById(R.id.name_of_burse);
        final TextView usd = findViewById(R.id.usd);
        final TextView eur = findViewById(R.id.eur);
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        name_of_burse.setText("Биржа на "+mydate);
        //get-request_for_bitcoin
        Timer timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //XBT
                OkHttpClient client = new OkHttpClient();

                String url ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTUSD&depth=1";
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
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    databit = databit + myResponse;

                                    try {
                                        JSONArray ja = new JSONArray(databit);
                                        for (int i=0;i<ja.length()-1;i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            singleParsedbit = jo.get("price")+"";
                                            dataParsedbit = dataParsedbit + singleParsedbit+"$";
                                        }
                                    }
                                        catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    xbt.setText("");
                                    xbt.setText(dataParsedbit);
                                    }
                                });
                            }
                    }
                });
                databit="";
                dataParsedbit="";
                singleParsedbit="";
                //XBTU19

                String url19 ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTU19&depth=1";
                Request request19 = new Request.Builder()
                        .url(url19)
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
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    databit19 = databit19 + myResponse;

                                    try {
                                        JSONArray ja = new JSONArray(databit19);
                                        for (int i=0;i<ja.length()-1;i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            singleParsedbit19 = jo.get("price")+"";
                                            dataParsedbit19 = dataParsedbit19 + singleParsedbit19+"$";
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    xbtu19.setText("");
                                    xbtu19.setText(dataParsedbit19);
                                }
                            });
                        }
                    }
                });
                databit19="";
                dataParsedbit19="";
                singleParsedbit19="";
                //XBTZ19
                String urlz19 ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBTZ19&depth=1";
                Request requestz19 = new Request.Builder()
                        .url(urlz19)
                        .build();
                client.newCall(requestz19).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            final String myResponse = response.body().string();
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    databitz19 = databitz19 + myResponse;

                                    try {
                                        JSONArray ja = new JSONArray(databitz19);
                                        for (int i=0;i<ja.length()-1;i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            singleParsedbitz19 = jo.get("price")+"";
                                            dataParsedbitz19 = dataParsedbitz19 + singleParsedbitz19+"$";
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    xbtz19.setText("");
                                    xbtz19.setText(dataParsedbitz19);
                                }
                            });
                        }
                    }
                });
                databitz19="";
                dataParsedbitz19="";
                singleParsedbitz19="";
                //XBT7D_U105
                String url105 ="https://www.bitmex.com/api/v1/orderBook/L2?symbol=XBT7D_U105&depth=1";
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
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    databit105 = databit105 + myResponse;

                                    try {
                                        JSONArray ja = new JSONArray(databit105);
                                        for (int i=0;i<ja.length()-1;i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            singleParsedbit105 = jo.get("price")+"";
                                            dataParsedbit105 = dataParsedbit105 + singleParsedbit105+"$";
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    xbt7_u105.setText("");
                                    xbt7_u105.setText(dataParsedbit105);
                                }
                            });
                        }
                    }
                });
                databit105="";
                dataParsedbit105="";
                singleParsedbit105="";
                //USDDDD


                String urlusd ="http://www.cbr-xml-daily.ru/daily_json.js";
                Request requestusd = new Request.Builder()
                        .url(urlusd)
                        .build();
                client.newCall(requestusd).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response responseusd) throws IOException {
                        if (responseusd.isSuccessful()){
                            final String myResponseusd = responseusd.body().string();
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    datausd = datausd + myResponseusd;
                                    datausd = "["+datausd+"]";
                                    try {
                                        JSONArray ja = new JSONArray(datausd);
                                        for (int i=0;i<ja.length();i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            JSONObject joo = (JSONObject) jo.get("Valute");
                                            JSONObject jooo = (JSONObject) joo.get("USD");
                                            singleParsedusd = jooo.get("Value")+"";
                                            dataParsedusd = dataParsedusd + singleParsedusd + "\u20BD";
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    usd.setText("");
                                    usd.setText(dataParsedusd);
                                }
                            });
                        }
                    }
                });
                datausd="";
                dataParsedusd="";
                singleParsedusd="";

                ///EUUUURR
                String urleur ="http://www.cbr-xml-daily.ru/daily_json.js";
                Request requesteur = new Request.Builder()
                        .url(urleur)
                        .build();
                client.newCall(requesteur).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response responseusd) throws IOException {
                        if (responseusd.isSuccessful()){
                            final String myResponseusd = responseusd.body().string();
                            StartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dataeur = dataeur + myResponseusd;
                                    dataeur = "["+dataeur+"]";
                                    try {
                                        JSONArray ja = new JSONArray(dataeur);
                                        for (int i=0;i<ja.length();i++) {
                                            JSONObject jo = (JSONObject) ja.get(i);
                                            JSONObject joo = (JSONObject) jo.get("Valute");
                                            JSONObject jooo = (JSONObject) joo.get("EUR");
                                            singleParsedeur = jooo.get("Value")+"";
                                            dataParsedeur = dataParsedeur + singleParsedeur+"\u20BD";
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    eur.setText("");
                                    eur.setText(dataParsedeur);
                                }
                            });
                        }
                    }
                });
                dataeur="";
                dataParsedeur="";
                singleParsedeur="";




            }
        };
        timer.schedule(timerTask,0,100000);
        //Endinggetrequest_bitcoin
        //https://currate.ru/api/?get=rates&pairs=USDRUB&key=92b43dfd78329b5554d0e5471b4b6d0c

        //get-request-USDRUB




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timerTask.cancel();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent courses = new Intent(StartActivity.this, StartActivity.class);
            startActivity(courses);

        } else if (id == R.id.nav_news) {
            Intent news = new Intent(StartActivity.this, NewsActivity.class);
            startActivity(news);

        } else if (id == R.id.nav_graphics) {
            Intent graphics = new Intent(StartActivity.this, GraphicsActivity.class);
            startActivity(graphics);

        } else if (id == R.id.nav_render) {
            Intent render = new Intent(StartActivity.this, RenderActivity.class);
            startActivity(render);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
