package Tracking;


/**
 * Write a description of class PedidoAluguer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PedidoAluguer extends Notificacao
{
    private int idCliente;
    private int idVeiculo;
    private String mensagem;
    
    /**
     * Construtores--------------------------------------------------------------------------------
     * Construtor default
     */
    public PedidoAluguer(){
       super();
       this.idCliente=0;
       this.idVeiculo=0;
       this.mensagem="";
      
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
    public PedidoAluguer(int idDest, String m, int idC, int idV ){
        super(idDest,m);
        this.idCliente = idC;
        this.idVeiculo = idV;
        this.mensagem = m;
        
    
    }
     /**
     * 
     * Construtor de cópia
     * 
     */
    public PedidoAluguer(PedidoAluguer pA){
        super(pA);
        this.idCliente = pA.getIdCliente();
        this.idVeiculo = pA.getIdVeiculo();
        this.mensagem = pA.getMensagem();
    }
    /**
     * Getters-----------------------------------------------------------------------------------------------
     * @return
     */
    public int getIdCliente(){
        return idCliente;
    }
    public int getIdVeiculo(){
        return idVeiculo;
    }
    public String getMensagem(){
        return mensagem;
    }
     /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    
    public void setIdCliente(int idC){
        this.idCliente = idC;
    }
    public void setIdVeiculo(int idV){
        this.idVeiculo = idV;
    }
    public void setMensagem(String m){
        this.mensagem =m;
    }
     /**
     * Metodos----------------------------------------------------------------------------------------------------------
     * Método clone
     * @return clone de PedidoAluguer
     */
    public PedidoAluguer clone(){
        return new PedidoAluguer(this);
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
               this.idCliente == aux.getIdCliente() &&
               this.idVeiculo == aux.getIdVeiculo();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("O Cliente: ");
        sb.append(this.getIdCliente());
        sb.append("quer alugar o seu Veiculo: ");
        sb.append(this.getIdVeiculo()+"\n");

        return sb.toString();
    }
 }
