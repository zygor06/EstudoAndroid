package firebaseappv2.hygor.com.br.whatsappfake.model;

/**
 * Created by Aragorn on 01/09/2017.
 */

public class Contato {

    private String identificador;
    private String nome;
    private String email;

    public Contato(){

    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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
}
