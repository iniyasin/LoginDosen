package com.dummy.logindosen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText nip;
    private EditText password;
    private Button login;
    private ProgressDialog pDialog;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        pDialog = new ProgressDialog(context);
        nip = (EditText)findViewById(R.id.nip);
        password = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        final String NIP = nip.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        pDialog.setMessage("Login Proccess...");
        showDialog();

        //membuat String request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Konfigurasi.LOGIN_SUCCESS)) {
                            hideDialog();
                            gotoDashboard();
                        } else {
                            hideDialog();
                            //menampilkan error message pada toast
                            Toast.makeText(context, "invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //untuk menghandle error
                        hideDialog();
                        Toast.makeText(context, "the server unreacheable", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //mengambil parameter untuk request
                params.put(Konfigurasi.KEY_NIP, NIP);
                params.put(Konfigurasi.KEY_PASS, pass);

                //return parameter
                return params;
            }
        };
        //menambah string request ke antrian
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotoDashboard() {
        Intent intent = new Intent(context, DosenDashboard.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
