package frasedodia.hygor.com.br.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;
    private int atual = 0;

    private final String[] frases ={"Hello", "World"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoNovaFrase.setText(frases[atual]);
                if(atual >= frases.length - 1){
                    atual = 0;
                }else{
                    atual++;
                }
            }
        });
    }
}
