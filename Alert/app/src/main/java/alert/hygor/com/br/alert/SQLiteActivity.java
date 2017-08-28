package alert.hygor.com.br.alert;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SQLiteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("appBD", MODE_PRIVATE, null);

            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR,idade INT(3))");

            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Hygor', 21)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Luana', 21)");

            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));

                cursor.moveToNext();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
