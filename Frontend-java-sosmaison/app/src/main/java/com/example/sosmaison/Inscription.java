package com.example.sosmaison;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
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

public class Inscription extends AppCompatActivity {
    private ImageView img_retourner,ic_hide;
    private EditText textprenom,textnom,textInputemail,textInputphone,textInputpassword,textInputconfirmation;
    private Button btn_inscription;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        getSupportActionBar().hide();

        //

        img_retourner = (ImageView) findViewById(R.id.image_retourner);
        img_retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInscription();
            }

        });

        //

        textnom = findViewById(R.id.editTextTextnom);
        textprenom = findViewById(R.id.editTextprenom);
        textInputemail = findViewById(R.id.editTextTextEmailAddress);
        textInputphone = findViewById(R.id.editTextPhone);
        textInputpassword = findViewById(R.id.editTextTextPassword);


        //


        btn_inscription = (Button)  findViewById(R.id.btn_inscription);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nom = textnom.getText().toString().trim();
                final String prenom = textprenom.getText().toString().trim();
                final String email = textInputemail.getText().toString().trim();
                final String phone = textInputphone.getText().toString().trim();
                final String password =textInputpassword.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(Inscription.this);
                progressDialog.setMessage("Loading...");

                if (nom.isEmpty()) {
                    Toast.makeText(Inscription.this, "saisissez votre nom", Toast.LENGTH_SHORT).show();
                }
                else  if (prenom.isEmpty()) {
                    Toast.makeText(Inscription.this, "saisissez votre prénom", Toast.LENGTH_SHORT).show();
                }
                else  if (email.isEmpty()) {
                            Toast.makeText(Inscription.this, "saisissez votre adresse email", Toast.LENGTH_SHORT).show();
                        }
                        else   if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(Inscription.this, "saisissez votre addresse email correctement", Toast.LENGTH_SHORT).show();

                        }
                        else if (phone.isEmpty())
                        {
                        Toast.makeText(Inscription.this, "saisissez votre numéro de téléphone", Toast.LENGTH_SHORT).show();

                         }
                        else if (phone.length() != 8)
                        {

                        Toast.makeText(Inscription.this, "saisissez votre numéro de téléphone correctement", Toast.LENGTH_SHORT).show();
                            textInputphone.setText("");

                        }
                        else  if (password.isEmpty()) {

                            Toast.makeText(Inscription.this, "saissiez votre  le mot de passe", Toast.LENGTH_SHORT).show();
                        }
                        else if (password.length() < 6 ) {

                            Toast.makeText(Inscription.this, "le mot de passe est court", Toast.LENGTH_SHORT).show();

                        }

                        else if ( !nom.equals("") && !prenom.equals("") && !email.equals("") && !phone.equals("") && !password.equals("")) {

                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.18/sosmaison/Nouveau dossier/signup.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equals("succès de l'inscription")){

                                Toast.makeText(Inscription.this,"... Veuillez vérifier votre adresse email ..! ", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();



                            }  else {
                                Toast.makeText(Inscription.this,response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();


                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Inscription.this,error.getMessage(), Toast.LENGTH_SHORT).show();



                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("prenom",prenom);
                            params.put("nom",nom);
                            params.put("email",email);
                            params.put("phone",phone);
                            params.put("password",password);



                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Inscription.this);
                    requestQueue.add(request);


                }

                        else  {
                            Toast.makeText(getApplicationContext(), "All filed required", Toast.LENGTH_SHORT).show();
                }

                    }

                });

            //

        ic_hide = (ImageView)findViewById(R.id.ic_hide_confirmation);
        ic_hide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textInputpassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        textInputconfirmation.setInputType(InputType.TYPE_CLASS_TEXT);

                        break;
                    case MotionEvent.ACTION_UP:
                        textInputpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        textInputconfirmation.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        break;
                }
                return true;
            }
        });






    }



        public void openInscription() {
            super.onBackPressed();

        }
    public void openMainActvity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}