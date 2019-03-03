package org.unical.locations;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import org.unical.locations.model.DataObjectList;
import org.unical.locations.model.DataObject;

import java.util.List;

public class LoginSuccessful extends AppCompatActivity {

    private Spinner countrySpinner;
    private Spinner citySpinner;
    private Spinner addressSpinner;
    private Spinner reviewSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        Button logout = findViewById(R.id.logoutBtn);
        logout.setMovementMethod(LinkMovementMethod.getInstance());
        countrySpinner = findViewById(R.id.countryspinner);
        citySpinner = findViewById(R.id.cityspinner);
        addressSpinner = findViewById(R.id.addressspinner);
        reviewSpinner = findViewById(R.id.reviewspinner);
        setCountrySpinnerListener();
        setCitySpinnerListener();
        setAddressSpinnerListener();
        requestData(countrySpinner,GlobalConstants.baseURL + "getCountries");
    }

    public void logoutClicked(View v) {
        Intent intent = new Intent(LoginSuccessful.this, MainActivity.class);
        startActivity(intent);
    }

    private void setCountrySpinnerListener(){
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String url = GlobalConstants.baseURL + "getCitiesByCountryName?countryName=" + ((DataObject)countrySpinner.getSelectedItem()).getName();
                requestData(citySpinner,url);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCitySpinnerListener(){
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String url = GlobalConstants.baseURL + "getAddressByCityName?cityName=" + ((DataObject)citySpinner.getSelectedItem()).getName();
                requestData(addressSpinner,url);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAddressSpinnerListener(){
        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String url = GlobalConstants.baseURL + "getReviewsByAddressId?addressId=" + ((DataObject)addressSpinner.getSelectedItem()).getId();
                requestData(reviewSpinner,url);
                setReviewSpinnerListener();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setReviewSpinnerListener(){
        reviewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginSuccessful.this).create();
                alertDialog.setTitle(((DataObject)reviewSpinner.getSelectedItem()).getName());
                alertDialog.setMessage(((DataObject)reviewSpinner.getSelectedItem()).getReviewContent());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void requestData(final Spinner spinner, String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                DataObjectList data = gson.fromJson(response, DataObjectList.class);
                List<DataObject> spinnerData = data.getElements();
                if(null != spinnerData){
                    assert spinner != null;
                    spinner.setVisibility(View.VISIBLE);
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(LoginSuccessful.this, spinnerData);
                    spinner.setAdapter(spinnerAdapter);
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
