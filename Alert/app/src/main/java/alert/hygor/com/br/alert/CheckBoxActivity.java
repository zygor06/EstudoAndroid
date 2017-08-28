package alert.hygor.com.br.alert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxActivity extends Activity {

    private CheckBox cao;
    private CheckBox gato;
    private CheckBox papagaio;
    private TextView texto;
    private Button mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        cao = (CheckBox) findViewById(R.id.caoId);
        gato = (CheckBox) findViewById(R.id.gatoId);
        papagaio = (CheckBox) findViewById(R.id.papagaioId);
        texto = (TextView) findViewById(R.id.textoId);
        mostrar = (Button) findViewById(R.id.buttonId);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itensSelecionados = "";

                itensSelecionados += "Item: " + cao.getText()  + " - " + cao.isChecked() +  "\n";
                itensSelecionados += "Item: " + gato.getText()  + " - " + gato.isChecked() +  "\n";
                itensSelecionados += "Item: " + papagaio.getText()  + " - " + papagaio.isChecked() +  "\n";

                texto.setText(itensSelecionados);

            }
        });

    }
}
