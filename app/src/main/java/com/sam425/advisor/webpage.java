package com.sam425.advisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webpage extends AppCompatActivity {
private WebView webpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);
        webpage=findViewById(R.id.webpage);
        webpage.setWebViewClient(new WebViewClient());
        webpage.loadUrl("https://www.paypal.com/in/signin");
    }
}
