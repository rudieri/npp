package exercicios.ex4;

/**
 *
 * @author rudieri
 */
public class Funcionario {
    private static final int VALOR_POR_HORA = 12;
    private static final int VALOR_POR_DEPENDENTE = 40;
    private static final float DESC_INSS = 0.085f;
    private static final float DESC_IR = 0.05f;
    private Integer codigo;
    private Float qtdHoras;
    private Integer qtdDependentes;
     

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Float getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(Float qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public Integer getQtdDependentes() {
        return qtdDependentes;
    }

    public void setQtdDependentes(Integer qtdDependentes) {
        this.qtdDependentes = qtdDependentes;
    }
    
    public float getTotalBruto(){
        return qtdHoras * VALOR_POR_HORA + qtdDependentes * VALOR_POR_DEPENDENTE;
    }
    
    public float getDescontoInss(){
        return getTotalBruto() * DESC_INSS;
    }
    public float getDescontoIr(){
        return getTotalBruto() * DESC_IR;
    }

    @Override
    public String toString() {
        float totalBruto = getTotalBruto();
        float descInss = getDescontoInss();
        float descIr = getDescontoIr();
        float totalLiquido = totalBruto - descInss - descIr;
        
        return "Código: " + codigo + " [Desc. INSS: " + descInss + ", Desc. IR: " + descIr + ", Salário Líquido: " +  totalLiquido + "]";
    }
    
    
}
