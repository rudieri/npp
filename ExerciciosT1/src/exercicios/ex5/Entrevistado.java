package exercicios.ex5;

/**
 *
 * @author rudieri
 */
public class Entrevistado {
    private final Sexo sexo;
    private final int idade;
    private final int qtdLivros;

    public Entrevistado(Sexo sexo, int idade, int qtdLivros) {
        this.sexo = sexo;
        this.idade = idade;
        this.qtdLivros = qtdLivros;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    public int getQtdLivros() {
        return qtdLivros;
    }
    
    
}
