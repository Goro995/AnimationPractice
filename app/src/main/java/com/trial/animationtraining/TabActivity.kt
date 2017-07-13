package com.trial.animationtraining

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TextView
import java.util.*


class TabActivity : android.app.TabActivity() {

    internal var tabHost: TabHost? = null
    internal var intent: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_activity)

        setupTabBar()
    }


    fun setupTabBar() {

        tabHost = findViewById(android.R.id.tabhost) as TabHost
        // initialization
        tabHost!!.setup()

        var tabSpec: TabHost.TabSpec

        intent = Intent().setClass(this, MainActivity::class.java)
        tabSpec = tabHost!!.newTabSpec("resize").setIndicator("View resize").setContent(intent)
        tabHost!!.addTab(tabSpec)


        intent = Intent().setClass(this, ViewsPolymorph::class.java)
        tabSpec = tabHost!!.newTabSpec("polymorph").setIndicator("View polymorph").setContent(intent)
        tabHost!!.addTab(tabSpec)

        intent = Intent().setClass(this, TimerActivity::class.java)
        tabSpec = tabHost!!.newTabSpec("timer").setIndicator("Timer").setContent(intent)
        tabHost!!.addTab(tabSpec)

        tabHost!!.currentTab = 1
        // This tab will be chosen as default
        tabHost!!.currentTab = 0

        // Handler of tab change
        tabHost!!.setOnTabChangedListener { }
    }
}