package br.com.diegodantas.financas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import br.com.diegodantas.dao.TabelasDao;

public class MainActivity extends AppCompatActivity implements Runnable {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabelasDao tabelasDao = new TabelasDao(this);
        tabelasDao.criaTabelas();

        handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    public void run() {
        startActivity(new Intent(this.getBaseContext(), MenuActivity.class));
        handler.removeCallbacksAndMessages(this);
        finish();
    }

    @Override
    public void onBackPressed() {}
}
