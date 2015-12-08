package org.levavi.levaviapp;

import org.levavi.levaviapp.UserInterface.UIFactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout mSearchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        UIFactory.getToolbar(this, toolbar).doTask();
        mSearchBox = (RelativeLayout)findViewById(R.id.search_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                showSearchBox();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchBox(){
        mSearchBox.setVisibility(View.VISIBLE);
    }
}
