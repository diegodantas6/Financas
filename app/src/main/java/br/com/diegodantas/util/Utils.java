package br.com.diegodantas.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.com.diegodantas.financas.R;

public class Utils {
	
	@SuppressWarnings("rawtypes")
	public static void logException(Class c, Exception e) {
		Log.e(c.getSimpleName(), "Causa: " + e.getCause() + "\nMessage: " + e.getMessage());
	}

	@SuppressWarnings("rawtypes")
	public static void logException(Class c, String mensagem) {
		Log.e(c.getSimpleName(), mensagem);
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Retorna false caso n�o esteja conectado
	 */
	public static boolean verificaConexao(Context context) {
	    boolean conectado;
		ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (((conectivtyManager.getActiveNetworkInfo() != null) && (conectivtyManager.getActiveNetworkInfo().isAvailable()) && (conectivtyManager.getActiveNetworkInfo().isConnected()))) {
	    	conectado = true;
	    } else {
	        conectado = false;
	    }
	    return conectado;
	}
	
	public static void showAlert(Context context, String message) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//		dialog.setIcon(R.drawable.ic_launcher);
		dialog.setTitle(R.string.app_name);
		dialog.setMessage(message);

		dialog.setNeutralButton("OK", new	DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int arg) {
			}
		});
		dialog.show();
	}
	
	public static ProgressDialog showProcessDialog(Context context, String message) {
		ProgressDialog progressDialog = ProgressDialog.show(context, "FvMobile", message);
//		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(false);
		return progressDialog;
	}

	public static ProgressDialog showProcessDialog(Context context) {
		ProgressDialog progressDialog = ProgressDialog.show(context, "FvMobile", "Aguarde por favor!");
//		progressDialog.setIcon(R.drawable.ic_launcher);
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(false);
		return progressDialog;
	}
	
	public static Intent calculadora() {
		Intent intent = new Intent();
		intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
		return intent; 
	}
	
	public static String converteUrl(String text) {
		String str = text; 
		if (text != null) {
			//str = str.replace("{", "%7B");
			//str = str.replace("}", "%7D");
			str = str.replace("\\", "");
		}
		return str;
	}

	public static String formatStringToFloat(String valor) {

		String pattern = "#,##0.00";
		Locale locale = new Locale("pt", "BR");
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
		DecimalFormat decimalFormat = new DecimalFormat(pattern, decimalFormatSymbols);

		return decimalFormat.format(Double.valueOf(valor));

	}
}
