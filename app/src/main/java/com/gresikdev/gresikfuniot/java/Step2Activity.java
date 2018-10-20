package com.gresikdev.gresikfuniot.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gresikdev.gresikfuniot.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Step2Activity extends AppCompatActivity {

    @BindView(R.id.etValRed)
    EditText mEtValRed;
    @BindView(R.id.etValGreen)
    EditText mEtValGreen;
    @BindView(R.id.etValBlue)
    EditText mEtValBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

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

    @OnClick(R.id.btnSubmitColor)
    void submitColor() {
        String red = mEtValRed.getText().toString();
        String green = mEtValGreen.getText().toString();
        String blue = mEtValBlue.getText().toString();

        String url = "http://10.37.11.235/genericArgs?R=" + red + "&G=" + green + "&B=" + blue;

        executeColor(url);
    }

    @OnClick(R.id.btnCheckColor)
    void checkColor(Button button) {
        String red = mEtValRed.getText().toString();
        String green = mEtValGreen.getText().toString();
        String blue = mEtValBlue.getText().toString();

        int color = Color.argb(255, convertToInt(red), convertToInt(green), convertToInt(blue));
        button.setBackgroundColor(color);
    }

    private int convertToInt(String s) {
        if (s == null || s.isEmpty()) return 0;
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
