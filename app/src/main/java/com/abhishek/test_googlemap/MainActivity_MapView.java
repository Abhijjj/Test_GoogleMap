package com.abhishek.test_googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.security.Permission;

public class MainActivity_MapView extends AppCompatActivity implements OnMapReadyCallback{

    private boolean mlocationpermissiongranted;

    private static final int Requestcode=101;

    private GoogleMap mgooglemap;

    private MapView mapView;
// four properties in map = traget zoom tilt bearing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView=findViewById(R.id.mapView);


        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync( this);

        initgooglemap();



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    mapView.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    mapView.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    mapView.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
    mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    mapView.onDestroy();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    mapView.onSaveInstanceState(outState);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    mapView.onLowMemory();

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

return ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        ==PackageManager.PERMISSION_GRANTED;
    }

    private Boolean iserviceok() {

        GoogleApiAvailability googleApiAvailability=GoogleApiAvailability.getInstance();

        int result=googleApiAvailability.isGooglePlayServicesAvailable(this);

        if(result== ConnectionResult.SUCCESS){


               return true;
        }else if(googleApiAvailability.isUserResolvableError(result)){

            Dialog dialog=googleApiAvailability.getErrorDialog(this,result,1004,task->
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
