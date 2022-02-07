package com.example.sosmaison;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {
    Activity act;
    Activity mContext;
    ArrayList<Artisan> P;
Location location;
Context context;
    public Recycler(ArrayList<Artisan> P, Location location, Context mContext) {
        this.P = P;
        this.location=location;
        this.context=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artisan p = P.get(position);

       holder.description.setText(p.getDetailService());
        holder.travail.setText(p.getNatureTravail());
        LatLng platlng=new LatLng(Double.valueOf(p.getLatitude()),Double.valueOf(p.getLongitude()));
        if (location!=null){
            String dist=String.valueOf(CalculationByDistance(new LatLng(location.getLatitude(),location.getLongitude()),platlng));
            dist=dist.substring(0,dist.indexOf('.')+4);
            holder.distance.setText(dist+"\nKM");}




        else
            holder.distance.setText("/");


    }

    @Override
    public int getItemCount() {
        return P.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView travail;
            TextView description;
            TextView distance;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            travail = itemView.findViewById(R.id.travail);
            description = itemView.findViewById(R.id.description);
            distance = itemView.findViewById(R.id.distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(itemView.getContext(), Details.class);
                    int pos = getAdapterPosition();
                    Artisan art=P.get(pos);
                    intent.putExtra("nature_travail",art.getNatureTravail());
                    intent.putExtra("niveau_competence",art.getNiveauCompetence());
                    intent.putExtra("annees_competence",art.getAnneesCompetence());
                    intent.putExtra("nature_diplome",art.getNatureDiplome());
                    System.out.println("FROM RECYCLER : nature : "+art.getNatureDiplome());
                    intent.putExtra("type_cours",art.getTypeCours());
                    intent.putExtra("annee_obtention",art.getAnneeObtention());
                    intent.putExtra("detail_service",art.getDetailService());
                    intent.putExtra("prix_jours",art.getPrixJours());
                    intent.putExtra("description_service",art.getDescriptionService());
                    intent.putExtra("latitude",art.getLatitude());
                    intent.putExtra("longitude",art.getLongitude());
                    intent.putExtra("distance",distance.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });
        }

    }
}