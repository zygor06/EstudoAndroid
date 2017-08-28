package alert.hygor.com.br.alert;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AnotacoesActivity extends AppCompatActivity {

    private ImageView btnSalvar;
    private EditText editText;

    private static final String NOME_ARQUIVO = "arquivo_anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);

        btnSalvar = (ImageView) findViewById(R.id.anotacoes_btnSalvarId);
        editText = (EditText) findViewById(R.id.anotacoes_editTextId);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoDigitado = editText.getText().toString();
                gravarNoArquivo(textoDigitado);
            }
        });

        if(lerArquivo(NOME_ARQUIVO) != null) {
            editText.setText(lerArquivo(NOME_ARQUIVO));
        }
    }

    private void gravarNoArquivo(String s){

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));

            outputStreamWriter.write(s);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("AnotacoesActivity", e.toString());
        }
    }

    private String lerArquivo(String s){

        String resultado = "";

        try{

            InputStream arquivo = openFileInput(s);

            if(arquivo != null){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(arquivo));

                String linha = "";

                while((linha = bufferedReader.readLine()) != null){
                    resultado += linha;
                }

            }

            arquivo.close();

        }catch(IOException e){
            Log.v("AnotacoesActivity", e.toString());
        }

        return resultado;

    }
}
