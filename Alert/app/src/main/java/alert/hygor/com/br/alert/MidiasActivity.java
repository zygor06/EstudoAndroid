package alert.hygor.com.br.alert;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

public class MidiasActivity extends AppCompatActivity {

    private Button botao;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midias);

        botao = (Button) findViewById(R.id.buttonTocarId);
        mp = MediaPlayer.create(MidiasActivity.this, R.raw.musica);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    pausarMusica();
                }else{
                    tocarMusica();
                }
            }
        });
    }

    private void tocarMusica(){
        if(mp != null){
            mp.start();
            botao.setText("Pausar");
        }
    }

    private void pausarMusica(){
        if(mp != null){
            mp.pause();
            botao.setText("Tocar");
        }
    }

    @Override
    protected void onDestroy() {
        if(mp != null && mp.isPlaying()){
            mp.stop();
            mp.release();
            mp = null;
        }

        super.onDestroy();
    }
}
