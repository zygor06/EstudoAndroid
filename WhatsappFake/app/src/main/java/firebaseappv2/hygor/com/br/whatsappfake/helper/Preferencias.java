package firebaseappv2.hygor.com.br.whatsappfake.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Aragorn on 30/08/2017.
 */

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String CHAVE_NOME = "nome";
    private final String CHAVE_TELEFONE = "telefone";
    private final String CHAVE_TOKEN = "token";
    private final String NOME_ARQUIVO = "fakewhatsapp.preferences";

    public Preferencias(Context contextoParametro){

        this.contexto = contextoParametro;
        this.preferences = contexto.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        this.editor = preferences.edit();

    }

    public void salvarUsuarioPreferencias(String nome, String telefone, String token){

        this.editor.putString(CHAVE_NOME, nome);
        this.editor.putString(CHAVE_TELEFONE, telefone);
        this.editor.putString(CHAVE_TOKEN, token);
        this.editor.commit();
    }

    public HashMap<String, String> getDadosUsuario(){

        HashMap<String, String> dadosUsuario = new HashMap<>();

        dadosUsuario.put(CHAVE_NOME, preferences.getString(CHAVE_NOME, null));
        dadosUsuario.put(CHAVE_TELEFONE, preferences.getString(CHAVE_TELEFONE, null));
        dadosUsuario.put(CHAVE_TOKEN, preferences.getString(CHAVE_TOKEN, null));

        return dadosUsuario;
    }
}
