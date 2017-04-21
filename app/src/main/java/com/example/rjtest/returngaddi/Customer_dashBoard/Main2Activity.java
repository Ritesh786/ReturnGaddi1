
package com.example.rjtest.returngaddi.Customer_dashBoard;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rjtest.returngaddi.Fragments_custom.AboutUsFragment;
import com.example.rjtest.returngaddi.Fragments_custom.ContactUsFragment;
import com.example.rjtest.returngaddi.Fragments_custom.CustomFragment;
import com.example.rjtest.returngaddi.R;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        CustomFragment fragment = new CustomFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame, fragment).addToBackStack("Return Gaddi").commit();
        manager.addOnBackStackChangedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share_custom) {
            return true;
        }
        if (id == R.id.action_logout_custom) {
            return true;
        }
        if (id == R.id.action_call_custom) {
            String phone = "+917553325445";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home_c) {

        } else if (id == R.id.notification_c) {

        } else if (id == R.id.about_c) {

            AboutUsFragment fragment = new AboutUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack("About Us").commit();
            manager.addOnBackStackChangedListener(this);

        } else if (id == R.id.policy_c) {

        } else if (id == R.id.contact_c) {

            ContactUsFragment fragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack("Contact Us").commit();
            manager.addOnBackStackChangedListener(this);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
        public void onBackStackChanged () {
            try {

                int lastBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount() - 1;

                FragmentManager.BackStackEntry lastBackStackEntry =
                        getSupportFragmentManager().getBackStackEntryAt(lastBackStackEntryCount);

                setTitle(lastBackStackEntry.getName());

            } catch (Exception e) {
                Main2Activity.this.finish();
            }
        }
    }

