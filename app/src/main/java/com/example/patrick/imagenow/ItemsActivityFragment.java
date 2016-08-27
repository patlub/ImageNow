package com.example.patrick.imagenow;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemsActivityFragment extends Fragment {

    private static final String JSON_URL = "http://192.168.43.134/owc/completed.php";
    private ListView listView;

    public ItemsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        listView = (ListView) view.findViewById(R.id.activity_list_view);
        sendRequest();
        return view;
    }

    private void sendRequest() {
        final ProgressDialog loading;
        loading = ProgressDialog.show(getActivity(), "Fetching", null, true, true);
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getActivity(), "Network error", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJson pj = new ParseJson(json);
        pj.parseJSON();
        ActivityList cl = new ActivityList(getActivity(), ParseJson.ids, ParseJson.names);
        listView.setAdapter(cl);
    }
}
