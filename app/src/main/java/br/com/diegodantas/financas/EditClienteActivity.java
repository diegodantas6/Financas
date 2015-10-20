package br.com.diegodantas.financas;

import br.com.diegodantas.dao.ClienteDao;
import br.com.diegodantas.model.Cliente;
import br.com.diegodantas.util.Mask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditClienteActivity extends Activity {

	EditText edtCliente;
	EditText edtData;
	EditText edtTelefone;
	EditText edtCelular;

	Long idCliente;
	Cliente cliente;
	ClienteDao clienteDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cliente);
		
		clienteDao = new ClienteDao(this);

		edtCliente = (EditText) findViewById(R.id.edtCliente);
		edtData = (EditText) findViewById(R.id.edtData);
		edtTelefone = (EditText) findViewById(R.id.edtTelefone);
		edtCelular = (EditText) findViewById(R.id.edtCelular);
		
		edtData.addTextChangedListener(Mask.insert("##/##/####", edtData));
		edtTelefone.addTextChangedListener(Mask.insert("(##)####-####", edtTelefone));
		edtCelular.addTextChangedListener(Mask.insert("(##)####-####", edtCelular));

		idCliente = getIntent().getLongExtra("id", 0);

		if (idCliente != 0) {
			cliente = clienteDao.getId(idCliente);

			edtCliente.setText(cliente.getCliDsCliente());
			edtData.setText(cliente.getCliDtEvento());
			edtTelefone.setText(cliente.getCliNrTelefone());
			edtCelular.setText(cliente.getCliNrCelular());
		}

		findViewById(R.id.imbCancelar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelar();
			}
		});

		findViewById(R.id.imbSalvar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				salvar();
			}
		});
	}

	@Override
	public void onBackPressed() {
		cancelar();
	}

	private void cancelar() {
		startActivity(new Intent(this.getBaseContext(), ListClienteActivity.class));
		finish();
	}

	private void salvar() {
		if (edtCliente.getText().toString().equals("")) {
			edtCliente.requestFocus();
			edtCliente.setError("Campo Obrigatorio");
			return;
		}
//		else if (edtData.getText().toString().equals("")) {
//			edtData.requestFocus();
//			edtData.setError("Campo Obrigatorio");
//			return;
//		}

		cliente = new Cliente();
		cliente.setCliDsCliente(edtCliente.getText().toString());
		cliente.setCliDtEvento(edtData.getText().toString());
		cliente.setCliNrTelefone(edtTelefone.getText().toString());
		cliente.setCliNrCelular(edtCelular.getText().toString());

		try {
			if (idCliente == 0) {
				clienteDao.incluir(cliente);
			} else {
				cliente.setCliCdCliente(idCliente);
				clienteDao.alterar(cliente);
			}

			Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
			cancelar();
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}
