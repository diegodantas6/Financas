package br.com.diegodantas.financas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button btnLancamento;
    Button btnResumo;
    Button btnConfiguracao;
    Button btnCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewToClass();

        addEvents();

    }

    private void addEvents() {
        btnLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLancamento();

            }
        });

        btnResumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onResumo();

            }
        });

        btnConfiguracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onConfiguracao();

            }
        });

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCliente();

            }
        });

    }

    private void viewToClass() {
        btnLancamento = (Button) findViewById(R.id.btnLancamento);
        btnResumo = (Button) findViewById(R.id.btnResumo);
        btnConfiguracao = (Button) findViewById(R.id.btnConfiguracao);
        btnCliente = (Button) findViewById(R.id.btnCliente);
    }

    private void onLancamento() {

        Toast.makeText(MenuActivity.this, "onLancamento", Toast.LENGTH_SHORT).show();
        
    }

    private void onResumo() {

        Toast.makeText(MenuActivity.this, "onResumo", Toast.LENGTH_SHORT).show();

    }

    private void onConfiguracao() {

        Toast.makeText(MenuActivity.this, "onConfiguracao", Toast.LENGTH_SHORT).show();

    }

    private void onCliente() {

        startActivity(new Intent(this, ListClienteActivity.class));
        finish();

    }
}
