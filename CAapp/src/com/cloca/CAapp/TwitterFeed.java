package com.cloca.CAapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class TwitterFeed extends Activity {
     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          //setContentView(R.layout.twitter);
        
          // Let's display the progress in the activity title bar, like the
          // browser app does.
          getWindow().requestFeature(Window.FEATURE_PROGRESS);

          WebView webview = new WebView(this);
          setContentView(webview);
        
          webview.getSettings().setJavaScriptEnabled(true);
          webview.getSettings().setDomStorageEnabled(true);
          webview.setInitialScale(75);

          final Activity activity = this;
          webview.setWebChromeClient(new WebChromeClient() {
          public void onProgressChanged(WebView view, int progress) {
               // Activities and WebViews measure progress with different scales.
               // The progress meter will automatically disappear when we reach 100%
               activity.setProgress(progress * 1000);
          }
        });
        
webview.setWebViewClient(new WebViewClient() {
          

 });
       //This will load the webpage that we want to see
        webview.loadUrl("https://www.twitter.com/conont");

     }
}