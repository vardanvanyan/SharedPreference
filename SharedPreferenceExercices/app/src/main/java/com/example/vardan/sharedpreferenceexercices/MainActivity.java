package com.example.vardan.sharedpreferenceexercices;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Model> list = new ArrayList<>();
    private static final String GSONLIST = "gson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setList();
        recycler();
        openDialog();
    }

    private void recycler() {
        RecyclerView recyclerView = findViewById(R.id.rv_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPrAdapter adapter = new SharedPrAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
    }

    private void openDialog() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewDialogFragment dialogFragment = new NewDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "tag");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveArrayList((ArrayList<Model>) list);
    }

    private void setList() {
        if (getArrayList() != null) {
            list = getArrayList();
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Model> getList() {
        return list;
    }

    private void saveArrayList(ArrayList<Model> list) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final Gson gson = new Gson();
        final String json = gson.toJson(list);
        editor.putString(GSONLIST, json);
        editor.apply();
    }

    private ArrayList<Model> getArrayList() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final Gson gson = new Gson();
        final String json = prefs.getString(GSONLIST, null);
        final Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
