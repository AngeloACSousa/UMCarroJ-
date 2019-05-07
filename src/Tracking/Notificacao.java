package Tracking;

public abstract class Notificacao {
    private int idDestinatario;
    private String mensagem;

    //construtores-------------------------------------------------------------------------------
    public Notificacao(){
        this.idDestinatario = 0;
        this.mensagem = "";
    }
    public Notificacao(int idDestinatario, String mensagem){
        this.idDestinatario = idDestinatario;
        this.mensagem = mensagem;
    }

    public Notificacao(Notificacao n){
        this.idDestinatario = n.getIdDestinatario();
        this.mensagem = n.getMensagem();
    }

    //getters------------------------------------------------------------------------------------
    public int getIdDestinatario() {
        return idDestinatario;
    }

    public String getMensagem() {
        return mensagem;
    }
    //setters-----------------------------------------------------------------------------------
    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
