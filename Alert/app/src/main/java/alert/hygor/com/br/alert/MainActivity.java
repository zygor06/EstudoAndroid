package alert.hygor.com.br.alert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView list;
    private String[] itens = {
            "Alert",
            "Check Box",
            "Seek Bar",
            "Quanto você gosta de séries?",
            "Radio Button",
            "Toggle Button",
            "Midias",
            "Shared Preferences",
            "Cor Usuário",
            "Anotações",
            "SQLite Banco de Dados",
            "Lista de Tarefas SQLite",
            "Fragments",
            "Opção inválida"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, AlertActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, CheckBoxActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, SeekBarActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, SerieActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, RadioButtonActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ToggleActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, MidiasActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, SharedPreferencesActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, CorUsuarioActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, AnotacoesActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, ListaTarefasActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, FragmentsActivity.class));
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Opção inválida!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
