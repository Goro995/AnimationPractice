package com.trial.animationtraining;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;



public class TabActivity extends android.app.TabActivity {

    private TabHost tabHost;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);

        setupTabBar();
    }


    public void setupTabBar() {

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // initialization
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        intent = new Intent().setClass(this, MainActivity.class);
        tabSpec = tabHost.newTabSpec("resize").setIndicator("View resize").setContent(intent);
        tabHost.addTab(tabSpec);


        intent = new Intent().setClass(this, ViewsPolymorph.class);
        tabSpec = tabHost.newTabSpec("polymorph").setIndicator("View polymorph").setContent(intent);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(1);
        // This tab will be chosen as default
        tabHost.setCurrentTab(0);

        // Handler of tab change
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {

            }
        });
    }
}