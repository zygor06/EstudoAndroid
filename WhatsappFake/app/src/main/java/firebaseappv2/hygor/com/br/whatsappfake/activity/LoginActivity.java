package firebaseappv2.hygor.com.br.whatsappfake.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import firebaseappv2.hygor.com.br.whatsappfake.R;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private Button btnCadastrar;

    private EditText editTelefone;
    private EditText editDDD;
    private EditText editDDI;
    private EditText editNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCadastrar   = (Button) findViewById(R.id.btnCadastrar);
        editTelefone   = (EditText) findViewById(R.id.editTelefone);
        editDDD        = (EditText) findViewById(R.id.editDDD);
        editDDI        = (EditText) findViewById(R.id.editDDI);
        editNome       = (EditText) findViewById(R.id.editNome);

        SimpleMaskFormatter simpleMaskDDI = new SimpleMaskFormatter(" +NNN ");
        MaskTextWatcher maskDDI = new MaskTextWatcher(editDDI, simpleMaskDDI);
        editDDI.addTextChangedListener(maskDDI);

        SimpleMaskFormatter simpleMaskDDD = new SimpleMaskFormatter(" NN ");
        MaskTextWatcher maskDDD = new MaskTextWatcher(editDDD, simpleMaskDDD);
        editDDD.addTextChangedListener(maskDDD);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter(" N NNNN-NNNN ");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(editTelefone, simpleMaskTelefone);
        editTelefone.addTextChangedListener(maskTelefone);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = editNome.getText().toString();
                String telefoneCompleto =
                                editDDI.getText().toString() +
                                editDDD.getText().toString() +
                                editTelefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(" ", "");

                String numeroEmulador = "5554";

                //Gerar token
                Random random = new Random();
                int numRandom = random.nextInt(999999 - 100000) + 100000;

                String token = String.valueOf(numRandom);
                String mensagemEnvio = "Whatsapp codigo de confirmação: " + token;

                //Salvar dados para validação
                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                //Envio do SMS
                boolean enviadoSMS = enviaSMS("+" + numeroEmulador, mensagemEnvio);

                /*HashMap<String, String> usuario = preferencias.getDadosUsuario();
                Log.i("TOKEN", "T:" + usuario.get("token"));*/

                startActivity(new Intent(LoginActivity.this, ValidadorActivity.class));
            }
        });

    }

    private boolean enviaSMS(String telefone, String mensagem){

        try{

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);

            return true;

        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
}
