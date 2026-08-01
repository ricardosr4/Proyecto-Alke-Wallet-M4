package com.example.proyectoalkewallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RequestMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_money);

        findViewById(R.id.iv_back).setOnClickListener(view -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        });

    }
}
