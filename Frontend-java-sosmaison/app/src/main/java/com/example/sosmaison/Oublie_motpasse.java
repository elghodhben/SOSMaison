package com.example.sosmaison;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Oublie_motpasse extends AppCompatActivity {
    private ImageView img_retourner;
    private Button btn_envoyer ;
    private EditText textInputEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oublie_motpasse);
        getSupportActionBar().hide();

        textInputEmail =(EditText)findViewById(R.id.editTextTextEmailAddress) ;

        btn_envoyer = (Button)findViewById(R.id.btn_envoyer);
        btn_envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String email = textInputEmail.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(Oublie_motpasse.this);
                progressDialog.setMessage("Loading...");
                if (email.isEmpty()) {
                    Toast.makeText(Oublie_motpasse.this, "saisissez votre adresse email", Toast.LENGTH_SHORT).show();
                }
                else   if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Oublie_motpasse.this, "valide votre adresse email", Toast.LENGTH_SHORT).show();

                }

                else if ( !email.equals("")) {


                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.18/sosmaison/Nouveau dossier/mot_passse_oublier.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equals("Veuillez vérifier votre adresse email ..!")){

                                Toast.makeText(Oublie_motpasse.this,  response , Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                MainActivityto();


                            }  else {
                                Toast.makeText(Oublie_motpasse.this,response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();





                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Oublie_motpasse.this,error.getMessage(), Toast.LENGTH_SHORT).show();



                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();


                            params.put("email",email);




                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Oublie_motpasse.this);
                    requestQueue.add(request);


                }

            }
        });

        img_retourner = (ImageView) findViewById(R.id.image_retourner);
        img_retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackHome();
            }

        });
    }
    public void gobackHome() {
        super.onBackPressed();

    }
    public void MainActivityto() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }

}
