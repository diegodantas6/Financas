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

//		tabela = "drop table lancamento;";
//		sql.add(tabela);

		tabela = "create table if not exists lancamento" +
				"(" +
				"id     integer not null primary key autoincrement," +
				"dia    integer not null," +
				"mes    integer not null," +
				"ano    integer not null," +
				"quantidade_cliente      real," +
				"quantidade_kg    		 real," +
				"preco_kg    			 real," +
				"receita_outros_produtos real," +
				"gasto_kg    			 real," +
				"gasto_outros_produtos   real," +
				"gasto_operacional       real," +
				"valor_dinheiro          real," +
				"valor_credito           real," +
				"valor_debito    		 real" +
				");";
		sql.add(tabela);

		super.execSql(sql);
	}

}