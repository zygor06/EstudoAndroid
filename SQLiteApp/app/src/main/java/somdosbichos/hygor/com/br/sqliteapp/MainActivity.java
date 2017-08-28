package somdosbichos.hygor.com.br.sqliteapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

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
        setContentView(R.layout.activity_main);

        try {

            btn = (Button) findViewById(R.id.buttonId);
            editText = (EditText) findViewById(R.id.editTextId);
            listView = (ListView) findViewById(R.id.listViewId);

            bancoDados = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR ) ");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    salvarTarefa(editText.getText().toString());
                }
            });

            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    removerTarefa(ids.get(position));

                }
            });*/

            listView.setLongClickable(true);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa(ids.get(position));
                    return true;
                }
            });

            recuperarTarefas();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void salvarTarefa(String texto){
        try{

            if(texto.equals("") || texto.isEmpty()){
                Toast.makeText(MainActivity.this, "Digite uma tarefa", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados.execSQL("INSERT INTO tarefas (tarefa) VALUES ('" + texto + "')");
                Toast.makeText(MainActivity.this, "Tarefa salva", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                editText.setText("");
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void recuperarTarefas(){
        try{

            Cursor cursor = bancoDados.rawQuery("SELECT id,tarefa FROM tarefas ORDER BY id DESC", null);

            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");

            itens = new ArrayList<>();
            ids = new ArrayList<>();

            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };

            listView.setAdapter(itensAdaptador);

            cursor.moveToFirst();

            while(cursor != null){

                Log.i("Resultado - ", "Id Tarefa: " + cursor.getString( indiceColunaId ) + " Tarefa: " + cursor.getString( indiceColunaTarefa ) );
                itens.add(cursor.getString(indiceColunaTarefa));
                ids.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();

            }



        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro - ", e.getMessage());
        }
    }

    private void removerTarefa(int id){
        try{

            bancoDados.execSQL("DELETE FROM tarefas WHERE id=" + id);
            recuperarTarefas();
            Toast.makeText(MainActivity.this, "Tarefa removida com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
