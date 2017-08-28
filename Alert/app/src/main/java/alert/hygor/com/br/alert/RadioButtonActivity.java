package alert.hygor.com.br.alert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonActivity extends Activity {

    private RadioGroup grupo;
    private RadioButton radioEscolhido;
    private Button button;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        grupo = (RadioGroup) findViewById(R.id.radioGroupId);
        button = (Button) findViewById(R.id.buttonId);
        texto = (TextView) findViewById(R.id.escolhaId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioEscolhido  = (RadioButton) findViewById(grupo.getCheckedRadioButtonId());
                texto.setText(radioEscolhido.getText());
            }
        });
    }
}
