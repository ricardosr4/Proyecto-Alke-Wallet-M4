package com.example.proyectoalkewallet.view.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoalkewallet.R;
import com.example.proyectoalkewallet.controller.AccountController;
import com.google.android.material.textfield.TextInputEditText;

public class RequestMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_money);

        AccountController accountController = new AccountController();
        TextInputEditText amountInput = findViewById(R.id.et_amount);

        findViewById(R.id.btn_request_money).setOnClickListener(view -> {
            String amountText = amountInput.getText() == null
                    ? ""
                    : amountInput.getText().toString();

            if (accountController.deposit(amountText)) {
                Toast.makeText(this, R.string.deposito_exitoso, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, R.string.monto_invalido, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(view -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        });
    }
}
