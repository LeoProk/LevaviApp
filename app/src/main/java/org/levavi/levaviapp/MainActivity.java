package org.levavi.levaviapp;

import org.levavi.levaviapp.UserInterface.UIFactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout mSearchBox;

    private boolean mHideSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        UIFactory.getToolbar(this, toolbar).doTask();
        mSearchBox = (RelativeLayout)findViewById(R.id.search_bar);
        ImageView cancelImg = (ImageView)findViewById(R.id.close);
        cancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBox(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(mHideSearch==true){
            MenuItem item = menu.findItem(R.id.search);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                searchBox(true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchBox(boolean visibility){
        if(visibility == true) {
            mHideSearch = true;
            invalidateOptionsMenu();
            mSearchBox.setVisibility(View.VISIBLE);
        }else {
            mHideSearch = false;
            invalidateOptionsMenu();
            mSearchBox.setVisibility(View.INVISIBLE);
        }
    }
}
