package org.unical.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.method.LinkMovementMethod;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.unical.locations.adapter.SpinnerAdapter;
import org.unical.locations.constants.GlobalConstants;
import org.unical.locations.model.City;
import org.unical.locations.model.DataObjectList;
import org.unical.locations.model.DataObject;

import java.util.List;

public class LoginSuccessful extends AppCompatActivity {

    private Spinner countrySpinner;
    private Spinner citySpinner;
    protected List<DataObject> countrySpinnerData;
    protected List<DataObject> citySpinnerData;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        Button logout = findViewById(R.id.logoutBtn);
        logout.setMovementMethod(LinkMovementMethod.getInstance());
        requestCountries();
    }

    public void logoutClicked(View v) {
        Intent intent = new Intent(LoginSuccessful.this, MainActivity.class);
        startActivity(intent);
    }

    private void requestCountries(){
        this.url = GlobalConstants.baseURL + "getCountries";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                DataObjectList countries = gson.fromJson(response, DataObjectList.class);
                countrySpinnerData = countries.getCountries();
                if(null != countrySpinnerData){
                    countrySpinner = findViewById(R.id.countryspinner1);
                    assert countrySpinner != null;
                    countrySpinner.setVisibility(View.VISIBLE);
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(LoginSuccessful.this, countrySpinnerData);
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

    private void requestCities(){
        this.url = GlobalConstants.baseURL + "getCitiesByCountryName?countryName=" + countrySpinner.getSelectedItem().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                City city = gson.fromJson(response, City.class);
//                citySpinnerData = countries.getCountries();
                //display first question to the user
                if(null != citySpinnerData){
                    citySpinner = findViewById(R.id.countryspinner1);
                    assert citySpinner != null;
                    citySpinner.setVisibility(View.VISIBLE);
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(LoginSuccessful.this, citySpinnerData);
                    citySpinner.setAdapter(spinnerAdapter);
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
