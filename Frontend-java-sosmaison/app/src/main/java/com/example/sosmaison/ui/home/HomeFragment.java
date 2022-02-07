package com.example.sosmaison.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sosmaison.ListingActivity;
import com.example.sosmaison.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LinearLayout jardinage,bricolage,electricite,mecanique,climatisation,
    plomberie,multimedia,gardiennage,remorquage,cours,animaux,
    photographe;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        jardinage=root.findViewById(R.id.jardinage);
        bricolage=root.findViewById(R.id.bricolage);
        electricite=root.findViewById(R.id.elictricite);
        mecanique=root.findViewById(R.id.mecanique);
        climatisation=root.findViewById(R.id.climatisation);
        plomberie=root.findViewById(R.id.plomberie);
        multimedia=root.findViewById(R.id.multimedia);
        gardiennage=root.findViewById(R.id.gardiennage);
        remorquage=root.findViewById(R.id.remorquage);
        cours=root.findViewById(R.id.cours);
        animaux=root.findViewById(R.id.animaux);
        jardinage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Jardinage");
                v.getContext().startActivity(intent);
            }
        });
        bricolage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Bricolage");
                v.getContext().startActivity(intent);
            }
        });
        electricite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Electricite");
                v.getContext().startActivity(intent);
            }
        });
        mecanique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Mecanique");
                v.getContext().startActivity(intent);
            }
        });
        climatisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Climatisation");
                v.getContext().startActivity(intent);
            }
        });
        plomberie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Plomberie");
                v.getContext().startActivity(intent);
            }
        });
        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Multimedia");
                v.getContext().startActivity(intent);
            }
        });
        gardiennage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Gardiennage");
                v.getContext().startActivity(intent);
            }
        });
        remorquage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Remorquage");
                v.getContext().startActivity(intent);
            }
        });
        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Cours");
                v.getContext().startActivity(intent);
            }
        });
        animaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ListingActivity.class);
                intent.putExtra("type","Animaux");
                v.getContext().startActivity(intent);
            }
        });
        return root;
    }
}