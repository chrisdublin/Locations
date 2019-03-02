package org.unical.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.text.method.LinkMovementMethod;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.unical.locations.adapter.SpinnerAdapter;
import org.unical.locations.constants.GlobalConstants;
import org.unical.locations.model.Countries;
import org.unical.locations.model.CountryDataObject;

import java.util.Arrays;
import java.util.List;

public class LoginSuccessful extends AppCompatActivity {

    private Spinner countrySpinner;
    protected List<CountryDataObject> spinnerData;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        Button logout = findViewById(R.id.logoutBtn);
        logout.setMovementMethod(LinkMovementMethod.getInstance());
        requestJsonObject();
    }

    public void logoutClicked(View v) {
        Intent intent = new Intent(LoginSuccessful.this, MainActivity.class);
        startActivity(intent);
    }

    private void requestJsonObject(){
        this.url = GlobalConstants.baseURL + "getCountries";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Countries countries = gson.fromJson(response, Countries.class);
                spinnerData = countries.getCountries();
                //display first question to the user
                if(null != spinnerData){
                    countrySpinner = findViewById(R.id.countryspinner1);
                    assert countrySpinner != null;
                    countrySpinner.setVisibility(View.VISIBLE);
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(LoginSuccessful.this, spinnerData);
                    countrySpinner.setAdapter(spinnerAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

}
