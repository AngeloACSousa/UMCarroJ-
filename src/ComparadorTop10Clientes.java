import Utilizadores.Cliente;

import java.io.Serializable;
import java.util.Comparator;

public class ComparadorTop10Clientes implements Comparator<Cliente>, Serializable {
    public int compare(Cliente c1, Cliente c2){
        if(c1.getAlugueres().size()<= c2.getAlugueres().size())
            return 1;
        if(c1.getAlugueres().size()>c2.getAlugueres().size())
            return -1;
        return 0;
    }
}
