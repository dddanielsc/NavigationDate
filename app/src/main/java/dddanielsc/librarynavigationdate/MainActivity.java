package dddanielsc.librarynavigationdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import dddanielsc.navigationdate.NavigationDate;
import dddanielsc.navigationdate.OnNavigationDate;

public class MainActivity extends AppCompatActivity {

    private NavigationDate navigationDate;

    private TextView tvInicio;
    private TextView tvFim;
    private TextView tvData;

    private AppCompatButton buttonDia;
    private AppCompatButton buttonSemana;
    private AppCompatButton buttonMes;
    private AppCompatButton buttonAno;
    private AppCompatButton buttonDataAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.navigationDate = (NavigationDate) findViewById(R.id.navigation_date);

        this.tvInicio = (TextView) findViewById(R.id.tv_inicio);
        this.tvFim = (TextView) findViewById(R.id.tv_fim);
        this.tvData = (TextView) findViewById(R.id.tv_data);

        this.buttonDia = (AppCompatButton) findViewById(R.id.btn_intervalo_dia);
        this.buttonSemana = (AppCompatButton) findViewById(R.id.btn_intervalo_semana);
        this.buttonMes = (AppCompatButton) findViewById(R.id.btn_intervalo_mes);
        this.buttonAno = (AppCompatButton) findViewById(R.id.btn_intervalo_ano);
        this.buttonDataAtual = (AppCompatButton) findViewById(R.id.btn_data_atual);

        navigationDate.initDate( new DateTime() );
    }

    @Override
    protected void onResume() {
        super.onResume();


        tvData.setText( navigationDate.getDateCurrent().toString( Utils.formatterJodaTimeBR() ));



        navigationDate.setOnNavigationDate(new OnNavigationDate() {
            @Override
            public void onDateChosen(NavigationDate navigationDate, DateTime begin, DateTime end) {

                tvInicio.setText( begin.toString( Utils.formatterJodaTimeBR() ));
                tvFim.setText( end.toString( Utils.formatterJodaTimeBR() ));

                tvData.setText( navigationDate.getDateCurrent().toString( Utils.formatterJodaTimeBR() ));

            }
        });


        buttonDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigationDate.setIntervalType( NavigationDate.INTERVAL_DAY );

            }
        });

        buttonSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigationDate.setIntervalType( NavigationDate.INTERVAL_WEEK );

            }
        });


        buttonMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigationDate.setIntervalType( NavigationDate.INTERVAL_MONTH );

            }
        });



        buttonAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigationDate.setIntervalType( NavigationDate.INTERVAL_YEAR );

            }
        });



        buttonDataAtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigationDate.setDateCurrent( new DateTime() );

            }
        });



    }
}
