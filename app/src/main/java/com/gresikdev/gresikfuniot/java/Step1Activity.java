package com.gresikdev.gresikfuniot.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gresikdev.gresikfuniot.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Step1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        ButterKnife.bind(this);
    }

    private void executeColor(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                },
                error -> {
                    // ignore
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @OnClick(R.id.btnSubmitRed)
    void submitRed() {
        executeColor("http://10.37.11.235/genericArgs?R=255&G=0&B=0");
    }

    @OnClick(R.id.btnSubmitGreen)
    void submitGreen() {
        executeColor("http://10.37.11.235/genericArgs?R=0&G=255&B=0");
    }

    @OnClick(R.id.btnSubmitBlue)
    void submitBlue() {
        executeColor("http://10.37.11.235/genericArgs?R=0&G=0&B=255");
    }

    @OnClick(R.id.btnSubmitBlack)
    void submitBlack() {
        executeColor("http://10.37.11.235/genericArgs?R=0&G=0&B=0");
    }
}
