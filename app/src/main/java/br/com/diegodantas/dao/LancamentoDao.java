package br.com.diegodantas.dao;

import android.content.Context;
import android.database.Cursor;

import br.com.diegodantas.model.Lancamento;

public class LancamentoDao extends AbstractDao {

	private static final String TABLE_NAME = "lancamento";
	private static final String FIELD_ID = "id";
	private static final String FIELD_DS = "dia";
	private static final String ORDER_BY = "dia";

	public LancamentoDao(Context context) {
		super(context, TABLE_NAME, FIELD_ID, ORDER_BY);
	}

	public Lancamento getLancamento(int dia, int mes, int ano) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append(" from lancamento ");
		sql.append(" where dia = ");
		sql.append(dia);
		sql.append(" and mes = ");
		sql.append(mes);
		sql.append(" and ano = ");
		sql.append(ano);

		Cursor cursor = super.execSql(sql.toString());

		return (Lancamento) new Lancamento().cursorToObjectModel(cursor);
	}

}
