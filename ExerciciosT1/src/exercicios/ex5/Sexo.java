package exercicios.ex5;

/**
 *
 * @author rudieri
 */
public enum Sexo {
    MASCULINO("M"), FEMININO("F")
    ;
    private final String sigla;

    private Sexo(String sigla) {
        this.sigla = sigla;
    }
    
    public static Sexo buscarPorSigla(String sigla){
        for (Sexo sexo : values()) {
            if (sexo.sigla.equalsIgnoreCase(sigla)) {
                return sexo;
            }
        }
        return null;
    }
    
}
