package somdosbichos.hygor.com.br.somdosbichos;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView cao;
    private ImageView gato;
    private ImageView leao;
    private ImageView ovelha;
    private ImageView macaco;
    private ImageView vaca;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cao     = (ImageView) findViewById(R.id.imgCaoId);
        gato    = (ImageView) findViewById(R.id.imgGatoId);
        leao    = (ImageView) findViewById(R.id.imgLeaoId);
        ovelha  = (ImageView) findViewById(R.id.imgOvelhaId);
        macaco  = (ImageView) findViewById(R.id.imgMacacoId);
        vaca    = (ImageView) findViewById(R.id.imgVacaId);

        cao.setOnClickListener(this);
        gato.setOnClickListener(this);
        leao.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        macaco.setOnClickListener(this);
        vaca.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgCaoId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.cao);
                tocarSom();
                break;
            case R.id.imgGatoId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.gato);
                tocarSom();
                break;
            case R.id.imgLeaoId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.leao);
                tocarSom();
                break;
            case R.id.imgOvelhaId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.ovelha);
                tocarSom();
                break;
            case R.id.imgMacacoId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.macaco);
                tocarSom();
                break;
            case R.id.imgVacaId:
                mp = MediaPlayer.create(MainActivity.this, R.raw.vaca);
                tocarSom();
                break;
        }
    }

    public void tocarSom(){
        if(mp != null){
            mp.start();
        }
    }

    @Override
    protected void onDestroy() {
        if(mp != null || mp.isPlaying()){
            mp.release();
            mp = null;
        }
        super.onDestroy();
    }
}
