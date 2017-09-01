package firebaseappv2.hygor.com.br.whatsappfake.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import firebaseappv2.hygor.com.br.whatsappfake.config.ConfiguracaoFireBase;

/**
 * Created by x05291657162 on 31/08/2017.
 */

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){

    }

    public void salvar(){
        DatabaseReference referencia = ConfiguracaoFireBase.getFirebase();
        referencia.child("usuarios").child(getId()).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
