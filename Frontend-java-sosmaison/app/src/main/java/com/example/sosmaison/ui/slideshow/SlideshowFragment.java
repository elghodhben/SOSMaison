package com.example.sosmaison.ui.slideshow;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sosmaison.Artisan;
import com.example.sosmaison.R;
import com.example.sosmaison.Recycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SlideshowFragment extends Fragment implements LocationListener {
    public RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private SlideshowViewModel slideshowViewModel;
    ArrayList<Artisan> artisans = new ArrayList<Artisan>();
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        requestQueue = Volley.newRequestQueue(getContext());

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_prods);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        jsonParse();
        return root;
    }

    private void jsonParse() {
        String url = "http://192.168.1.12/sosmaison/Nouveau dossier/get_jobs.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            /*
                        {
            "nature_travail": "",
            "niveau_competence": "",
            "annees_competence": "0",
            "nature_diplome": "",
            "type_cours": "",
            "annee_obtention": "0000",
            "detail_service": "",
            "prix_jours": "0",
            "description_service": "",
            "latitude": "0",
            "longitude": "0"
            },   */@Override
            public void onResponse(JSONArray response) {
                try {

                    org.json.JSONArray arr=response;
                    for (int i = 0; i < arr.length(); i++) {
                        Artisan artisan=new Artisan();

                        JSONObject art = arr.getJSONObject(i);
                        artisan.setAnneeObtention( art.getString("annee_obtention"));
                        artisan.setNatureTravail( art.getString("nature_travail"));
                        artisan.setNatureDiplome(art.getString("nature_diplome"));
                        artisan.setNiveauCompetence( art.getString("niveau_competence"));
                        artisan.setAnneesCompetence(art.getString("annees_competence"));
                        artisan.setTypeCours(art.getString("type_cours"));
                        artisan.setDetailService(art.getString("detail_service"));
                        artisan.setPrixJours(art.getString("prix_jours"));
                        artisan.setDescriptionService(art.getString("description_service"));
                        artisan.setLatitude(art.getString("latitude"));
                        artisan.setLongitude(art.getString("longitude"));
                        artisans.add(artisan);


                    }

                initializeRecyclerView();
                called=false;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    public void initializeRecyclerView()
    {
        Recycler adapter = new Recycler(artisans, null, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
    boolean called=false;
    @Override
    public void onLocationChanged(Location location) {
        System.out.println("LOCATION CHANGED"+called);
        if (called==false && location!=null)

        {Recycler adapter = new Recycler(artisans,location,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);}
        called=true;
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
