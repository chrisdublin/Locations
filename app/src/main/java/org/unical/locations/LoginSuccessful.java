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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.unical.locations.adapter.SpinnerAdapter;
import org.unical.locations.constants.GlobalConstants;
import org.unical.locations.model.CountryDataObject;

import java.util.Arrays;
import java.util.List;

public class LoginSuccessful extends AppCompatActivity {

    private Spinner countrySpinner;
    protected List<CountryDataObject> spinnerData;
    private RequestQueue requestQueue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        Button logout = findViewById(R.id.logoutBtn);
        logout.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void logoutClicked(View v) {
        Intent intent = new Intent(LoginSuccessful.this, MainActivity.class);
        startActivity(intent);
    }

    private Response.Listener<JSONObject> buildListener(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                spinnerData = Arrays.asList(mGson.fromJson(response.toString(), CountryDataObject.class));
                //display first question to the user
                if(null != spinnerData){
                    countrySpinner = (Spinner) findViewById(R.id.countryspinner1);
                    assert countrySpinner != null;
                    countrySpinner.setVisibility(View.VISIBLE);
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(LoginSuccessful.this, spinnerData);
                    countrySpinner.setAdapter(spinnerAdapter);
                }

            }
        };
    }

    private Response.ErrorListener buildErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                errorMessage.setText("Username already used!");
                Log.e("Volley", error.toString());
            }
        };
    }

    private void fetchCountries() {
        this.url = GlobalConstants.baseURL + "getCountries";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  buildListener(),buildErrorListener());
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
