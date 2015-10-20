package br.com.diegodantas.financas;

import br.com.diegodantas.dao.ClienteDao;
import br.com.diegodantas.util.Utils;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListClienteActivity extends ListActivity {

	EditText edtPesquisar;
	SimpleCursorAdapter adapter;
	ClienteDao clienteDao;
	
	Long clienteSelected = 0l;
	
	private static final int VISIBLE = 0;
	private static final int INVISIBLE = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_base);
		
		clienteDao = new ClienteDao(this);
		
		refreshList();
		
		edtPesquisar = (EditText) findViewById(R.id.edtPesquisar);
		edtPesquisar.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				pesquisar();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		
		findViewById(R.id.imbPesquisar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				fecharPesquisar();
			}
		});
	}

	private void fecharPesquisar() {
		edtPesquisar.setVisibility(View.INVISIBLE);
	}

	private void refreshList() {
		adapter = new SimpleCursorAdapter(this, R.layout.activity_list_base_item, clienteDao.getListCursor(), clienteDao.getColumns(), clienteDao.getTo(), 0);
		this.setListAdapter(adapter);
		findViewById(android.R.id.list).requestFocus();
		clienteDao.close();
	}

	private void pesquisar() {
		adapter.getFilter().filter(edtPesquisar.getText().toString());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Cursor cursor = (Cursor) l.getItemAtPosition(position);
		clienteSelected = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
//		String cliente = cursor.getString(cursor.getColumnIndexOrThrow("cli_ds_cliente"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.men_incluir) {
			startActivity(new Intent(this, EditClienteActivity.class));
			finish();
		}
		if (item.getItemId() == R.id.men_pesquisar) {
			edtPesquisar.setVisibility(View.VISIBLE);
		}
		else if (item.getItemId() == R.id.men_alterar) {
			if (clienteSelected == 0) {
				Utils.showToast(this, "Selecione um cliente primeiro!");
			} else {
				Intent intent = new Intent(this, EditClienteActivity.class);
				intent.putExtra("id", clienteSelected);
				startActivity(intent);
				finish();
			}
		}
		else if (item.getItemId() == R.id.men_excluir) {
			if (clienteSelected == 0) {
				Utils.showToast(this, "Selecione um cliente primeiro!");
			} else {
				AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//				dialog.setIcon(R.drawable.ic_launcher);
				dialog.setTitle(R.string.app_name);
				dialog.setMessage("Tem certeza que deseja excluir?\nCliente: " + clienteDao.getId(clienteSelected).getCliDsCliente());

				dialog.setPositiveButton("Sim", new	DialogInterface.OnClickListener() {
					public void onClick(DialogInterface di, int arg) {
						excluir();
					}
				});
				dialog.setNegativeButton("N�o", new	DialogInterface.OnClickListener() {
					public void onClick(DialogInterface di, int arg) {
						di.dismiss();
					}
				});
				dialog.show();
			}
		}
		else if (item.getItemId() == R.id.men_menu) {
			voltarMenu();
		}
		else if (item.getItemId() == R.id.men_sair) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//			dialog.setIcon(R.drawable.ic_launcher);
			dialog.setTitle(R.string.app_name);
			dialog.setMessage("Tem certeza que deseja sair?");

			dialog.setPositiveButton("Sim", new	DialogInterface.OnClickListener() {
				public void onClick(DialogInterface di, int arg) {
					finish();
				}
			});
			dialog.setNegativeButton("N�o", new	DialogInterface.OnClickListener() {
				public void onClick(DialogInterface di, int arg) {
					di.dismiss();
				}
			});
			dialog.show();
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onBackPressed() {
		voltarMenu();
	}

	private void voltarMenu() {
		startActivity(new Intent(this, MenuActivity.class));
		finish();
	}

	private void excluir() {
		try {
			clienteDao.excluir(clienteSelected);
			refreshList();
			clienteSelected = 0l;
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
