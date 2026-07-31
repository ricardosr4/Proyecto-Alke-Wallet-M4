package com.example.proyectoalkewallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btn_send).setOnClickListener(view -> {
            startActivity(new Intent(this, SendMoney.class));
        });

        findViewById(R.id.btn_receive).setOnClickListener(view -> {
            startActivity(new Intent(this, RequestMoney.class));
        });

        findViewById(R.id.iv_avatar).setOnClickListener(view -> {
            startActivity(new Intent(this, Profile.class));
        });

    }
}
