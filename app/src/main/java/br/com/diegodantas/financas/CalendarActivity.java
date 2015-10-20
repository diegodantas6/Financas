package br.com.diegodantas.financas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        viewToClass();

        addEvents();

    }

    private void addEvents() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String data = String.format("Dia: %s, Mês: %s, Ano: %s", dayOfMonth, month, year);

                Toast.makeText(CalendarActivity.this.getApplicationContext(), data, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void viewToClass() {
        calendarView = (CalendarView) findViewById(R.id.calendarView);
    }
}
