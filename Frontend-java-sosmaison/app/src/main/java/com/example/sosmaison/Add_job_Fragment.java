package com.example.sosmaison;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_job_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_job_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_job_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_job_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_job_Fragment newInstance(String param1, String param2) {
        Add_job_Fragment fragment = new Add_job_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Button btn;

    }
    Spinner travail,competences;
    EditText annec;
    Spinner diplom;
    EditText typecours;
    EditText anneobtenion;
    EditText description;
    EditText desc_service;
    EditText prix;
    Switch localiser;
    String stravail;
    String scompetences;
    String sannec;
    String sdiplom;
    String stypecours;
    String sanneobtention;
    String sdesc;
    String sprix;
    String sdesc_service;
    Context context;
    public static String lat,lg;
    static Location location;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root= inflater.inflate(R.layout.fragment_add_job_, container, false);
        Button publie=root.findViewById(R.id.Publie);
        travail=root.findViewById(R.id.spinnerTravail);
        competences=root.findViewById(R.id.spinner4);
        annec=root.findViewById(R.id.anneec);
        diplom=root.findViewById(R.id.spinner5);
        typecours=root.findViewById(R.id.typecours);
        anneobtenion=root.findViewById(R.id.anneobtenion);
        description=root.findViewById(R.id.description);
        prix=root.findViewById(R.id.prix);
        localiser=root.findViewById(R.id.switch1);
        desc_service=root.findViewById(R.id.desc_service);
        context=this.getContext();
        localiser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Intent intent=new Intent(context,MapsActivity.class);
                    startActivity(intent);
                }
            }
        });
        publie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 stravail=travail.getSelectedItem().toString();
                 scompetences=competences.getSelectedItem().toString();
                 sannec=annec.getText().toString();
                 sdiplom=diplom.getSelectedItem().toString();
                 stypecours=typecours.getText().toString();
                 sanneobtention=anneobtenion.getText().toString();
                 sdesc=description.getText().toString();
                 sprix=prix.getText().toString();
                 sdesc_service=desc_service.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.12/sosmaison/Nouveau dossier/gestion_job.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LOG_VOLLEY", response);
                        Toast.makeText(getContext(), "Artisan ajouté avec succès",
                                Toast.LENGTH_LONG).show();

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LOG_VOLLEY", error.toString());
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("stravail", stravail);
                        params.put("scompetences", scompetences);
                        params.put("sannec", sannec);
                        params.put("sdiplom", sdiplom);
                        params.put("stypecours", stypecours);
                        params.put("sanneobtention", sanneobtention);
                        params.put("sdesc", sdesc);
                        params.put("sprix", sprix);
                        params.put("sdesc_service", sdesc_service);

                        params.put("lat",lat);
                        params.put("long",lg);


                        return params;
                    }



                };
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                requestQueue.add(stringRequest);
                stringRequest.setShouldCache(false);
                //VollySupport.getmInstance(RegisterActivity.this).addToRequestque(stringRequest);
                System.out.println();

            }
        });
        return root;
    }
}