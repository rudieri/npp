package exercicios.ex5;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/**
 *
 * @author rudieri
 */
public class Ex5 {
    public static void main(String[] args) {
        ArrayList<Entrevistado> entrevistados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Informe a idade [ <= 0 p/ sair]: ");
            int idade = scanner.nextInt();
            if (idade <= 0) {
                break;
            }
            Sexo sexo;
            do {
                System.out.println("Informe o sexo[M = Masculino, F = Feminino]: ");
                String strSexo = scanner.next();
                sexo = Sexo.buscarPorSigla(strSexo);
                if (sexo != null) {
                    break;
                } else {
                    System.out.println("Valor informado inválido");
                }
            } while (true);
            System.out.println("Informe a quantidade de livros lidos em 2006: ");
            int qtdLivros = scanner.nextInt();
            Entrevistado entrevistado = new Entrevistado(sexo, idade, qtdLivros);
            entrevistados.add(entrevistado);
            System.out.println("");
            System.out.println("");
            
        } while (true);
        
        Integer qtdLivrosMenores10Anos = entrevistados.stream().filter(e -> e.getIdade() < 10).map(e->e.getQtdLivros()).reduce(0, Integer::sum);
        long qtdMulheresMais5Livros = entrevistados.stream().filter(e->e.getSexo() == Sexo.FEMININO && e.getQtdLivros() >= 5).count();
        double mediaIdadeHomensMenos5Livros = entrevistados.stream().filter(e -> e.getSexo() == Sexo.MASCULINO && e.getQtdLivros() < 5).mapToInt(e -> e.getIdade()).average().getAsDouble();
        long qtdNaoLeram = entrevistados.stream().filter(e -> e.getQtdLivros() == 0).count();
        double perNaoLeram = qtdNaoLeram * 100d / entrevistados.size();
        System.out.println("Qtd. livros lidos por menores de 10 anos: " + qtdLivrosMenores10Anos);
        System.out.println("Qtd. mulheres que leram 5 ou mais livros: " + qtdMulheresMais5Livros);
        System.out.println("Média de idade dos homens que leram menos de 5 livros: " + mediaIdadeHomensMenos5Livros);
        System.out.println("Percentual de pessoas que não leram livros: " + perNaoLeram);
    }
   
}
