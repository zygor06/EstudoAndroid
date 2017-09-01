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

    private final String CHAVE_EMAIL = "email";
    private final String CHAVE_SENHA = "senha";
    private final String NOME_ARQUIVO = "fakewhatsapp.preferences";

    public Preferencias(Context contextoParametro){

        this.contexto = contextoParametro;
        this.preferences = contexto.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        this.editor = preferences.edit();

    }

    public void salvarUsuarioPreferencias(String email, String senha){

        this.editor.putString(CHAVE_EMAIL, email);
        this.editor.putString(CHAVE_SENHA, senha);
        this.editor.commit();
    }

    public HashMap<String, String> getDadosUsuario(){

        HashMap<String, String> dadosUsuario = new HashMap<>();

        dadosUsuario.put(CHAVE_EMAIL, preferences.getString(CHAVE_EMAIL, null));
        dadosUsuario.put(CHAVE_SENHA, preferences.getString(CHAVE_SENHA, null));

        return dadosUsuario;
    }

    public void limparUsuarioPreferencias(){
        salvarUsuarioPreferencias(null, null);
    }
}
