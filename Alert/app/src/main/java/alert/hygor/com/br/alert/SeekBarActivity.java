package alert.hygor.com.br.alert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeekBarActivity extends Activity {

    private SeekBar barra;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        barra = (SeekBar) findViewById(R.id.seekBarId);
        resultado = (TextView) findViewById(R.id.resultadoId);

        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                resultado.setText(progress + " de 10.");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this, "Register Device Failure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
