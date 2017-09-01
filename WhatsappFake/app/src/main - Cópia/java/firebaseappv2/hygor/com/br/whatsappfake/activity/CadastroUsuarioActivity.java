package firebaseappv2.hygor.com.br.whatsappfake.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import firebaseappv2.hygor.com.br.whatsappfake.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText cadastroNome;
    private EditText cadastroEmail;
    private EditText cadastroSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cadastroNome = (EditText) findViewById(R.id.editCadastroNome);
        cadastroEmail = (EditText) findViewById(R.id.editEmail);
        cadastroSenha = (EditText) findViewById(R.id.editSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
