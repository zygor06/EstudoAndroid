package caraoucoroa.hygor.com.br.caraoucoroa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView btnJogar;

    private String[] opcao ={"cara", "coroa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogar = (ImageView) findViewById(R.id.btnJogarId);
        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int numeroAleatorio = random.nextInt(2);

                Intent intent = new Intent(getApplicationContext(), DetalheActivity.class);
                intent.putExtra("opcao", opcao[numeroAleatorio]);
                startActivity(intent);
            }
        });
    }
}
