package com.e.ak_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class PDFViewer extends AppCompatActivity {

    @BindView(R.id.pdfView)
    WebView pdfView;

    String pdf_url="https://docs.google.com/gview?embedded=true&url=http://www.irs.gov/pub/irs-pdf/fw4.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        ButterKnife.bind(PDFViewer.this);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
        startActivity(browserIntent);

        //pdfView.loadUrl("https://docs.google.com/gview?embedded=true&url=http://www.irs.gov/pub/irs-pdf/fw4.pdf");

    }
}
