package alert.hygor.com.br.alert;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ListaTarefasActivity extends Activity {

    private Button btn;
    private EditText editText;
    private ListView listView;
    private SQLiteDatabase bancoDados;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        try {

            btn = (Button) findViewById(R.id.listaTarefas_btnId);
            editText = (EditText) findViewById(R.id.listaTarefas_textEditId);
            listView = (ListView) findViewById(R.id.listaTarefas_listViewId);

            itens = new ArrayList<>();
            ids = new ArrayList<>();

            bancoDados = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    salvarTarefa(editText.getText().toString());
                    editText.setText("");
                    recuperarTarefas();
                }
            });

            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    removerTarefa(ids.get(position));

                }
            });*/

            recuperarTarefas();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void salvarTarefa(String texto){
        try{

            if(texto.equals("") || texto.isEmpty()){
                Toast.makeText(ListaTarefasActivity.this, "Digite uma tarefa", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados.execSQL("INSERT INTO tarefas (tarefa) VALUES ('" + texto + "')");
                Toast.makeText(ListaTarefasActivity.this, "Tarefa salva", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void recuperarTarefas(){
        try{

            Cursor cursor = bancoDados.rawQuery("SELECT tarefa FROM tarefas ORDER BY id DESC", null);

            int indiceTarefa = cursor.getColumnIndex("tarefa");
            int indiceId = cursor.getColumnIndex("id");

            itensAdaptador = new ArrayAdapter<String>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens);

            listView.setAdapter(itensAdaptador);

            cursor.moveToFirst();

            while(cursor != null){


                //int id = Integer.parseInt(idString);
                Log.i("TAREFAS - ", "hELLO");
                //Log.i("RESULTADO: ", cursor.getString(indiceId));
                //ids.add(Integer.parseInt(cursor.getString(indiceId)));
                itens.add(cursor.getString(indiceTarefa));
                //ids.add(cursor.getInt(indiceId));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(int id){
        try{

            bancoDados.execSQL("DELETE FROM tarefas WHERE id=" + id);
            recuperarTarefas();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
