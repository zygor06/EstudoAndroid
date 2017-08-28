package alert.hygor.com.br.alert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleActivity extends Activity {

    private ToggleButton tButton;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toogle);

        tButton = (ToggleButton) findViewById(R.id.toggleButtonId);
        texto = (TextView) findViewById(R.id.textoExibicaoId);

        tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    texto.setText("Ligado");
                }else{
                    texto.setText(null);
                }
            }
        });
    }
}
