package alert.hygor.com.br.alert;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SerieActivity extends Activity {

    private SeekBar seekBar;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        imagem = (ImageView) findViewById(R.id.imagemExibicaoId);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress){
                    case 1:
                        imagem.setImageResource(R.drawable.pouco);
                        break;
                    case 2:
                        imagem.setImageResource(R.drawable.medio);
                        break;
                    case 3:
                        imagem.setImageResource(R.drawable.muito);
                        break;
                    case 4:
                        imagem.setImageResource(R.drawable.susto);
                        break;
                    default:
                        imagem.setImageResource(R.drawable.pouco);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
