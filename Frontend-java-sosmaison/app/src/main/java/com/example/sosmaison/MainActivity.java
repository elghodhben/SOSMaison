package com.example.sosmaison;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity {


    private TextView txt_inscription, txt_oublie;
    private Button btn_connexion;
    private EditText textInputEmail, textInputpassword;
    private ImageView ic_hide;
    private Button btn_facebook;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

// permission connection

        if(!isNetworkAvailable()==true)
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please Check Your Internet Connection")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }
        else if(isNetworkAvailable()==true)
        {
            Toast.makeText(MainActivity.this,
                    "Welcome", Toast.LENGTH_LONG).show();
        }






// input connection

        textInputEmail = findViewById(R.id.editTextTextEmailAddress);
        textInputpassword = findViewById(R.id.editTextTextPassword);


        // hide password

        btn_facebook = findViewById(R.id.facebook);

        ic_hide = (ImageView) findViewById(R.id.ic_hide_confirmation);
        ic_hide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textInputpassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        textInputpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

// move to activity connection

        txt_inscription = (TextView) findViewById(R.id.inscription);
        txt_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInscription();
            }
        });

        //move to activity forget password

        txt_oublie = (TextView) findViewById(R.id.oublie_mot_de_passe);
        txt_oublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOubliepassword();
            }
        });

        // connection

        btn_connexion = (Button) findViewById(R.id.btn_envoyer);
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = textInputEmail.getText().toString().trim();
                final String password = textInputpassword.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "saisissez votre adresse email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, " valide votre adresse email", Toast.LENGTH_SHORT).show();

                } else if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "saissiez votre  le mot de passe", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "le mot de passe est court", Toast.LENGTH_SHORT).show();

                } else if (!email.equals("") && !password.equals("")) {


                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.12/sosmaison/Nouveau dossier/login.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("Connexion réussie")) {

                                Toast.makeText(MainActivity.this, email, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), MenuConnexionActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                System.out.println("erreur login : "+response);
                                progressDialog.dismiss();


                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();


                            params.put("email", email);
                            params.put("password", password);


                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(request);


                } else {
                    Toast.makeText(getApplicationContext(), "All filed required", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    // fuction move to activity Inscription

    public void openInscription() {
        Intent intent = new Intent(this, Inscription.class);
        startActivity(intent);
    }

    //function move to activity forget password

    public void openOubliepassword() {
        Intent intent = new Intent(this, Oublie_motpasse.class);
        startActivity(intent);
    }


    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {


            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                        return true;
                    }
                }
            }
        }


        return false;




    }

}


