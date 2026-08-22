package com.example.proyectoalkewallet.view.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoalkewallet.R;
import com.example.proyectoalkewallet.controller.AccountController;
import com.google.android.material.textfield.TextInputEditText;

public class SendMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_money);

        AccountController accountController = new AccountController();
        TextInputEditText amountInput = findViewById(R.id.et_amount);

        findViewById(R.id.btn_send_money).setOnClickListener(view -> {
            String amountText = amountInput.getText() == null
                    ? ""
                    : amountInput.getText().toString();

            if (accountController.withdraw(amountText)) {
                Toast.makeText(this, R.string.retiro_exitoso, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, R.string.saldo_insuficiente, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(view -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        });
    }
}
