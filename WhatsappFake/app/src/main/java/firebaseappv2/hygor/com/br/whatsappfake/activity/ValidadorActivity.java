package firebaseappv2.hygor.com.br.whatsappfake.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import firebaseappv2.hygor.com.br.whatsappfake.R;

import static firebaseappv2.hygor.com.br.whatsappfake.R.id.editDDI;

public class ValidadorActivity extends AppCompatActivity {

    private EditText editCodValidacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        editCodValidacao = (EditText) findViewById(R.id.editCodValidacao);

        SimpleMaskFormatter simpleMaskCodigo = new SimpleMaskFormatter(" NNN NNN ");
        MaskTextWatcher maskCodigo = new MaskTextWatcher(editCodValidacao, simpleMaskCodigo);
        editCodValidacao.addTextChangedListener(maskCodigo);

    }
}
