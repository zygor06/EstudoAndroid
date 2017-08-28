package gasolinaoualcool.hygor.com.br.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button verificar;
    private EditText precoAlcool;
    private EditText precoGasolina;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        verificar = (Button) findViewById(R.id.btnVerificarId);
        precoAlcool = (EditText) findViewById(R.id.inputAlcoolId);
        precoGasolina = (EditText) findViewById(R.id.inputGasolinaId);
        resultado = (TextView) findViewById(R.id.txtResultadoId);

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoAlcool = precoAlcool.getText().toString();
                String textoGasolina = precoGasolina.getText().toString();
                if(textoAlcool.isEmpty() || textoGasolina.isEmpty()){
                    resultado.setText("Por favor, informe os dois valores.");
                }else{
                    Double alcool = Double.parseDouble(textoAlcool);
                    Double gasolina = Double.parseDouble(textoGasolina);

                    double resultadoFinal = alcool / gasolina;

                    if(resultadoFinal >= 0.7){
                        resultado.setText("Compensa mais utilizar a Gasolina");
                    }else{
                        resultado.setText("Compensa mais utilizar o Álcool");
                    }
                }

            }
        });

    }
}
