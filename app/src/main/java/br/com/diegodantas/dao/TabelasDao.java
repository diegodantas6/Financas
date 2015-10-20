package br.com.diegodantas.dao;

import java.util.ArrayList;

import android.content.Context;

public class TabelasDao extends AbstractDao {

	public TabelasDao(Context context) {
		super(context);
	}

	public void criaTabelas() {
		ArrayList<String> sql = new ArrayList<String>();
		String tabela;

//		tabela = "drop table cliente;";
//		sql.add(tabela);

		tabela = "create table if not exists cliente" +
				"(" +
				"cli_cd_cliente     integer not null primary key autoincrement," +
				"cli_ds_cliente     text," +
				"cli_dt_evento      text," +
				"cli_nr_telefone    text," +
				"cli_nr_celular     text"  +
				");";
		sql.add(tabela);

		super.execSql(sql);
	}

}