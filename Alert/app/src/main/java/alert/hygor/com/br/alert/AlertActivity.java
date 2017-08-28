package alert.hygor.com.br.alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AlertActivity extends Activity {

    private Button button;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        button = (Button) findViewById(R.id.buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new AlertDialog.Builder(AlertActivity.this);
                dialog.setTitle("Título");
                dialog.setMessage("Mensagem");
                dialog.setCancelable(false);

                dialog.setNegativeButton("Não",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertActivity.this, "Pressionado o botão não", Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertActivity.this, "Pressionado o botão sim", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.setNeutralButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertActivity.this, "Pressionado o botão cancelar", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.create();
                dialog.show();

            }
        });
    }
}
