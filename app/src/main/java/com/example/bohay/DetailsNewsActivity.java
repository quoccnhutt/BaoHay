package com.example.bohay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.drawerlayout.widget.DrawerLayout;

public class DetailsNewsActivity extends AppCompatActivity {

    TextView tv;
    WebView webView;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    String url = "http://192.168.43.204/doancoso3/tintuc/chitiettin/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);

        // drawer layout instance to toggle the menu icon to ope

        webView = findViewById(R.id.wv_detail);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String idtin=String.valueOf(intent.getIntExtra("idtin", 123));
        String http = url + idtin;

        webView.loadUrl(http);

//        tv.setText(http);
//        Toast.makeText(DetailsNewsActivity.this, idtin + "", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.option_menu_2, menu);
//        MenuItem item = menu.add(1, 100, 300, "Settings");
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }
}