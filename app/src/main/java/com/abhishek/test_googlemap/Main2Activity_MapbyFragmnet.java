package com.abhishek.test_googlemap;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Toast;

public class Main2Activity_MapbyFragmnet extends AppCompatActivity implements OnMapReadyCallback {

    private boolean mlocationpermissiongranted;

    private static final int Requestcode=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__mapby_fragmnet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initgooglemap();
        SupportMapFragment supportMapFragment=SupportMapFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_map,supportMapFragment)
                .commit();

        supportMapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
    private void initgooglemap() {
        if(iserviceok()){
            if(checkselfpermission()){


                Toast.makeText(this,"Ready to map",Toast.LENGTH_LONG).show();
            }else {

                requewtpermission();
            }


        }

    }

    private boolean checkselfpermission() {

        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private Boolean iserviceok() {

        GoogleApiAvailability googleApiAvailability=GoogleApiAvailability.getInstance();

        int result=googleApiAvailability.isGooglePlayServicesAvailable(this);

        if(result== ConnectionResult.SUCCESS){


            return true;
        }else if(googleApiAvailability.isUserResolvableError(result)){

            Dialog dialog=googleApiAvailability.getErrorDialog(this,result,1004, task->
                    Toast.makeText(this,"Dialog is cancelled by user",Toast.LENGTH_LONG).show());

            dialog.show();
        }else {
            Toast.makeText(this,"Play Service are required by the Application",Toast.LENGTH_LONG).show();

        }


        return false;
    }

    private void requewtpermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },Requestcode);
            }

        }}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== Requestcode && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            mlocationpermissiongranted=true;


            Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Permission not Granted",Toast.LENGTH_LONG).show();

        }


    }


}
