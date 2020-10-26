package com.shashi.qtvapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_videos, R.id.nav_social_media, R.id.nav_about, R.id.nav_terms_condition)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuId = item.getItemId();       //get the id if menu

        switch (menuId) {
            case R.id.menu_social:
                showSocialMediaDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSocialMediaDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layoutView = layoutInflater.inflate(R.layout.social_media_dialog, null);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this).setView(layoutView);
        alertDialog.setCancelable(true);
        final AlertDialog testDialog = alertDialog.create();
        testDialog.show();

        Button buttonInstagram = layoutView.findViewById(R.id.button_instagram);
        Button buttonTwitter = layoutView.findViewById(R.id.button_Twitter);
        Button buttonFacebook = layoutView.findViewById(R.id.button_facebook);

        buttonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInsta();
                testDialog.dismiss();  // to dismiss
            }
        });

        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTwitter();
                testDialog.dismiss();  // to dismiss
            }
        });

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFacebook();
                testDialog.dismiss();  // to dismiss
            }
        });
    }

    private void openInsta() {
        Uri uri = Uri.parse("https://www.instagram.com/_sriansh_/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/_sriansh_/")));
        }
    }

    private void openFacebook() {
        Intent intent = null;
        try {
            this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/srianshsankhi/?view_public_for=221721918334613"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/srianshsankhi/?view_public_for=221721918334613"));
        }
        this.startActivity(intent);

    }

    private void openTwitter() {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/srianshsankhi7?s=08"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/srianshsankhi7?s=08"));
        }
        this.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}