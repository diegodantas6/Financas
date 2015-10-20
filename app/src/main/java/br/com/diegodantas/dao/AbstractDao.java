package br.com.diegodantas.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.diegodantas.interfaces.ObjectModel;

public abstract class AbstractDao extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "teste.db";
	private static final int DATABASE_VERSION = 1;

	private String TABLE_NAME;
	private String FIELD_ID;
	private String ORDER_BY;

	private SQLiteDatabase sqLiteDatabase;

	public AbstractDao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public AbstractDao(Context context, String tableName, String fieldId, String fieldOrder) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.TABLE_NAME = tableName;
		this.FIELD_ID = fieldId;
		this.ORDER_BY = fieldOrder;
	}

	public void initialize() {
		sqLiteDatabase = this.getWritableDatabase();
	}

	public long incluir(ObjectModel objectModel) throws Exception {
		long l = 0;
		sqLiteDatabase = this.getWritableDatabase();
		try {
			objectModel.validaCamposObrigatorios();
			l = sqLiteDatabase.insert(this.TABLE_NAME, null, objectModel.getContentValues());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			this.close();
		}
		return l;
	}

	public void alterar(ObjectModel objectModel) throws Exception {
		sqLiteDatabase = this.getWritableDatabase();
		try {
			objectModel.validaCamposObrigatorios();
			sqLiteDatabase.replace(this.TABLE_NAME, null, objectModel.getContentValues());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			this.close();
		}
	}

	public void excluir(long id) throws Exception {
		int i = 0;
		sqLiteDatabase = this.getWritableDatabase();
		try {
			i = sqLiteDatabase.delete(this.TABLE_NAME, this.FIELD_ID + " = " + id, null);
			if (i == 0)
				throw new Exception("asd");
		} catch (Exception e) {
			throw new Exception("N?o foi poss?vel remover o registro!");
		} finally {
			this.close();
		}
	}

	public void excluirTudo() throws Exception {
		sqLiteDatabase = this.getWritableDatabase();
		try {
			sqLiteDatabase.delete(this.TABLE_NAME, null, null);
		} catch (Exception e) {
			throw new Exception("N?o foi poss?vel remover os registros!");
		} finally {
			this.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ArrayList getList(ObjectModel objectModel) {
		Cursor cursor = this.execSql("select * from " + TABLE_NAME + " order by " + ORDER_BY);
		ArrayList list = new ArrayList();
		while (cursor.moveToNext()) {
			list.add(objectModel.cursorToObjectModel(cursor));
		}
		this.close();
		return list;
	}

	protected ObjectModel getId(ObjectModel objectModel, long id) {
		Cursor cursor = this.execSql("select * from " + TABLE_NAME + " where " + FIELD_ID + " = " + id);
		ObjectModel ret = null;
		if (cursor.moveToFirst()) {
			ret = objectModel.cursorToObjectModel(cursor);
		}
		this.close();
		return ret;
	}

	public Cursor execSql(String sql) {
		Cursor cursor = null;
		sqLiteDatabase = this.getReadableDatabase();
		try {
			cursor = sqLiteDatabase.rawQuery(sql, null);
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), e.getMessage());
		}
		return cursor;
	}

	public void execSql(ArrayList<String> sql) {
		sqLiteDatabase = this.getWritableDatabase();
		try {
			for (String string : sql) {
				sqLiteDatabase.execSQL(string);
			}
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), e.getMessage());
		} finally {
			this.close();
		}
	}

	public void executeQuery(String sql) {
		try {
			sqLiteDatabase.execSQL(sql);
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), e.getMessage());
		}
	}

	public boolean isEmpyt() {
		Cursor cursor = execSql("select count(" + this.FIELD_ID + ") from "+ this.TABLE_NAME);
		boolean retorno = true;
		while (cursor.moveToNext()) {
			Long count = cursor.getLong(0);
			if (count != null)
				retorno = !(count > 0);
		}
		close();
		return retorno;
	}

	public void close() {
		if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
			sqLiteDatabase.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}