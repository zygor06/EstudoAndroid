package alert.hygor.com.br.alert;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alert.hygor.com.br.fragments.CadastroFragment;
import alert.hygor.com.br.fragments.LoginFragment;

public class FragmentsActivity extends AppCompatActivity {

    private Button btnLogar;
    private Boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnLogar.setText("Cadastre-se");
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(status){

                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction.add(R.id.rl_container_fragmento, loginFragment);
                    fragmentTransaction.commit();
                    btnLogar.setText("Cadastre-se");
                    status = false;
                }else{

                    CadastroFragment cadastroFragment = new CadastroFragment();
                    fragmentTransaction.add(R.id.rl_container_fragmento, cadastroFragment);
                    fragmentTransaction.commit();
                    btnLogar.setText("Logar");
                    status = true;
                }
            }
        });

    }
}
