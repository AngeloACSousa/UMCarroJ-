package Tracking;


/**
 * Write a description of class RejeitaPedido here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RejeitaPedido extends PedidoAluguer
{
   private String mensagem;

     /**
     * Construtores--------------------------------------------------------------------------------
     * Construtor default
     */
    public RejeitaPedido()
    {
       super();
       String mensagem="";
    }
     /**
     * 
     * Construtor parameterizado
     * 
     * @param idDestinatario;
     * @param mensagem;
     * @param idCliente
     * @param idVeiculo
     * 
     */
    public RejeitaPedido(int idDest, String m, int idC, int idV ){
        super(idDest,m, idC, idV);
        this.mensagem = m;
        
    }
    /**
     * 
     * Construtor de cópia
     * 
     */
    public RejeitaPedido(RejeitaPedido aP){
        super(aP);
        this.mensagem = aP.getMensagem();
    }
    /**
     * Getters-----------------------------------------------------------------------------------------------
     * @return
     */
     public String getMensagem(){
        return mensagem;
    }
    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    public void setMensagem(String m){
        this.mensagem = m;
    }
    /**
     * Metodos----------------------------------------------------------------------------------------------------------
     * Método clone
     * @return clone de RejeitaPedido
     */
    public RejeitaPedido clone(){
        return new RejeitaPedido(this);
    }
    /**
     * Método equals
     * @param o
     * @return true ou false
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        PedidoAluguer aux =(PedidoAluguer) o;
        return super.equals(aux) && 
               this.mensagem == aux.getMensagem();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("O seu pedido de aluguer do veiculo: ");
        sb.append(this.getIdVeiculo());
        sb.append("foi: ");
        sb.append(this.getMensagem()+"\n");

        return sb.toString();
    }
}
