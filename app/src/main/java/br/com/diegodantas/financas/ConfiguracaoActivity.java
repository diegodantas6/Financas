package br.com.diegodantas.financas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.diegodantas.util.Constantes;
import br.com.diegodantas.util.Utils;

public class ConfiguracaoActivity extends AppCompatActivity {

    SharedPreferences preferences;

    EditText edtPrecoKg;
    EditText edtTaxaCredito;
    EditText edtTaxaDebito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        viewToClass();

        getDados();
    }

    private void viewToClass() {
        edtPrecoKg = (EditText) findViewById(R.id.edtPrecoKg);
        edtTaxaCredito = (EditText) findViewById(R.id.edtTaxaCredito);
        edtTaxaDebito = (EditText) findViewById(R.id.edtTaxaDebito);
    }

    private void getDados() {
        preferences = getSharedPreferences(Constantes.PREF_NAME, MODE_PRIVATE);

        edtPrecoKg.setText(preferences.getString(Constantes.PRECO_KG, "29.99"));
        edtTaxaCredito.setText(preferences.getString(Constantes.TAXA_CREDITO, "4.5"));
        edtTaxaDebito.setText(preferences.getString(Constantes.TAXA_DEBITO, "2.3"));
    }

    @Override
    public void onBackPressed() {

        if (edtPrecoKg.getText().toString().isEmpty()) {
            edtPrecoKg.setError("Campo obrigatório!");
            return;
        } else if (edtTaxaCredito.getText().toString().isEmpty()) {
            edtTaxaCredito.setError("Campo obrigatório!");
            return;
        } else if (edtTaxaDebito.getText().toString().isEmpty()) {
            edtTaxaDebito.setError("Campo obrigatório!");
            return;
        }

        salvaDados();

        startActivity(new Intent(this, MenuActivity.class));
        finish();

    }

    private void salvaDados() {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(Constantes.PRECO_KG, edtPrecoKg.getText().toString());
        editor.putString(Constantes.TAXA_CREDITO, edtTaxaCredito.getText().toString());
        editor.putString(Constantes.TAXA_DEBITO, edtTaxaDebito.getText().toString());

        editor.commit();

        Utils.showToast(this.getApplicationContext(), "Salvo com sucesso!");

    }

}
