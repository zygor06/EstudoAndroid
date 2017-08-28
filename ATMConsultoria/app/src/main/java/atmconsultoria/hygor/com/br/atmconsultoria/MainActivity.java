package atmconsultoria.hygor.com.br.atmconsultoria;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity{

    private ImageView botaoEmpresa;
    private ImageView botaoServicos;
    private ImageView botaoClientes;
    private ImageView botaoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEmpresa = (ImageView) findViewById(R.id.btnEmpresaId);
        botaoServicos = (ImageView) findViewById(R.id.btnServicosId);
        botaoClientes = (ImageView) findViewById(R.id.btnClientesId);
        botaoContato = (ImageView) findViewById(R.id.btnContatoId);

        botaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmpresaActivity.class));
            }
        });

        botaoServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ServicosActivity.class));
            }
        });

        botaoClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClienteActivity.class));
            }
        });

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ContatoActivity.class));
            }
        });

    }
}
