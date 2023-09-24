package com.example.netclanexplorer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.netclanexplorer.Fragments.ExploreFragment;
import com.example.netclanexplorer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView nvBottom;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private ImageView ivMenu;
    private LinearLayout llRefine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        llRefine = findViewById(R.id.ll_refine);
        ivMenu = findViewById(R.id.iv_menu);
        nvBottom = findViewById(R.id.nv_bottom);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawer_layout);

        nvBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return handleBottomNavigationItemClick(item);
            }
        });
        navigationView.setCheckedItem(R.id.my_network);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return handleNavigationDrawerItemClick(item);
            }
        });
        loadFragment(new ExploreFragment(getSupportFragmentManager()));
        ivMenu.setOnClickListener(v -> {
            toggleDrawer();
        });

        llRefine.setOnClickListener(v -> {
            startActivity(new Intent(this, RefineActivity.class));
        });
    }

    private void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer((GravityCompat.START));
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private boolean handleBottomNavigationItemClick(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.explore:
                Toast.makeText(this, "Fragment is changed!", Toast.LENGTH_SHORT).show();
                loadFragment(new ExploreFragment(getSupportFragmentManager()));
                break;
        }
        return true;
    }

    private boolean handleNavigationDrawerItemClick(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_network:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}