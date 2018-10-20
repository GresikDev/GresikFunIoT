package com.gresikdev.gresikfuniot.java;

import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gresikdev.gresikfuniot.R;
import com.squareup.seismic.ShakeDetector;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OptionalActivity extends AppCompatActivity {

    @BindView(R.id.etValRed)
    EditText mEtValRed;
    @BindView(R.id.etValGreen)
    EditText mEtValGreen;
    @BindView(R.id.etValBlue)
    EditText mEtValBlue;

    @BindView(R.id.etIPAdresss)
    EditText mEtIPAddress;

    @BindView(R.id.btnCheckColor)
    Button mBtnCheckColor;

    private SensorManager mSensorManager;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional);

        ButterKnife.bind(this);

        doChangeColor();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mShakeDetector = new ShakeDetector(this::doChangeColor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mShakeDetector != null && mSensorManager != null) mShakeDetector.start(mSensorManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mShakeDetector != null) mShakeDetector.stop();
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

    void doChangeColor() {
        int red = new Random().nextInt(256);
        int green = new Random().nextInt(256);
        int blue = new Random().nextInt(256);

        mEtValRed.setText(red + "");
        mEtValGreen.setText(green + "");
        mEtValBlue.setText(blue + "");

        int color = Color.argb(255, red, green, blue);
        mBtnCheckColor.setBackgroundColor(color);

        String ip = mEtIPAddress.getText().toString();
        String url = "http://" + ip + "/genericArgs?R=" + red + "&G=" + green + "&B=" + blue;
        executeColor(url);
    }

    @OnClick(R.id.btnCheckColor)
    void checkColor() {
        doChangeColor();
    }
}
