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

    private final String CHAVE_IDENTIFICADOR = "identificador";
    private final String NOME_ARQUIVO = "fakewhatsapp.preferences";

    public Preferencias(Context contextoParametro){

        this.contexto = contextoParametro;
        this.preferences = contexto.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        this.editor = preferences.edit();

    }

    public void salvarDados(String nome){

        String identificador;

        if(nome != null){
            identificador = Base64Custom.codificar64(nome);
        }else{
            identificador = nome;
        }

        this.editor.putString(CHAVE_IDENTIFICADOR, identificador);
        this.editor.commit();
    }

    public HashMap<String, String> getDadosUsuario(){

        HashMap<String, String> dadosUsuario = new HashMap<>();
        dadosUsuario.put(CHAVE_IDENTIFICADOR, preferences.getString(CHAVE_IDENTIFICADOR, null));

        return dadosUsuario;
    }

    public void limparUsuarioPreferencias(){
        salvarDados(null);
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }
}
