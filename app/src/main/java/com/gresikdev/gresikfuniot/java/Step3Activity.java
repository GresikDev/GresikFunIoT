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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Step3Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.etValRed)
    EditText mEtValRed;
    @BindView(R.id.etValGreen)
    EditText mEtValGreen;
    @BindView(R.id.etValBlue)
    EditText mEtValBlue;

    @BindView(R.id.btnCheckColor)
    Button mBtnCheckColor;

    @BindView(R.id.seekBarRed)
    SeekBar mSeekbarRed;
    @BindView(R.id.seekBarGreen)
    SeekBar mSeekbarGreen;
    @BindView(R.id.seekBarBlue)
    SeekBar mSeekbarBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        ButterKnife.bind(this);

        mEtValRed.setText(mSeekbarRed.getProgress() + "");
        mEtValGreen.setText(mSeekbarGreen.getProgress() + "");
        mEtValBlue.setText(mSeekbarBlue.getProgress() + "");
        checkColor();

        mSeekbarRed.setOnSeekBarChangeListener(this);
        mSeekbarGreen.setOnSeekBarChangeListener(this);
        mSeekbarBlue.setOnSeekBarChangeListener(this);
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
        int red = mSeekbarRed.getProgress();
        int green = mSeekbarGreen.getProgress();
        int blue = mSeekbarBlue.getProgress();

        String url = "http://10.37.11.235/genericArgs?R=" + red + "&G=" + green + "&B=" + blue;

        executeColor(url);
    }

    private void checkColor() {
        int red = mSeekbarRed.getProgress();
        int green = mSeekbarGreen.getProgress();
        int blue = mSeekbarBlue.getProgress();

        int color = Color.argb(255, red, green, blue);
        mBtnCheckColor.setBackgroundColor(color);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBarRed) {
            mEtValRed.setText(progress + "");
        } else if (seekBar.getId() == R.id.seekBarGreen) {
            mEtValGreen.setText(progress + "");
        } else if (seekBar.getId() == R.id.seekBarBlue) {
            mEtValBlue.setText(progress + "");
        }
        checkColor();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
