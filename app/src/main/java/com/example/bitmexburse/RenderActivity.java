package com.example.bitmexburse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

class SimpleWebViewClientImpl extends WebViewClient {

    private Activity activity = null;

    public SimpleWebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (url.contains("bitmex.com")) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}
public class RenderActivity extends AppCompatActivity {
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_render);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        btn = findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(RenderActivity.this ,MyMenuActivity.class);
                startActivity(menu);
            }
        });
        WebView web = findViewById(R.id.webview);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        SimpleWebViewClientImpl webViewClient = new SimpleWebViewClientImpl(this);
        web.setWebViewClient(webViewClient);
        web.loadUrl("https://bitmex.com");
    }

}
