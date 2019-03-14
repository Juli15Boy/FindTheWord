package com.juliaqibteam.find.word.definitions.drawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.infideap.drawerbehavior.Advance3DDrawerLayout;
import com.juliaqibteam.find.word.definitions.AboutFragment;
import com.juliaqibteam.find.word.definitions.AppsFragment;
import com.juliaqibteam.find.word.definitions.DefinitionFragment;
import com.juliaqibteam.find.word.definitions.R;


public class Advance3DDrawer1Activity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    private Advance3DDrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_3d_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchView searchView = findViewById(R.id.search_box);
        searchView.setVisibility(View.GONE);

        ImageButton imageButton = findViewById(R.id.search_btn);
        imageButton.setVisibility(View.GONE);

        TextView textView = findViewById(R.id.no_result);
        textView.setVisibility(View.GONE);

        drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(Gravity.LEFT);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.setViewScale(Gravity.START, 0.96f);
        drawer.setRadius(Gravity.START, 20);
        drawer.setViewElevation(Gravity.START, 8);
        drawer.setViewRotation(Gravity.START, 15);
    }

    @Override
    public void onBackPressed() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* switch (item.getItemId()) {
            case R.id.action_right_drawer:
                drawer.openDrawer(Gravity.END);
                return true;
        }
        */
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        fragment = new DefinitionFragment();
        Bundle args = new Bundle();
        if (id == R.id.nav_definitions) {
            args.putInt(DefinitionFragment.ARG_WORD_NUMBER, 0);
        } else if (id == R.id.nav_synonyms) {
            args.putInt(DefinitionFragment.ARG_WORD_NUMBER, 1);
        } else if (id == R.id.nav_antonyms) {
            args.putInt(DefinitionFragment.ARG_WORD_NUMBER, 2);
        } else if (id == R.id.nav_examples) {
            args.putInt(DefinitionFragment.ARG_WORD_NUMBER, 3);
        } else if (id == R.id.nav_typeOf) {
            args.putInt(DefinitionFragment.ARG_WORD_NUMBER, 4);
        }else if (id == R.id.nav_about_us) {
            fragment = new AboutFragment();
        }else if (id == R.id.nav_more_apps) {
            fragment = new AppsFragment();
        }

        TextView textView = findViewById(R.id.homepage);
        textView.setVisibility(View.GONE);
        ListView lv = findViewById(R.id.results);
        lv.setAdapter(null);
        SearchView searchView = findViewById(R.id.search_box);
        searchView.setVisibility(View.VISIBLE);

        ImageButton imageButton = findViewById(R.id.search_btn);
        imageButton.setVisibility(View.VISIBLE);

        TextView no_result = findViewById(R.id.no_result);
        no_result.setVisibility(View.GONE);
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.f_word, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
