package firebaseappv2.hygor.com.br.whatsappfake.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import firebaseappv2.hygor.com.br.whatsappfake.R;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Preferencias;

public class ValidadorActivity extends AppCompatActivity {

    private EditText editCodValidacao;
    private Button btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        btnValidar = (Button) findViewById(R.id.btnValidar);
        editCodValidacao = (EditText) findViewById(R.id.editCodValidacao);

        SimpleMaskFormatter simpleMaskCodigo = new SimpleMaskFormatter(" NNN NNN ");
        MaskTextWatcher maskCodigo = new MaskTextWatcher(editCodValidacao, simpleMaskCodigo);
        editCodValidacao.addTextChangedListener(maskCodigo);

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preferencias preferencias = new Preferencias(ValidadorActivity.this);
                HashMap<String, String> dadosUsuario = preferencias.getDadosUsuario();

                String tokenGerado = dadosUsuario.get("token");
                String tokenDigitado = editCodValidacao.getText().toString();
                tokenDigitado = tokenDigitado.replace(" ", "");

                if(tokenDigitado.equals(tokenGerado)){
                    Toast.makeText(ValidadorActivity.this, "Token VALIDADO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ValidadorActivity.this, "Token NÃO VALIDADO", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
