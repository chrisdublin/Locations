package org.unical.locations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import org.unical.locations.model.User;

public class RegistrationActivity extends AppCompatActivity {

    Button registerBtn;
    EditText nameTxt;
    EditText surnameTxt;
    EditText username;
    EditText password1Txt;
    TextView errorMessage;

    RequestQueue requestQueue;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        TextView login = findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginSuccessful.class);
                startActivity(intent);
            }
        });

        nameTxt = findViewById(R.id.txtName);
        surnameTxt = findViewById(R.id.txtSurname);
        registerBtn = findViewById(R.id.btnRegister);
        password1Txt = findViewById(R.id.txtPwd1);
        username = findViewById(R.id.txtUserName);
        errorMessage = findViewById(R.id.errorTxt);
        requestQueue = Volley.newRequestQueue(this);
    }

    private Response.Listener<JSONObject> buildListener(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);

            }
        };
    }

    private Response.ErrorListener buildErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMessage.setText("Username already used!");
                Log.e("Volley", error.toString());
            }
        };
    }

    private void register() {
        this.url = GlobalConstants.baseURL + "insertUser";
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

    private JSONObject createPayload()  {
        JSONObject jsonObject = null;
        try {
            User user = new User(nameTxt.getText().toString(), surnameTxt.getText().toString(), username.getText().toString(),password1Txt.getText().toString());
            Gson gson = new Gson();
            String json = gson.toJson(user);
            jsonObject = new JSONObject(json);
        }
        catch (Exception e) {
            Log.e("Volley","error while creating the json");
        }
        return jsonObject;
    }

    public void registerClicked(View v) {
        register();
    }
}