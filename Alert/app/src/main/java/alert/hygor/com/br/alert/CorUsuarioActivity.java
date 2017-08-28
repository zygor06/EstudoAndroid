package alert.hygor.com.br.alert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class CorUsuarioActivity extends Activity {

    private RadioGroup radioGroup;
    private RadioButton selecionado;
    private Button button;
    private ConstraintLayout layout;

    private static final String SHARED_PREFERENCES = "SharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cor_usuario);

        radioGroup = (RadioGroup) findViewById(R.id.cor_radioGroup);
        button = (Button) findViewById(R.id.cor_buttonId);
        layout = (ConstraintLayout) findViewById(R.id.cor_layoutId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idRadioSelecionado = radioGroup.getCheckedRadioButtonId();

                if(idRadioSelecionado > 0){
                    selecionado = (RadioButton) findViewById(idRadioSelecionado);
                }

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String corEscolhida = selecionado.getText().toString();

                editor.putString("corEscolhida", corEscolhida);
                editor.commit();

                setBackground(corEscolhida);

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, 0);
        if(sharedPreferences.contains("corEscolhida")){
            setBackground(sharedPreferences.getString("corEscolhida", "Branco"));
        }
    }

    private void setBackground(String s){
        if(s.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#495b7c"));
        }else if(s.equals("Laranja")){
            layout.setBackgroundColor(Color.parseColor("#ffce26"));
        }else if(s.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#009670"));
        }else if(s.equals("Branco")){
            layout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }
}
