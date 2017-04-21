
package com.example.rjtest.returngaddi.DashBoard;

import android.content.Intent;
import android.net.Uri;
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

import com.example.rjtest.returngaddi.Fragments_custom.CustomFragment;
import com.example.rjtest.returngaddi.R;
import com.example.rjtest.returngaddi.Transporter_dashboard.AboutUsTransFragment;
import com.example.rjtest.returngaddi.Transporter_dashboard.BookingStatutsFrag;
import com.example.rjtest.returngaddi.Transporter_dashboard.ContactUsTransFragment;
import com.example.rjtest.returngaddi.Transporter_dashboard.RegisterVehicleFrag;
import com.example.rjtest.returngaddi.Transporter_dashboard.TransporterFragment;

public class ReturnGaddi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_gaddi);
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

        TransporterFragment fragment = new TransporterFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame_trans, fragment).addToBackStack("Return Gaddi").commit();
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
        getMenuInflater().inflate(R.menu.return_gaddi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share_trans) {
            return true;
        }
        if (id == R.id.Logout_trans) {
            return true;
        }
        if (id == R.id.action_call_trans) {
            String phone = "+917553325445";
            Intent transcall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(transcall);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            TransporterFragment fragment = new TransporterFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.frame_trans, fragment).addToBackStack("Return Gaddi").commit();
            manager.addOnBackStackChangedListener(this);

        } else if (id == R.id.notification) {

        } else if (id == R.id.about) {


            AboutUsTransFragment fragment = new AboutUsTransFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame_trans, fragment).addToBackStack("About Us").commit();
            manager.addOnBackStackChangedListener(this);

        } else if (id == R.id.policy) {

        } else if (id == R.id.contact) {

            ContactUsTransFragment fragment = new ContactUsTransFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame_trans, fragment).addToBackStack("Contact Us").commit();
            manager.addOnBackStackChangedListener(this);

        } else if (id == R.id.nav_register_vehicle) {

            RegisterVehicleFrag fragment = new RegisterVehicleFrag();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame_trans, fragment).addToBackStack("Register Vehicle").commit();
            manager.addOnBackStackChangedListener(this);

        } else if (id == R.id.my_vehicle) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {

        try {

            int lastBackStackEntryCount = getSupportFragmentManager().getBackStackEntryCount() - 1;

            FragmentManager.BackStackEntry lastBackStackEntry =
                    getSupportFragmentManager().getBackStackEntryAt(lastBackStackEntryCount);

            setTitle(lastBackStackEntry.getName());

        } catch (Exception e) {
           ReturnGaddi.this.finish();
        }

    }
}
