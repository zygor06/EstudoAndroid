package firebaseappv2.hygor.com.br.whatsappfake.model;


/**
 * Created by Aragorn on 01/09/2017.
 */

public class Mensagem {

    private String idUsuario;
    private String mensagem;

    public Mensagem(){

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
