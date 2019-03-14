package com.juliaqibteam.find.word.definitions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.juliaqibteam.find.word.definitions.drawer.Advance3DDrawer1Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, Advance3DDrawer1Activity.class));
    }

    @Override
    public void onClick(View v) {

    }
}
