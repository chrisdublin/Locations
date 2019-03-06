package org.unical.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.unical.locations.constants.GlobalConstants;
import org.unical.locations.model.LocationInsertRequest;
import org.unical.locations.model.User;

public class InsertReview extends AppCompatActivity {

    private EditText title;
    private EditText country;
    private EditText city;
    private EditText street;
    private EditText zipCode;
    private EditText review;
    private CheckBox isSafe;
    private Button submitBtn;
    private String url;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_review);

        title = findViewById(R.id.txtReviewTitle);
        country = findViewById(R.id.txtCountry);
        city = findViewById(R.id.txtCity);
        street = findViewById(R.id.txtStreetName);
        zipCode = findViewById(R.id.txtZipCode);
        review = findViewById(R.id.txtReview);
        isSafe = findViewById(R.id.safearea);
        submitBtn = findViewById(R.id.btnSubmit);
        requestQueue = Volley.newRequestQueue(this);
    }

    private Response.Listener<JSONObject> buildListener(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


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

    private void submit() {
        this.url = GlobalConstants.baseURL + "insertReview";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, createPayload(), buildListener(),buildErrorListener());
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

    private boolean isSaveArea(){
        if(isSafe.isChecked())
            return true;
        else
            return false;
    }

    private JSONObject createPayload()  {
        JSONObject jsonObject = null;
        try {
            LocationInsertRequest locationInsertRequest = new LocationInsertRequest(country.getText().toString(), city.getText().toString(), street.getText().toString(), review.getText().toString(), Integer.parseInt(zipCode.getText().toString()),
                    isSaveArea(), title.getText().toString(), Integer.parseInt("1"));
            Gson gson = new Gson();
            String json = gson.toJson(locationInsertRequest);
            jsonObject = new JSONObject(json);
        }
        catch (Exception e) {
            Log.e("Volley","error while creating the json");
        }
        return jsonObject;
    }


    public void submitClicked(View v) {
        submit();
    }
}
