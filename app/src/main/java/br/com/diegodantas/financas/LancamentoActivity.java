package br.com.diegodantas.financas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.diegodantas.dao.LancamentoDao;
import br.com.diegodantas.model.Lancamento;
import br.com.diegodantas.util.Utils;

public class LancamentoActivity extends AppCompatActivity {

    int dia;
    int mes;
    int ano;

    Lancamento lancamento;
    LancamentoDao dao;

    EditText edtQuantidadeCliente;
    EditText edtQuantidadeKg;
    EditText edtPrecoKg;
    EditText edtReceitaOutrosProdutos;
    EditText edtReceitaGeral;

    EditText edtGastoKg;
    EditText edtGastoOutrosProdutos;
    EditText edtGastoOperacional;
    EditText edtGastoGeral;

    EditText edtDinheiro;
    EditText edtDebito;
    EditText edtCredito;
    EditText edtDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        getParams();

        String data = String.format("%s/%s/%s", Utils.parceZero(dia, 2), Utils.parceZero(mes, 2), ano);

        getSupportActionBar().setTitle(data);

        viewToClass();

        objToView();

    }

    private void viewToClass() {
        edtQuantidadeCliente = (EditText) findViewById(R.id.edtQuantidadeCliente);
        edtQuantidadeKg = (EditText) findViewById(R.id.edtQuantidadeKg);
        edtPrecoKg = (EditText) findViewById(R.id.edtPrecoKg);
        edtReceitaOutrosProdutos = (EditText) findViewById(R.id.edtReceitaOutrosProdutos);
        edtReceitaGeral = (EditText) findViewById(R.id.edtReceitaGeral);
        edtReceitaGeral.setFocusable(false);

        edtGastoKg = (EditText) findViewById(R.id.edtGastoKg);
        edtGastoOutrosProdutos = (EditText) findViewById(R.id.edtGastoOutrosProdutos);
        edtGastoOperacional = (EditText) findViewById(R.id.edtGastoOperacional);
        edtGastoGeral = (EditText) findViewById(R.id.edtGastoGeral);
        edtGastoGeral.setFocusable(false);

        edtDinheiro = (EditText) findViewById(R.id.edtDinheiro);
        edtDebito = (EditText) findViewById(R.id.edtDebito);
        edtCredito = (EditText) findViewById(R.id.edtCredito);
        edtDia = (EditText) findViewById(R.id.edtDia);
        edtDia.setFocusable(false);
    }

    private void objToView() {
        edtQuantidadeCliente.setText(Utils.floatToString(lancamento.getQuantidadeCliente()));
        edtQuantidadeKg.setText(Utils.floatToString(lancamento.getQuantidadeKg()));
        edtPrecoKg.setText(Utils.floatToString(lancamento.getPrecoKg()));
        edtReceitaOutrosProdutos.setText(Utils.floatToString(lancamento.getReceitaOutrosProdutos()));

        edtGastoKg.setText(Utils.floatToString(lancamento.getGastoKg()));
        edtGastoOutrosProdutos.setText(Utils.floatToString(lancamento.getGastoOutrosProdutos()));
        edtGastoOperacional.setText(Utils.floatToString(lancamento.getGastoOperacional()));

        edtDinheiro.setText(Utils.floatToString(lancamento.getValorDinheiro()));
        edtDebito.setText(Utils.floatToString(lancamento.getValorDebito()));
        edtCredito.setText(Utils.floatToString(lancamento.getValorCredito()));
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

        salvaDados();

        startActivity(new Intent(this, CalendarActivity.class));
        finish();

    }

    private void salvaDados() {

        lancamento.setValorDebito(Utils.editableToFloat(edtDebito.getText()));
        lancamento.setValorCredito(Utils.editableToFloat(edtCredito.getText()));
        lancamento.setValorDinheiro(Utils.editableToFloat(edtDinheiro.getText()));
        lancamento.setGastoOperacional(Utils.editableToFloat(edtGastoOperacional.getText()));
        lancamento.setGastoOutrosProdutos(Utils.editableToFloat(edtGastoOutrosProdutos.getText()));
        lancamento.setGastoKg(Utils.editableToFloat(edtGastoKg.getText()));
        lancamento.setPrecoKg(Utils.editableToFloat(edtPrecoKg.getText()));
        lancamento.setQuantidadeKg(Utils.editableToFloat(edtQuantidadeKg.getText()));
        lancamento.setQuantidadeCliente(Utils.editableToFloat(edtQuantidadeCliente.getText()));
        lancamento.setReceitaOutrosProdutos(Utils.editableToFloat(edtReceitaOutrosProdutos.getText()));

        lancamento.setDia(dia);
        lancamento.setMes(mes);
        lancamento.setAno(ano);

        try {

            if (lancamento.getId() == null)
                dao.incluir(lancamento);
            else
                dao.alterar(lancamento);

        } catch (Exception e) {
            Utils.logException(this.getClass(), e);
        }

    }

}
