package alert.hygor.com.br.alert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends Activity {

    private Button button;
    private EditText textoNome;
    private TextView resultado;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        button = (Button) findViewById(R.id.sp_button);
        textoNome = (EditText) findViewById(R.id.sp_editTextId);
        resultado = (TextView) findViewById(R.id.sp_textoResultadoId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textoNome.getText().toString().isEmpty()){
                    Toast.makeText(SharedPreferencesActivity.this, "Por favor, preencher o nome", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("nome", textoNome.getText().toString());
                    editor.commit();
                    resultado.setText("Olá, " + sharedPreferences.getString("nome", "Usuário não definido"));
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("nome")){
            resultado.setText("Olá, " + sharedPreferences.getString("nome", "Usuário não definido"));
        }else{
            resultado.setText("Olá usuário!");
        }

    }
}
