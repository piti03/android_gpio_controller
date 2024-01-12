package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // define viewBinding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        // Define some strings
        // set the bottom off
        binding.button.setOnClickListener(v -> {
            String username = Objects.requireNonNull(binding.request1.getText()).toString();
            String password = Objects.requireNonNull(binding.request2.getText()).toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill the parameters! first", Toast.LENGTH_SHORT).show();
            }else {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("process to login...");
                progressDialog.show();
                // you must write your codes here....
                @SuppressLint("DefaultLocale") String URL = String.format("script_address?username=%s&password=%s",username,password);
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                        response -> {
                            if(response.contains("confirm the process")){
                                Intent intent = new Intent(MainActivity.this, Board.class);
                                startActivity(intent);
                            }else{
                                if(response.contains("no")){
                                    Toast.makeText(this, "username or password is incorrect, please try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                            progressDialog.dismiss();
                        }, error -> {
                    Toast.makeText(this,"something went wrong, no connection to destination", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                });
                requestQueue.add(stringRequest);
            }
        });

        setContentView(view);
    }

}
