package com.example.patrick.imagenow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    private EditText mLocationView;
    private EditText mDescView;
    private Button mActivityBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferences loginDetails = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
//        if (loginDetails.getBoolean(Config.LOGGEDIN_SHARED_PREF, false)) {
//            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
//            startActivity(intent);
//        }
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLocationView = (EditText) findViewById(R.id.location);
        mDescView = (EditText) findViewById(R.id.desc);

        mActivityBButton = (Button) findViewById(R.id.activity_report_button);
        mActivityBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptReport();
            }
        });
    }

    private void attemptReport() {
        mLocationView.setError(null);
        mDescView.setError(null);

        String location = mLocationView.getText().toString();
        String description = mDescView.getText().toString();
        String loginEmail = getSharedPreferences(Config.ID_SHARED_PREF, 1).toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(location)) {
            mLocationView.setError(getString(R.string.error_field_required));
            focusView = mLocationView;
            cancel = true;
        } else if (TextUtils.isEmpty(description)) {
            mDescView.setError(getString(R.string.error_field_required));
            focusView = mDescView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            sendReport(location, description, loginEmail);
        }
    }

    private void sendReport(final String location, final String description, final String loginEmail) {
        final ProgressDialog loading;
        loading = ProgressDialog.show(ReportActivity.this, "Sending", null, true, true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ACTIVITY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        loading.dismiss();
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {

                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(ReportActivity.this, "Server Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        loading.dismiss();
                        Toast.makeText(ReportActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_LOCATION, location);
                params.put(Config.KEY_DESC, description);
                params.put(Config.KEY_LOGIN_EMAIL, loginEmail);

                //returning parameter
                return params;
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.report) {
            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(intent);
        } else if (id == R.id.activities) {
            Intent intent = new Intent(getApplicationContext(), ItemsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}