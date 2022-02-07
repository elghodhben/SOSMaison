package com.example.sosmaison;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.sosmaison.MapsActivity.MY_PERMISSIONS_REQUEST_LOCATION;

public class Details extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private MapView mapView;
    private GoogleMap map;
    GoogleApiClient mGoogleApiClient;
    public static Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private GoogleMap mMap;
    public LatLng latLng;
    TextView tdistance,tnature_travail,tniveau_competence,tannes_comepetence,tnature_diplome,ttype_cours,tannee_obtention,tdetail_service,tprix_jours,tdescription_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Bundle extras=getIntent().getExtras();
        tnature_travail=findViewById(R.id.travail);
        tniveau_competence=findViewById(R.id.niveau_competence);
        tannee_obtention=findViewById(R.id.anneobtenion);
        tannes_comepetence=findViewById(R.id.annees_competence);
        tnature_diplome=findViewById(R.id.nature_diplome);
        ttype_cours=findViewById(R.id.typecours);
        tdetail_service=findViewById(R.id.detail_service);
        tprix_jours=findViewById(R.id.prix);
        tdescription_service=findViewById(R.id.desc_service);
        tdistance=findViewById(R.id.distance);

        String nature_travail =  extras.getString("nature_travail");
        String niveau_competence= extras.getString("niveau_competence");
        String annes_comepetence=extras.getString("annees_competence");
        String nature_diplome=extras.getString("nature_diplome");
        String type_cours= extras.getString("type_cours");
        String annee_obtention= extras.getString("annee_obtention");
        String detail_service = extras.getString("detail_service");
        String prix_jours=  extras.getString("prix_jours");
        String description_service = extras.getString("description_service");
        String latitude=   extras.getString("latitude");
        String longitude= extras.getString("longitude");
        String distance=extras.getString("distance");
        tnature_diplome.setText(nature_diplome);
        System.out.println("NATURE  : "+ nature_diplome);

        tniveau_competence.setText(niveau_competence);
        tannes_comepetence.setText(annes_comepetence);
        tnature_travail.setText(nature_travail);
        ttype_cours.setText(type_cours);
        tdetail_service.setText(detail_service);
        tprix_jours.setText(prix_jours+"/DT");
        tannee_obtention.setText(annee_obtention);
        tdescription_service.setText(description_service);
        latLng=new LatLng(Double.valueOf(latitude),Double.valueOf(longitude));
        tdistance.setText(distance);
        mapView = (MapView) findViewById(R.id.linclude).findViewById(R.id.map);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

                System.out.println("NEW LAT LONG"+ latLng.latitude+" "+ latLng.longitude);
                Add_job_Fragment.lat=String.valueOf(latLng.latitude);
                Add_job_Fragment.lg=String.valueOf(latLng.longitude);
                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);

            }

        //Initialize Google Play Services


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}