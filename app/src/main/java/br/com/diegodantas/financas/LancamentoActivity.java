package br.com.diegodantas.financas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.diegodantas.dao.LancamentoDao;
import br.com.diegodantas.model.Lancamento;
import br.com.diegodantas.util.Utils;

public class LancamentoActivity extends AppCompatActivity {

    int dia;
    int mes;
    int ano;

    Lancamento lancamento;
    LancamentoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        getParams();

        String data = String.format("%s/%s/%s", Utils.parceZero(dia, 2), Utils.parceZero(mes, 2), ano);

        getSupportActionBar().setTitle(data);

    }

    private void getParams() {

        dia = getIntent().getIntExtra("dia", 0);

        mes = getIntent().getIntExtra("mes", 0);
        mes++;

        ano = getIntent().getIntExtra("ano", 0);

        dao = new LancamentoDao(this.getApplicationContext());

        lancamento = dao.getLancamento(dia, mes, ano);

    }

    @Override
    public void onBackPressed() {

//        if (edtPrecoKg.getText().toString().isEmpty()) {
//            edtPrecoKg.setError("Campo obrigatório!");
//            return;
//        } else if (edtTaxaCredito.getText().toString().isEmpty()) {
//            edtTaxaCredito.setError("Campo obrigatório!");
//            return;
//        } else if (edtTaxaDebito.getText().toString().isEmpty()) {
//            edtTaxaDebito.setError("Campo obrigatório!");
//            return;
//        }

//        salvaDados();

        startActivity(new Intent(this, CalendarActivity.class));
        finish();

    }

}
