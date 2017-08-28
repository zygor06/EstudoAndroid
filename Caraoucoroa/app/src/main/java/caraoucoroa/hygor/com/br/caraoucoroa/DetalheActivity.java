package caraoucoroa.hygor.com.br.caraoucoroa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class DetalheActivity extends AppCompatActivity {

    private ImageView btnVoltar;
    private ImageView moeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        moeda = (ImageView) findViewById(R.id.moedaId);

        Bundle extra = getIntent().getExtras();
        if(extra != null){

            String opcao = extra.getString("opcao");
            if(opcao.equals("cara")){
                moeda.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.moeda_cara));
            }else{
                moeda.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.moeda_coroa));
            }

        }
        btnVoltar = (ImageView) findViewById(R.id.btnVoltarId);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(DetalheActivity.this, MainActivity.class));
            }
        });
    }
}