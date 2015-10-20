package br.com.diegodantas.interfaces;

import android.content.ContentValues;
import android.database.Cursor;

public interface ObjectModel {

	public ContentValues getContentValues();

	public ObjectModel cursorToObjectModel(Cursor cursor);

	public void validaCamposObrigatorios() throws Exception;

}
