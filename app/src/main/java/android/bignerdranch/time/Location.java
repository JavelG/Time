package android.bignerdranch.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Location extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    Button getlocationBtn;
    TextView showLocationTxt;

    LocationManager locationManager;
    String lattitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //Add permission
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        showLocationTxt = findViewById(R.id.show_location);
        getlocationBtn = findViewById(R.id.getLocation);

        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                //Check gpa is enable or not
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    onGPS();
                }
                else
                {
                    //GPS is already on
                    getlocation();
                }

            }
        });


    }
    private void getlocation(){
        //Check Permissions 6.43
        if(ActivityCompat.checkSelfPermission(Location.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && ActivityCompat.checkSelfPermission(Location.this,))
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }


           if(LocationGps !=null)
            {
                double lat=LocationGps.getLattiude();
                double longi=LocationGps.getLongitude();
                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                showLocationTxt.setText("Your Location:" +"\n"+"Lattiude= " + lattitude+"\n"+"Longitude= " + longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLattiude();
                double longi=LocationNetwork.getLongitude();
                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                showLocationTxt.setText("Your Location:" +"\n"+"Lattiude= " + lattitude+"\n"+"Longitude= " + longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLattiude();
                double longi=LocationPassive.getLongitude();
                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                showLocationTxt.setText("Your Location:" +"\n"+"Lattiude= " + lattitude+"\n"+"Longitude= " + longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }


    }
    
    private void onGPS(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}

//     ActivityCompat,requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION)
//HOW TO GET CURRENT LOCATION LATITUDE AND LONGITUDE IN ANDROID STUDIO EXAMPLE