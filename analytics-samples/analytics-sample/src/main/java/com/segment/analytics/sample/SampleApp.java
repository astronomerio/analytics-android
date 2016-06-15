package com.segment.analytics.sample;

import android.app.Application;
import android.widget.Toast;
import com.segment.analytics.Analytics;

public class SampleApp extends Application {

  // https://segment.com/segment-engineering/sources/android-test/settings/keys
  private static final String ANALYTICS_WRITE_KEY = "M22kc6xTTPyKmpfFg";

  @Override public void onCreate() {
    super.onCreate();

    // Initialize a new instance of the Analytics client.
    Analytics.Builder builder = new Analytics.Builder(this, ANALYTICS_WRITE_KEY) //
        .trackApplicationLifecycleEvents() //
        .recordScreenViews();
    if (BuildConfig.DEBUG) {
      builder.logLevel(Analytics.LogLevel.VERBOSE);
    }

    // Set the initialized instance as a globally accessible instance.
    Analytics.setSingletonInstance(builder.build());

    // Now anytime you call Analytics.with, the custom instance will be returned.
    Analytics analytics = Analytics.with(this);

    // If you need to know when integrations have been initialized, use the onIntegrationReady
    // listener.
    analytics.onIntegrationReady("Segment.io", new Analytics.Callback() {
      @Override public void onReady(Object instance) {
        Toast.makeText(SampleApp.this, "Astronomer integration!", Toast.LENGTH_LONG).show();
      }
    });
  }
}
