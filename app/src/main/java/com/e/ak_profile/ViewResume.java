package com.e.ak_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ViewResume extends AppCompatActivity {

    WebView webView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_resume);

        webView=findViewById(R.id.web);

        String url = Uri.encode("https://aravindrajak7.000webhostapp.com/images/resume.pdf");
        String finalUrl = "http://docs.google.com/viewer?url=" + url + "&embedded=true";

        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.loadUrl(finalUrl);

        progressDialog=new ProgressDialog(ViewResume.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                progressDialog.dismiss();

                view.getSettings().setLoadsImagesAutomatically(true);
                webView.setVisibility(View.VISIBLE);

                Log.v("after load", view.getUrl());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressDialog.show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
                Log.e("error", description);

            }
        });
    }
}
