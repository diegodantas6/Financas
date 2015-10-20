package br.com.diegodantas.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import br.com.diegodantas.financas.R;
import br.com.diegodantas.model.Cliente;

public class ClienteDao extends AbstractDao {

	private static final String TABLE_NAME = "cliente";
	private static final String FIELD_ID = "cli_cd_cliente";
	private static final String FIELD_DS = "cli_ds_cliente";
	private static final String ORDER_BY = "cli_cd_cliente";

	public ClienteDao(Context context) {
		super(context, TABLE_NAME, FIELD_ID, ORDER_BY);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> getList() {
		return (ArrayList<Cliente>) super.getList(new Cliente());
	}

	public Cliente getId(Long id) {
		return (Cliente) super.getId(new Cliente(), id);
	}
	
	public Cursor getListCursor() {
		Cursor cursor = super.execSql("select " + FIELD_ID + " as _id , " + FIELD_DS + " from " + TABLE_NAME + " order by " + ORDER_BY);
		//super.close();
		return cursor;
	}
	
	public String[] getColumns() {
		String[] columns = new String[] {
			    "_id",
			    FIELD_DS
			  };
		return columns;
	}
	
	public int[] getTo() {
		int[] to = new int[] { 
			    R.id.code,
			    R.id.name
			  };
		return to;
	}
}
