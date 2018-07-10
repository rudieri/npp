package exercicios.ex8;

import java.math.BigDecimal;

/**
 *
 * @author rudieri
 */
public class Produto {
    private int id;
    private BigDecimal valor;
    private BigDecimal percAumento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPercAumento() {
        return percAumento;
    }

    public void setPercAumento(BigDecimal percAumento) {
        this.percAumento = percAumento;
    }
    
    public BigDecimal getNovoValor() {
        return valor.multiply(percAumento).divide(new BigDecimal(100)).setScale(2);
    }

    
    
   
    
}
