package idadedecachorro.hygor.com.br.idadedecachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText idade;
    private Button descobrir;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idade = (EditText) findViewById(R.id.idadeId);
        descobrir = (Button) findViewById(R.id.buttonDescobrirId);
        resultado = (TextView) findViewById(R.id.resultadoId);

        descobrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigitado = idade.getText().toString();
                if(textoDigitado.isEmpty()){
                    resultado.setText("Por favor, digite um valor válido");
                }else{

                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int resultadoFinal = valorDigitado * 7;

                    resultado.setText("A idade do cachorro em anos humanos é: " + resultadoFinal + " anos.");

                }
            }
        });
    }
}
