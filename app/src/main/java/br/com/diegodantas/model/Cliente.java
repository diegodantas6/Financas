package br.com.diegodantas.model;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.diegodantas.interfaces.ObjectModel;

public class Cliente implements ObjectModel {

	private Long cliCdCliente;
	private String cliDsCliente;
	private String cliDtEvento;
	private String cliNrTelefone;
	private String cliNrCelular;

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put("cli_cd_cliente", this.getCliCdCliente());
		values.put("cli_ds_cliente", this.getCliDsCliente());
		values.put("cli_dt_evento", this.getCliDtEvento());
		values.put("cli_nr_telefone", this.getCliNrTelefone());
		values.put("cli_nr_celular", this.getCliNrCelular());

		return values;
	}

	@Override
	public ObjectModel cursorToObjectModel(Cursor cursor) {
		Cliente obj = new Cliente();
		obj.setCliCdCliente(cursor.getLong(cursor.getColumnIndex("cli_cd_cliente")));
		obj.setCliDsCliente(cursor.getString(cursor.getColumnIndex("cli_ds_cliente")));
		obj.setCliDtEvento(cursor.getString(cursor.getColumnIndex("cli_dt_evento")));
		obj.setCliNrTelefone(cursor.getString(cursor.getColumnIndex("cli_nr_telefone")));
		obj.setCliNrCelular(cursor.getString(cursor.getColumnIndex("cli_nr_celular")));

		return obj;
	}

	@Override
	public void validaCamposObrigatorios() throws Exception {
		if (this.getCliDsCliente() == null)
			throw new Exception("O campo 'Descri��o' ? obrigat�rio!");
		else if (this.getCliDtEvento().isEmpty())
			throw new Exception("O campo 'Data' � obrigat�rio!");
	}

	public Long getCliCdCliente() {
		return cliCdCliente;
	}

	public void setCliCdCliente(Long cliCdCliente) {
		this.cliCdCliente = cliCdCliente;
	}

	public String getCliDsCliente() {
		return cliDsCliente;
	}

	public void setCliDsCliente(String cliDsCliente) {
		this.cliDsCliente = cliDsCliente;
	}

	public String getCliDtEvento() {
		return cliDtEvento;
	}

	public void setCliDtEvento(String cliDtEvento) {
		this.cliDtEvento = cliDtEvento;
	}

	public String getCliNrTelefone() {
		return cliNrTelefone;
	}

	public void setCliNrTelefone(String cliNrTelefone) {
		this.cliNrTelefone = cliNrTelefone;
	}

	public String getCliNrCelular() {
		return cliNrCelular;
	}

	public void setCliNrCelular(String cliNrCelular) {
		this.cliNrCelular = cliNrCelular;
	}

}