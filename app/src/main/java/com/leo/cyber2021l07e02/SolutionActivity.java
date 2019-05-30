package com.leo.cyber2021l07e02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class SolutionActivity extends AppCompatActivity {

    private TextView tvSolutions;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        tvSolutions = findViewById(R.id.tvSolutions);
        webView = findViewById(R.id.webView);
    }

    public void onClick_Back(View view) {
    }
}
