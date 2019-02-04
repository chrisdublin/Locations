package org.unical.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText userNameTxt;
    EditText passwordTxt;
    TextView errorMessage;


    RequestQueue requestQueue;


    String baseUrl = "http://10.0.2.2:9080/locationreview/api/services/";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register = findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        userNameTxt = findViewById(R.id.txtUserName);
        passwordTxt = findViewById(R.id.txtPwd);
        loginBtn = findViewById(R.id.btnLogin);
        errorMessage = findViewById(R.id.errorTxt);
        requestQueue = Volley.newRequestQueue(this);

    }

    private void login(String username, String password) {
        this.url = this.baseUrl + "login?userName=" + username + "&password=" + password;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("Found")){
                                Intent intent = new Intent(MainActivity.this, LoginSuccessful.class);
                                startActivity(intent);
//                                errorMessage.setText("Login Successful");
                            }
                            else
                            {
                                errorMessage.setText("Incorrect UserName or Password");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorMessage.setText("error while calling the service");
                        Log.e("Volley", error.toString());
                    }
                }
        );
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

    public void loginClicked(View v) {
        login(userNameTxt.getText().toString(), passwordTxt.getText().toString());
    }
}