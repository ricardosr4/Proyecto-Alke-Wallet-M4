package com.example.proyectoalkewallet.controller.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoalkewallet.R;
import com.example.proyectoalkewallet.model.Cuenta;
import com.google.android.material.textfield.TextInputEditText;

public class SendMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_money);

        TextInputEditText amountInput = findViewById(R.id.et_amount);

        findViewById(R.id.btn_send_money).setOnClickListener(view -> {
            try {
                String amountText = amountInput.getText() == null
                        ? ""
                        : amountInput.getText().toString();
                double monto = Double.parseDouble(amountText.trim().replace(',', '.'));

                if (Cuenta.retirar(monto)) {
                    Toast.makeText(this, R.string.retiro_exitoso, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, R.string.saldo_insuficiente, Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException exception) {
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
