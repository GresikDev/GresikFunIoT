package com.gresikdev.gresikfuniot.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gresikdev.gresikfuniot.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Step4Activity extends AppCompatActivity {

    @BindView(R.id.etValRed)
    EditText mEtValRed;
    @BindView(R.id.etValGreen)
    EditText mEtValGreen;
    @BindView(R.id.etValBlue)
    EditText mEtValBlue;

    private int red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);

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

    @OnClick(R.id.btnCheckColor)
    void checkColor(Button button) {
        red = new Random().nextInt(256);
        green = new Random().nextInt(256);
        blue = new Random().nextInt(256);

        mEtValRed.setText(red + "");
        mEtValGreen.setText(green + "");
        mEtValBlue.setText(blue + "");

        int color = Color.argb(255, red, green, blue);
        button.setBackgroundColor(color);
    }

    @OnClick(R.id.btnSubmitColor)
    void submitColor() {
        String url = "http://10.37.11.235/genericArgs?R=" + red + "&G=" + green + "&B=" + blue;
        executeColor(url);
    }
}
