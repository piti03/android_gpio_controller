package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityBoardBinding;

public class Board extends AppCompatActivity {
    private ActivityBoardBinding binding;
    // define the strings...
    String firstLed ="0";
    String secondLed = "0";
    String thirdLed = "0";
    String forthLed = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);

     binding.firstLED.setOnCheckedChangeListener((buttonView, isChecked) -> {
       firstLed = (isChecked)? "1":"0";
     });
     binding.secondLED.setOnCheckedChangeListener((buttonView, isChecked) -> {
        secondLed = (isChecked)? "1":"0";
     });
     binding.thirdLED.setOnCheckedChangeListener((buttonView, isChecked) -> {
        thirdLed = (isChecked)? "1" : "0";
     });
     binding.forthLED.setOnCheckedChangeListener((buttonView, isChecked) -> {
       forthLed = (isChecked)? "1" : "0";
     });

        // Communicating with Network using Volley
        binding.myfab.setOnClickListener(v -> {

            String BOARD_URL = String.format("script_address?firstLED=%s&secondLED=%s&thirdLED=%s&forthLED=%s",firstLed,secondLed,thirdLed,forthLed);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, BOARD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.contains("Record got ")){
                        Toast.makeText(Board.this, "LEDs got updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Board.this, "a conflict during the process", Toast.LENGTH_SHORT).show();
                    }

                }
            }, error -> {
                Toast.makeText(this, "no a success in connection", Toast.LENGTH_SHORT).show();

            });
            requestQueue.add(stringRequest);


        });















        setContentView(view);
    }
}
