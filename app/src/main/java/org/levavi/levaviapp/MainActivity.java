package org.levavi.levaviapp;

import org.levavi.levaviapp.UserInterface.UIFactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        UIFactory.getToolbar(this, toolbar).doTask();
    }
}
