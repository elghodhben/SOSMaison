package com.example.sosmaison;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    ListView listeview;
    ArrayAdapter<String> adapter;
    String[] data = {"Modifier adresse email", "Modifier numéro de téléphone", "Modifier le mots de passe","Déconnecter"};


    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        listeview = (ListView) view.findViewById(R.id.listeview);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        listeview.setAdapter(adapter);
        listeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ){
                    Toast.makeText(getContext(), "email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),Email.class);
                    startActivity(intent);
                }
                if (position == 1 ){
                    Toast.makeText(getContext(), "email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),upgrade_phone.class);
                    startActivity(intent);
                }
                if (position == 2 ){
                    Toast.makeText(getContext(), "email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),upgrade_password.class);
                    startActivity(intent);
                }
                if (position == 3 ){
                    Toast.makeText(getContext(), "déconnexion", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return  view;    }
}