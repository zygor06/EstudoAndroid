package firebaseappv2.hygor.com.br.whatsappfake.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import firebaseappv2.hygor.com.br.whatsappfake.R;
import firebaseappv2.hygor.com.br.whatsappfake.config.ConfiguracaoFireBase;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Base64Custom;
import firebaseappv2.hygor.com.br.whatsappfake.helper.Preferencias;
import firebaseappv2.hygor.com.br.whatsappfake.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference reference;

    private EditText editEmail;
    private EditText editSenha;
    private Usuario usuario;
    private FirebaseAuth authenticator;
    private Preferencias preferencias;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferencias = new Preferencias(this);

        verificarUsuarioLogado();

        //Verifica se o usuário já realizou login anteriormente, e salva os dados



        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);

    }

    public void logar(View view){

        if(verificaDados()){

            usuario = new Usuario();
            usuario.setEmail(editEmail.getText().toString());
            usuario.setSenha(editSenha.getText().toString());

            validarLogin();

        }else{
            Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    private void verificarUsuarioLogado(){
        authenticator = ConfiguracaoFireBase.getFirebaseAuth();
        if(authenticator.getCurrentUser() != null){
            preferencias = new Preferencias(LoginActivity.this);
            HashMap<String, String> dados = preferencias.getDadosUsuario();
            Toast.makeText(LoginActivity.this,dados.get("identificador"), Toast.LENGTH_SHORT ).show();
            abrirTelaPrincipal();
        }
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for( int resultado : grantResults ){

            if( resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar esse app, é necessário aceitar as permissões");

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean verificaDados(){
        if(editEmail.getText().toString().isEmpty() || editEmail.getText().toString().equals("")
                ||editSenha.getText().toString().isEmpty() || editSenha.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    private void validarLogin(){

        authenticator = ConfiguracaoFireBase.getFirebaseAuth();
        authenticator.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                String errMessage = "";

                if(task.isSuccessful()){
                    //Sucesso ao logar



                    reference = ConfiguracaoFireBase.getFirebase()
                            .child("usuarios")
                            .child(Base64Custom.codificar64(usuario.getEmail()));

                    valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Usuario usuarioRecuperado = dataSnapshot.getValue(Usuario.class);

                            preferencias = new Preferencias(LoginActivity.this);
                            preferencias.salvarDados(usuario.getEmail(), usuarioRecuperado.getNome());

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    reference.addListenerForSingleValueEvent(valueEventListener);


                    abrirTelaPrincipal();

                }else{
                    try{
                        throw task.getException();
                    } catch (Exception e) {
                        e.printStackTrace();
                        errMessage = "Erro ao logar";
                    }

                    Toast.makeText(LoginActivity.this, errMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}