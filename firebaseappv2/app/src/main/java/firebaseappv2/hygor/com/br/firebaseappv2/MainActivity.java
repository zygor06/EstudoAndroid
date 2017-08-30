package firebaseappv2.hygor.com.br.firebaseappv2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        //Cadastro

        /*auth.createUserWithEmailAndPassword("hygor@teste.com", "nemteconto").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Log.i("CREATE USER", "Sucesso ao cadastrar");
                }else{
                    Log.i("CREATE USER", "Erro ao cadastrar usuário");
                }

            }
        });*/

        //Login

        /*auth.signInWithEmailAndPassword("hygor@teste.com", "nemteconto").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Log.i("CREATE USER", "Sucesso ao logar");
                }else{
                    Log.i("CREATE USER", "Erro ao logar usuário");
                }

            }
        });*/

        auth.signOut();

        //Verificar usuário logado

        if(auth.getCurrentUser() != null){

            Log.i("VERIFICA USUARIO LOGADO", "Usuário está logado!!");

        }else{

            Log.i("VERIFICA USUARIO LOGADO", "Usuário não está logado!!");
        }
    }
}
