package firebaseappv2.hygor.com.br.whatsappfake.model;

/**
 * Created by Aragorn on 02/09/2017.
 */

public class Conversa {

    private String idUsuario;
    private String nome;
    private String mensagem;
    private String remetente;

    public Conversa(){

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
