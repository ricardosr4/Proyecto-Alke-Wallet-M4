package com.example.proyectoalkewallet.view.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoalkewallet.R;
import com.example.proyectoalkewallet.controller.AccountController;
import com.example.proyectoalkewallet.view.profile.Profile;
import java.util.Locale;

public class Home extends AppCompatActivity {

    private final AccountController accountController = new AccountController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btn_send).setOnClickListener(view -> {
            Intent intent = new Intent(this, SendMoney.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_receive).setOnClickListener(view -> {
            Intent intent = new Intent(this, RequestMoney.class);
            startActivity(intent);
        });

        findViewById(R.id.iv_avatar).setOnClickListener(view -> {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView balance = findViewById(R.id.tv_balance);
        String formattedBalance = String.format(
                Locale.US,
                getString(R.string.formato_saldo),
                accountController.getBalance()
        );
        balance.setText(formattedBalance);
    }
}
