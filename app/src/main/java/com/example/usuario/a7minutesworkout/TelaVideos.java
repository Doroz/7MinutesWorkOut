package com.example.usuario.a7minutesworkout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class TelaVideos extends AppCompatActivity {

    WebView webView;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_videos);

        webView = (WebView) findViewById(R.id.webView);
        titulo = (TextView) findViewById(R.id.textoNomeExercicio);

        webView.setWebViewClient(new MyBrowser());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/watch?v=xlrE5Gz1_9w");
        webView.setWebViewClient(new WebViewClient());

        String nomeVideo = getIntent().getExtras().getString("nomeVideo");
        titulo.setText(nomeVideo);
    }

    private class MyBrowser extends WebViewClient{
        public boolean overrideUrlLoading (WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
