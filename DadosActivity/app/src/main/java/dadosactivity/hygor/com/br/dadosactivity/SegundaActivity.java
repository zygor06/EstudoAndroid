package dadosactivity.hygor.com.br.dadosactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SegundaActivity extends Activity {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        texto = (TextView) findViewById(R.id.textoId);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            String textoPassado = extra.getString("nome");
            texto.setText(text);
        }

    }
}
